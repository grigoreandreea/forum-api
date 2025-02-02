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

@WebMvcTest(controllers = TopicController.class) //this tells Spring Boot to auto configure a Spring web context
// for integration tests for the DestinationController class
public class TopicControllerIT {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private TopicService topicService;
    @MockBean
    private TopicMapper topicMapper;
    @MockBean
    private CommentService commentService;
    @MockBean
    private CommentMapper commentMapper;

    @Test
    public void getTopics() throws Exception {
        List<Topic> topics = new ArrayList<>();
        topics.add(new Topic(1, "Test Topic 1", "Description 1", 1, 1));
        topics.add(new Topic(2, "Test Topic 2", "Description 2", 1, 1));

        when(topicService.getTopics())
                .thenReturn(Optional.of(topics));

        mockMvc.perform(get("/topics")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().json(" [{\"id\":1,\"name\":\"Test Topic 1\",\"description\":\"Description 1\",\"userId\":1,\"categoryId\":1},{\"id\":2,\"name\":\"Test Topic 2\",\"description\":\"Description 2\",\"userId\":1,\"categoryId\":1}]"));
    }

    @Test
    public void getTopic() throws Exception {
        Topic topic = new Topic(2, "Test Topic 2", "Description 2", 1, 1);

        when(topicService.getTopic(topic.getId()))
                .thenReturn(Optional.of(topic));

        mockMvc.perform(get("/topics/2")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(topic.getName()));
    }

    @Test
    public void createTopic() throws Exception {
        TopicRequest request = new TopicRequest("Test Topic 2", "Description 2", 1, 1);

        when(topicService.create(any())).thenReturn(new Topic(2, "Test Topic 2", "Description 2", 1, 1));
        when(topicMapper.topicRequestToTopic(any())).thenReturn(new Topic(2, "Test Topic 2", "Description 2", 1, 1));

        mockMvc.perform(post("/topics")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(request.getName()));
    }

    @Test
    public void updateTopic() throws Exception {
        TopicRequest request = new TopicRequest("Test Topic 2", "Description 2", 1, 1);

        when(topicService.create(any())).thenReturn(new Topic(2, "Test Topic 2", "Description 2", 1, 1));
        when(topicMapper.topicRequestToTopic(any())).thenReturn(new Topic(2, "Test Topic 2", "Description 2", 1, 1));

        mockMvc.perform(put("/topics/2")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(request.getName()));
    }

    @Test
    public void deleteTopic() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .delete("/topics/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
