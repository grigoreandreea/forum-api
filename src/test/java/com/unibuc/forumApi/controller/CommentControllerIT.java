package com.unibuc.forumApi.controller;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.unibuc.forumApi.dto.*;
import com.unibuc.forumApi.mapper.*;
import com.unibuc.forumApi.model.*;
import com.unibuc.forumApi.service.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = CommentController.class) //this tells Spring Boot to auto configure a Spring web context
// for integration tests for the DestinationController class
public class CommentControllerIT {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CommentService commentService;
    @MockBean
    private CommentMapper commentMapper;

    @Test
    public void getComments() throws Exception {
        List<Comment> comments = new ArrayList<>();
        comments.add(new Comment(1, "Description 1", 1, 1));
        comments.add(new Comment(2, "Description 2", 1, 1));

        when(commentService.getComments())
                .thenReturn(Optional.of(comments));

        mockMvc.perform(get("/comments")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().json(" [{\"id\":1,\"description\":\"Description 1\",\"userId\":1,\"topicId\":1},{\"id\":2,\"description\":\"Description 2\",\"userId\":1,\"topicId\":1}]"));
    }

    @Test
    public void getComment() throws Exception {
        Comment comment = new Comment( 2, "Description 2", 1, 1);

        when(commentService.getComment(comment.getId()))
                .thenReturn(Optional.of(comment));

        mockMvc.perform(get("/comments/2")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value(comment.getDescription()));
    }

    @Test
    public void createComment() throws Exception {
        CommentRequest request = new CommentRequest("Description 2", 1, 1);

        when(commentService.create(any())).thenReturn(new Comment( 2, "Description 2", 1, 1));
        when(commentMapper.commentRequestToComment(any())).thenReturn(new Comment( 2, "Description 2", 1, 1));

        mockMvc.perform(post("/comments")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.description").value(request.getDescription()));
    }

    @Test
    public void updateComment() throws Exception {
        CommentRequest request = new CommentRequest("Description 2", 1, 1);

        when(commentService.update(any())).thenReturn(new Comment( 2, "Description 2", 1, 1));
        when(commentMapper.commentRequestToComment(any())).thenReturn(new Comment( 2, "Description 2", 1, 1));

        mockMvc.perform(put("/comments/2")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.description").value(request.getDescription()));
    }

    @Test
    public void deleteComment() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .delete("/comments/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
