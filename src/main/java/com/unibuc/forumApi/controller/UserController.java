package com.unibuc.forumApi.controller;

import com.unibuc.forumApi.dto.*;
import com.unibuc.forumApi.exception.TopicNotFoundException;
import com.unibuc.forumApi.exception.UserNotFoundException;
import com.unibuc.forumApi.mapper.*;
import com.unibuc.forumApi.model.*;
import com.unibuc.forumApi.service.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final TopicService topicService;
    private final UserMapper userMapper;

    public UserController(
            UserService userService,
            TopicService topicService,
            UserMapper userMapper
    ) {
        this.userService = userService;
        this.topicService = topicService;
        this.userMapper = userMapper;
    }

    @GetMapping
    @ResponseBody
    public Optional<List<User>> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Optional<User> getUser(@PathVariable int id) {
        return userService.getUser(id);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserRequest userRequest) {
        User mappedUser = userMapper.userRequestToUser(userRequest);
        User savedUser = userService.create(mappedUser);
        return ResponseEntity.created(URI.create("/users/" + savedUser.getId()))
                .body(savedUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody UserRequest userRequest) {
        User mappedUser = userMapper.userRequestToUser(userRequest);
        mappedUser.setId(id);
        User savedUser = userService.update(mappedUser);
        return ResponseEntity.created(URI.create("/users/" + savedUser.getId()))
                .body(savedUser);
    }

    @DeleteMapping("/{id}")
    public void removeUser(@PathVariable int id) {
        userService.removeUser(id);
    }

    @GetMapping("/{id}/topics")
    @ResponseBody
    public UserWithTopics getUserTopics(@PathVariable int id) {
        Optional<User> user = userService.getUser(id);
        if(user.isEmpty()) {
            throw new UserNotFoundException(id);
        }

        Optional<List<Topic>> topics = userService.getUserTopics(id);
        if(topics.isEmpty()) {
            return new UserWithTopics(user.get());
        }

        return new UserWithTopics(user.get(), topics.get());
    }

    @GetMapping("/{id}/topics/{idTopic}")
    @ResponseBody
    public List<Topic> getUserTopics(@PathVariable int id, @PathVariable int idTopic) {
        Optional<User> user = userService.getUser(id);
        if(user.isEmpty()) {
            throw new UserNotFoundException(id);
        }

        Optional<List<Topic>> topics = userService.getUserTopics(id);
        if(topics.isEmpty()) {
            return new ArrayList<>();
        }

        return topics.get().stream().filter(topic -> topic.getId() == idTopic).collect(Collectors.toList());
    }

    @GetMapping("/{id}/topics/{idTopic}/comments")
    @ResponseBody
    public TopicWithComments getTopicsComments(@PathVariable int id, @PathVariable int idTopic) {
        Optional<User> user = userService.getUser(id);
        if(user.isEmpty()) {
            throw new UserNotFoundException(id);
        }
        Optional<Topic> topic = topicService.getTopic(idTopic);
        if(topic.isEmpty()) {
            return null;
        }
        TopicWithComments response = new TopicWithComments(topic.get());

        Optional<List<Comment>> comments = topicService.getTopicComments(idTopic);
        if(comments.isEmpty()) {
            response.setComments(new ArrayList<>());
        } else {
            response.setComments(comments.get());
        }
        return response;
    }

    @GetMapping("/{id}/topics/{idTopic}/comments/{idComment}")
    @ResponseBody
    public Optional<Comment> getTopicsComment(@PathVariable int id, @PathVariable int idTopic, @PathVariable int idComment) {
        Optional<User> user = userService.getUser(id);
        if(user.isEmpty()) {
            throw new UserNotFoundException(id);
        }
        Optional<Topic> topic = topicService.getTopic(idTopic);
        if(topic.isEmpty()) {
            throw new TopicNotFoundException(id);
        }
        Optional<List<Comment>> comments = topicService.getTopicComments(idTopic);
        if(comments.isEmpty()) {
            return null;
        }
        return comments.get().stream().filter(comment -> comment.getId() == idComment).findFirst();
    }

    @GetMapping("/{id}/comments")
    @ResponseBody
    public UserWithComments getUserComments(@PathVariable int id) {
        Optional<User> user = userService.getUser(id);
        if(user.isEmpty()) {
            throw new UserNotFoundException(id);
        }

        Optional<List<Comment>> comments = userService.getUserComments(id);
        if(comments.isEmpty()) {
            return new UserWithComments(user.get());
        }

        return new UserWithComments(user.get(), comments.get());
    }

    @GetMapping("/{id}/comments/{idComment}")
    @ResponseBody
    public Optional<Comment> getUserComments(@PathVariable int id, @PathVariable int idComment) {
        Optional<User> user = userService.getUser(id);
        if(user.isEmpty()) {
            throw new UserNotFoundException(id);
        }

        Optional<List<Comment>> comments = userService.getUserComments(id);
        if(comments.isEmpty()) {
            return null;
        }

        return comments.get().stream().filter(comment -> comment.getId() == idComment).findFirst();
    }
}
