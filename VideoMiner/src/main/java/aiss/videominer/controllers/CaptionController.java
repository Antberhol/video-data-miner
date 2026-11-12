package aiss.videominer.controllers;
import aiss.videominer.model.Caption;
import aiss.videominer.repository.CaptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Caption", description = "Caption management API")
@RestController
@RequestMapping("/videominer/captions")
public class CaptionController {

    private final CaptionRepository captionRepository;

    @Autowired
    public CaptionController(CaptionRepository captionRepository){
        this.captionRepository = captionRepository;
    }

    @Operation(summary = "Retrieve a list of Captions", description = "Get a list of Caption objects", tags = { "Caption" })
    @GetMapping
    public List<Caption> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String language,
            @RequestParam(required = false) String order
    ){
        Pageable pageable;
        if(order != null){
            if(order.startsWith("-")){
                pageable = PageRequest.of(page, size, Sort.by(order.substring(1)).descending());
            }else{
                pageable = PageRequest.of(page, size, Sort.by(order).ascending());
            }
        }else {
            pageable = PageRequest.of(page, size);
        }
        
        Page<Caption> captions;
        if (language != null) {
            captions = captionRepository.findByLanguageContaining(language, pageable);
        } else {
            captions = captionRepository.findAll(pageable);
        }
        
        return captions.getContent();
    }

    @Operation(summary = "Retrieve a Caption by Id", description = "Get a Caption object by specifying its id", tags = { "Caption" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Caption found", content = { @Content(schema = @Schema(implementation = Caption.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "Caption not found", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("/{id}")
    public Caption findOne(@Parameter(description = "id of caption to be searched") @PathVariable String id) throws aiss.videominer.exception.CaptionException {
        Optional<Caption> Caption = captionRepository.findById(id);
        if (!Caption.isPresent()) {
            throw new aiss.videominer.exception.CaptionException();
        }

        return Caption.get();
    }

}
