package aiss.videominer.controllers;
import aiss.videominer.model.Channel;
import aiss.videominer.repository.ChannelRepository;
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
import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Channel", description = "Channel management API")
@RestController
@RequestMapping("/videominer/channels")
public class ChannelController {

    private final ChannelRepository channelRepository;

    @Autowired
    public ChannelController(ChannelRepository channelRepository){
        this.channelRepository = channelRepository;
    }

    @Operation(summary = "Retrieve a list of Channels", description = "Get a list of Channel objects", tags = { "Channel" })
    @GetMapping
    public List<Channel> findAll(
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
        Page<Channel> channels;
        if(name != null){
            channels = channelRepository.findByNameContaining(name, pageable);
        }else if(description != null){
            channels = channelRepository.findByDescriptionContaining(description, pageable);
        }else{
            channels = channelRepository.findAll(pageable);
        }

        return channels.getContent();
    }

    @Operation(summary = "Retrieve a Channel by Id", description = "Get a Channel object by specifying its id", tags = { "Channel" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Channel found", content = { @Content(schema = @Schema(implementation = Channel.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "Channel not found", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("/{id}")
    public Channel findOne(@Parameter(description = "id of channel to be searched") @PathVariable String id) throws aiss.videominer.exception.ChannelException {
        Optional<Channel> channel = channelRepository.findById(id);
        if (!channel.isPresent()) {
            throw new aiss.videominer.exception.ChannelException();
        }

        return channel.get();
    }

    @Operation(summary = "Create a Channel", description = "Create a new Channel object", tags = { "Channel" })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Channel createChannel(@Valid @RequestBody Channel channel){
        return channelRepository.save(channel);
    }

    @Operation(summary = "Update a Channel", description = "Update an existing Channel object by id", tags = { "Channel" })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Channel updateChannel(@Valid @RequestBody Channel channel, @Parameter(description = "id of channel to be updated") @PathVariable String id){
        channel.setId(id);
        return channelRepository.save(channel);
    }

    @Operation(summary = "Delete a Channel", description = "Delete a Channel object by specifying its id", tags = { "Channel" })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteChannel(@Parameter(description = "id of channel to be deleted") @PathVariable String id) throws aiss.videominer.exception.ChannelException {
        Optional<Channel> channel = channelRepository.findById(id);
        if(!channel.isPresent()){
            throw new aiss.videominer.exception.ChannelException();
        }
        channelRepository.deleteById(id);
    }
}
