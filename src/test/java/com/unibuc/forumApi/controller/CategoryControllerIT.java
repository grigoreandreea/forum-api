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

@WebMvcTest(controllers = CategoryController.class) //this tells Spring Boot to auto configure a Spring web context
// for integration tests for the DestinationController class
public class CategoryControllerIT {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CategoryService categoryService;
    @MockBean
    private CategoryMapper categoryMapper;
    @MockBean
    private TopicService topicService;
    @MockBean
    private TopicMapper topicMapper;

    @Test
    public void getCategories() throws Exception {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1, "Health"));
        categories.add(new Category(2, "Lifestyleee"));

        when(categoryService.getCategories())
                .thenReturn(Optional.of(categories));

        mockMvc.perform(get("/categories")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"name\":\"Health\"},{\"id\":2,\"name\":\"Lifestyleee\"}]"));
    }

    @Test
    public void getCategory() throws Exception {
        Category category = new Category(2, "Lifestyleee");

        when(categoryService.getCategory(category.getId()))
                .thenReturn(Optional.of(category));

        mockMvc.perform(get("/categories/2")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(category.getName()));
    }

    @Test
    public void createCategory() throws Exception {
        CategoryRequest request = new CategoryRequest("Lifestyleee");

        when(categoryService.create(any())).thenReturn(new Category("Lifestyleee"));

        mockMvc.perform(post("/categories")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(request.getName()));
    }

    @Test
    public void updateCategory() throws Exception {
        CategoryRequest request = new CategoryRequest("Lifestyle Updated");

        when(categoryService.create(any())).thenReturn(new Category(2, "Lifestyle Updated"));
        when(categoryMapper.categoryRequestToCategory(any())).thenReturn(new Category("Lifestyle Updated"));

        mockMvc.perform(put("/categories/2")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(request.getName()));
    }

    @Test
    public void deleteCategory() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .delete("/categories/2")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
}
