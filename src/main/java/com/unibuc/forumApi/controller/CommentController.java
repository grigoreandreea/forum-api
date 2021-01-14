package com.unibuc.forumApi.controller;

import com.unibuc.forumApi.dto.CommentRequest;
import com.unibuc.forumApi.dto.TopicRequest;
import com.unibuc.forumApi.mapper.CommentMapper;
import com.unibuc.forumApi.model.Comment;
import com.unibuc.forumApi.model.Topic;
import com.unibuc.forumApi.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
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
    public ResponseEntity<Comment> createComment(@RequestBody CommentRequest commentRequest) {
        Comment mappedComment = commentMapper.commentRequestToComment(commentRequest);
        System.out.println(mappedComment.toString());
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
