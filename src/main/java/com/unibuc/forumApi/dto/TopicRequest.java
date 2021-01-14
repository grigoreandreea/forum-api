package com.unibuc.forumApi.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TopicRequest {

    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotNull
    private int userId;

    @NotNull
    private int categoryId;

    public TopicRequest() {
    }

    public TopicRequest(String name, String description, int userId, int categoryId) {
        this.name = name;
        this.description = description;
        this.userId = userId;
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
