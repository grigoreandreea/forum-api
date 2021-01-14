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

@WebMvcTest(controllers = UserController.class) //this tells Spring Boot to auto configure a Spring web context
// for integration tests for the DestinationController class
public class UserControllerIT {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UserService userService;
    @MockBean
    private UserMapper userMapper;
    @MockBean
    private TopicService topicService;
    @MockBean
    private TopicMapper topicMapper;

    @Test
    public void getUsers() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "John Snow", 1, 1));
        users.add(new User(2, "Dean Martin", 1, 1));

        when(userService.getUsers())
                .thenReturn(Optional.of(users));

        mockMvc.perform(get("/users")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"username\":\"John Snow\",\"date\":null,\"gender\":false,\"countryId\":1,\"cityId\":1},{\"id\":2,\"username\":\"Dean Martin\",\"date\":null,\"gender\":false,\"countryId\":1,\"cityId\":1}]"));
    }

    @Test
    public void getUser() throws Exception {
        User User = new User(2, "Dean Martin", 1, 1);

        when(userService.getUser(User.getId()))
                .thenReturn(Optional.of(User));

        mockMvc.perform(get("/users/2")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(User.getUsername()));
    }

    @Test
    public void createUser() throws Exception {
        UserRequest request = new UserRequest("Dean Martin", 1, 1);

        when(userService.create(any())).thenReturn(new User(2, "Dean Martin", 1, 1));

        mockMvc.perform(post("/users")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value(request.getUsername()));
    }

    @Test
    public void updateUser() throws Exception {
        UserRequest request = new UserRequest("Dean Martin", 1, 1);

        when(userService.update(any())).thenReturn(new User(2, "Dean Martin", 1, 1));
        when(userMapper.userRequestToUser(any())).thenReturn(new User(2, "Dean Martin", 1, 1));

        mockMvc.perform(put("/users/2")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value(request.getUsername()));
    }

    @Test
    public void deleteUser() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .delete("/users/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
