package com.unibuc.forumApi.controller;

import com.unibuc.forumApi.dto.*;
import com.unibuc.forumApi.exception.CommentNotFoundException;
import com.unibuc.forumApi.exception.TopicNotFoundException;
import com.unibuc.forumApi.mapper.*;
import com.unibuc.forumApi.model.Comment;
import com.unibuc.forumApi.model.Topic;
import com.unibuc.forumApi.service.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topics")
public class TopicController {
    private final TopicService topicService;
    private final TopicMapper topicMapper;
    private final CommentMapper commentMapper;
    private final CommentService commentService;

    public TopicController(
            TopicService topicService,
            TopicMapper topicMapper,
            CommentMapper commentMapper,
            CommentService commentService
    ) {
        this.topicService = topicService;
        this.topicMapper = topicMapper;
        this.commentMapper = commentMapper;
        this.commentService = commentService;
    }

    @GetMapping
    @ResponseBody
    public Optional<List<Topic>> getTopic() {
        return topicService.getTopics();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Optional<Topic> getTopic(@PathVariable int id) {
        return topicService.getTopic(id);
    }

    @PostMapping
    @ApiOperation(value = "Create a Topic",
            notes = "Creates a new Topic based on the information received in the request")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Topic was successfully created based on the received request"),
            @ApiResponse(code = 400, message = "Validation error on the received request")
    })
    public ResponseEntity<Topic> createTopic(@RequestBody TopicRequest topicRequest) {
        Topic mappedTopic = topicMapper.topicRequestToTopic(topicRequest);
        Topic savedTopic = topicService.create(mappedTopic);
        return ResponseEntity.created(URI.create("/topics/" + savedTopic.getId()))
                .body(savedTopic);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Topic> updateTopic(@PathVariable int id, @RequestBody TopicRequest topicRequest) {
        Topic mappedTopic = topicMapper.topicRequestToTopic(topicRequest);
        mappedTopic.setId(id);
        Topic savedTopic = topicService.create(mappedTopic);
        return ResponseEntity.created(URI.create("/topics/" + savedTopic.getId()))
                .body(savedTopic);
    }

    @DeleteMapping("/{id}")
    public void removeTopic(@PathVariable int id) {
        topicService.removeTopic(id);
    }

    @GetMapping("/{id}/comments")
    @ResponseBody
    public TopicWithComments getTopicComments(@PathVariable int id) {
        Optional<Topic> topic = topicService.getTopic(id);
        if(topic.isEmpty()) {
            throw new TopicNotFoundException(id);
        }

        Optional<List<Comment>> comments = topicService.getTopicComments(id);
        if(comments.isEmpty()) {
            return new TopicWithComments(topic.get());
        }

        return new TopicWithComments(topic.get(), comments.get());
    }

    @GetMapping("/{id}/comments/{idComment}")
    @ResponseBody
    public Optional<Comment> getTopicComments(@PathVariable int id, @PathVariable int idComment) {
        Optional<Topic> topic = topicService.getTopic(id);
        if(topic.isEmpty()) {
            throw new TopicNotFoundException(id);
        }

        Optional<List<Comment>> comments = topicService.getTopicComments(id);
        if(comments.isEmpty()) {
            throw new CommentNotFoundException(idComment);
        }

        return comments.get().stream().filter(comment -> comment.getId() == idComment).findFirst();
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<Comment> createComment(@PathVariable int id, @RequestBody CommentRequest commentRequest) {
        Optional<Topic> topic = topicService.getTopic(id);
        if(topic.isEmpty()) {
            throw new TopicNotFoundException(id);
        }

        commentRequest.setTopicId(id);
        Comment mappedComment = commentMapper.commentRequestToComment(commentRequest);
        Comment savedComment = commentService.create(mappedComment);
        return ResponseEntity.created(URI.create("/comments/" + savedComment.getId()))
                .body(savedComment);
    }

    @PutMapping("/{id}/comments/{idComment}")
    public ResponseEntity<Comment> updateComment(@PathVariable int id, @PathVariable int idComment, @RequestBody CommentRequest commentRequest) {
        Optional<Topic> topic = topicService.getTopic(id);
        if(topic.isEmpty()) {
            throw new TopicNotFoundException(id);
        }

        commentRequest.setTopicId(id);
        Comment mappedComment = commentMapper.commentRequestToComment(commentRequest);
        mappedComment.setId(idComment);
        Comment savedComment = commentService.create(mappedComment);
        return ResponseEntity.created(URI.create("/comments" + savedComment.getId()))
                .body(savedComment);
    }

    @DeleteMapping("/{id}/comments/{idComment}")
    public void removeComment(@PathVariable int id, @PathVariable int idComment) {
        commentService.removeComment(idComment);
    }
}
