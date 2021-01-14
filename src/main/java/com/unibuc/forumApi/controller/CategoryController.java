package com.unibuc.forumApi.controller;


import com.unibuc.forumApi.dto.TopicRequest;
import com.unibuc.forumApi.dto.CategoryRequest;
import com.unibuc.forumApi.dto.CategoryWithTopics;
import com.unibuc.forumApi.exception.TopicNotFoundException;
import com.unibuc.forumApi.exception.CategoryNotFoundException;
import com.unibuc.forumApi.mapper.CategoryMapper;
import com.unibuc.forumApi.mapper.CommentMapper;
import com.unibuc.forumApi.mapper.TopicMapper;
import com.unibuc.forumApi.model.Topic;
import com.unibuc.forumApi.model.Category;
import com.unibuc.forumApi.service.CategoryService;
import com.unibuc.forumApi.service.CommentService;
import com.unibuc.forumApi.service.TopicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;
    private final TopicService topicService;
    private final TopicMapper topicMapper;
    private final CommentMapper commentMapper;
    private final CommentService commentService;

    public CategoryController(
            CategoryService categoryService, 
            CategoryMapper categoryMapper, 
            TopicService topicService, 
            TopicMapper topicMapper, 
            CommentMapper commentMapper, 
            CommentService commentService
    ) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
        this.topicService = topicService;
        this.topicMapper = topicMapper;
        this.commentMapper = commentMapper;
        this.commentService = commentService;
    }

    @GetMapping
    @ResponseBody
    public Optional<List<Category>> getCategories() {
        return categoryService.getCategories();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Optional<Category> getCategory(@PathVariable int id) {
        return categoryService.getCategory(id);
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody CategoryRequest categoryRequest) {
        Category mappedCategory = categoryMapper.categoryRequestToCategory(categoryRequest);
        Category savedCategory = categoryService.create(mappedCategory);
        return ResponseEntity.created(URI.create("/categories/" + savedCategory.getId()))
                .body(savedCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable int id, @RequestBody CategoryRequest CategoryRequest) {
        Category mappedCategory = categoryMapper.categoryRequestToCategory(CategoryRequest);
        mappedCategory.setId(id);
        Category savedCategory = categoryService.create(mappedCategory);
        return ResponseEntity.created(URI.create("/categories/" + savedCategory.getId()))
                .body(savedCategory);
    }

    @DeleteMapping("/{id}")
    public void removeCategory(@PathVariable int id) {
        categoryService.removeCategory(id);
    }

    @GetMapping("/{id}/topics")
    @ResponseBody
    public CategoryWithTopics getCategoryTopics(@PathVariable int id) {
        Optional<Category> Category = categoryService.getCategory(id);
        if(Category.isEmpty()) {
            throw new CategoryNotFoundException(id);
        }

        Optional<List<Topic>> Topics = categoryService.getCategoryTopics(id);
        if(Topics.isEmpty()) {
            return new CategoryWithTopics(Category.get());
        }

        return new CategoryWithTopics(Category.get(), Topics.get());
    }

    @GetMapping("/{id}/topics/{idTopic}")
    @ResponseBody
    public Optional<Topic> getCategoryTopics(@PathVariable int id, @PathVariable int idTopic) {
        Optional<Category> Category = categoryService.getCategory(id);
        if(Category.isEmpty()) {
            throw new CategoryNotFoundException(id);
        }

        Optional<List<Topic>> Topics = categoryService.getCategoryTopics(id);
        if(Topics.isEmpty()) {
            throw new TopicNotFoundException(idTopic);
        }

        return Topics.get().stream().filter(Topic -> Topic.getId() == idTopic).findFirst();
    }

    @PostMapping("/{id}/topics")
    public ResponseEntity<Topic> createTopic(@PathVariable int id, @RequestBody TopicRequest TopicRequest) {
        Optional<Category> Category = categoryService.getCategory(id);
        if(Category.isEmpty()) {
            throw new CategoryNotFoundException(id);
        }

        TopicRequest.setCategoryId(id);
        Topic mappedTopic = topicMapper.topicRequestToTopic(TopicRequest);
        Topic savedTopic = topicService.create(mappedTopic);
        return ResponseEntity.created(URI.create("/comments/" + savedTopic.getId()))
                .body(savedTopic);
    }

    @PutMapping("/{id}/topics/{idTopic}")
    public ResponseEntity<Topic> updateTopic(@PathVariable int id, @PathVariable int idTopic, @RequestBody TopicRequest TopicRequest) {
        Optional<Category> Category = categoryService.getCategory(id);
        if(Category.isEmpty()) {
            throw new CategoryNotFoundException(id);
        }

        TopicRequest.setCategoryId(id);
        Topic mappedTopic = topicMapper.topicRequestToTopic(TopicRequest);
        mappedTopic.setId(idTopic);
        Topic savedTopic = topicService.create(mappedTopic);
        return ResponseEntity.created(URI.create("/comments/" + savedTopic.getId()))
                .body(savedTopic);
    }

    @DeleteMapping("/{id}/topics/{idTopic}")
    public void removeComment(@PathVariable int id, @PathVariable int idTopic) {
        topicService.removeTopic(idTopic);
    }

}
