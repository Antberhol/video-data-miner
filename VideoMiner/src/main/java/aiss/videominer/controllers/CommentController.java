package aiss.videominer.controllers;


import aiss.videominer.model.Comment;
import aiss.videominer.model.Video;
import aiss.videominer.repository.CommentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

@Tag(name = "Comment", description = "Comment management API")
@RestController
@RequestMapping("/videominer/comments")
public class CommentController {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentController(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    @Operation(summary = "Retrieve a list of Comments", description = "Get a list of Comment objects", tags = { "Comment" })
    @GetMapping
    public List<Comment> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String text,
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
        
        Page<Comment> comments;
        if(text != null){
            comments = commentRepository.findByTextContaining(text, pageable);
        } else {
            comments = commentRepository.findAll(pageable);
        }
        return comments.getContent();
    }

    @Operation(summary = "Retrieve a Comment by Id", description = "Get a Comment object by specifying its id", tags = { "Comment" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Comment found", content = { @Content(schema = @Schema(implementation = Comment.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "Comment not found", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("/{id}")
    public Comment findOne(@Parameter(description = "id of comment to be searched") @PathVariable String id) throws aiss.videominer.exception.CommentException {
        Optional<Comment> comment = commentRepository.findById(id);
        if (!comment.isPresent()) {
            throw new aiss.videominer.exception.CommentException();
        }

        return comment.get();
    }

}
