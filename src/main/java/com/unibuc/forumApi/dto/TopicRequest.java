package com.unibuc.forumApi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel(value = "Topic request", description = "Required details needed to create a new Topic")
public class TopicRequest {

    @NotBlank
    @ApiModelProperty(value = "name", required = true, notes = "The name of the Topic", example = "Cardio", position = 1)
    private String name;
    @NotBlank
    @ApiModelProperty(value = "description", required = true, notes = "The description of the Topic", example = "The ultimate smartphone!!", position = 4)
    private String description;
    @NotNull
    @ApiModelProperty(value = "userId", required = true, notes = "The id of the User", example = "1", position = 1)
    private int userId;
    @NotNull
    @ApiModelProperty(value = "categoryId", required = true, notes = "The id of the Category", example = "1", position = 1)
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
