package aiss.videominer.controllers;


import aiss.videominer.model.Video;
import aiss.videominer.repository.VideoRepository;
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

@Tag(name = "Video", description = "Video management API")
@RestController
@RequestMapping("/videominer/videos")
public class VideoController {

    private final VideoRepository videoRepository;

    @Autowired
    public VideoController(VideoRepository videoRepository){
        this.videoRepository = videoRepository;
    }

    @Operation(summary = "Retrieve a list of Videos", description = "Get a list of Video objects", tags = { "Video" })
    @GetMapping
    public List<Video> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
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
        Page<Video> videos;
        if(name != null){
            videos = videoRepository.findByNameContaining(name, pageable);
        }else if (description != null) {
            videos = videoRepository.findByDescriptionContaining(description, pageable);
        }else{
            videos = videoRepository.findAll(pageable);
        }
        return videos.getContent();
    }

    @Operation(summary = "Retrieve a Video by Id", description = "Get a Video object by specifying its id", tags = { "Video" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Video found", content = { @Content(schema = @Schema(implementation = Video.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "Video not found", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("/{id}")
    public Video findOne(@Parameter(description = "id of video to be searched") @PathVariable String id) throws aiss.videominer.exception.VideoException {
        Optional<Video> video = videoRepository.findById(id);
        if (!video.isPresent()) {
            throw new aiss.videominer.exception.VideoException();
        }

        return video.get();
    }

    @Operation(summary = "Retrieve a list of Videos by User Id", description = "Get a list of Video objects by User Id", tags = { "Video" })
    @GetMapping("/user/{userId}")
    public List<Video> findByUserId(@Parameter(description = "id of user to find videos for") @PathVariable String userId){
        return videoRepository.findAll().stream().filter(v -> v.getAuthor() != null && v.getAuthor().getId() != null && v.getAuthor().getId().equals(userId)).toList();
    }

    @Operation(summary = "Delete a Video", description = "Delete a Video object by specifying its id", tags = { "Video" })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVideo(@Parameter(description = "id of video to be deleted") @PathVariable String id) throws aiss.videominer.exception.VideoException {
        Optional<Video> video = videoRepository.findById(id);
        if (!video.isPresent()) {
            throw new aiss.videominer.exception.VideoException();
        }
        videoRepository.deleteById(id);
    }

}
