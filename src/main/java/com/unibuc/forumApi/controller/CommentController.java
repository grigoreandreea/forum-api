package com.unibuc.forumApi.controller;

import com.unibuc.forumApi.dto.CommentRequest;
import com.unibuc.forumApi.mapper.CommentMapper;
import com.unibuc.forumApi.model.Comment;
import com.unibuc.forumApi.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
@Api(value = "/comments",
        tags = "Comments")
public class CommentController {
    private final CommentService commentService;
    private final CommentMapper commentMapper;

    public CommentController(
            CommentService commentService,
            CommentMapper commentMapper
    ) {
        this.commentService = commentService;
        this.commentMapper = commentMapper;
    }

    @GetMapping
    @ResponseBody
    public Optional<List<Comment>> getComments() {
        return commentService.getComments();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Optional<Comment> getComment(@PathVariable int id) {
        return commentService.getComment(id);
    }

    @PostMapping
    @ApiOperation(value = "Create a Comment",
            notes = "Creates a new Comment based on the information received in the request")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Comment was successfully created based on the received request"),
            @ApiResponse(code = 400, message = "Validation error on the received request")
    })
    public ResponseEntity<Comment> createComment(@RequestBody CommentRequest commentRequest) {
        Comment mappedComment = commentMapper.commentRequestToComment(commentRequest);
        Comment savedComment = commentService.create(mappedComment);
        return ResponseEntity.created(URI.create("/comments/" + savedComment.getId()))
                .body(savedComment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable int id, @RequestBody CommentRequest commentRequest) {
        Comment mappedComment = commentMapper.commentRequestToComment(commentRequest);
        mappedComment.setId(id);
        Comment savedComment = commentService.update(mappedComment);
        return ResponseEntity.created(URI.create("/comments/" + savedComment.getId()))
                .body(savedComment);
    }

    @DeleteMapping("/{id}")
    public void removeComment(@PathVariable int id) {
        commentService.removeComment(id);
    }





}
