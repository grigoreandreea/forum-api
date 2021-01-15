package com.unibuc.forumApi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel(value = "Comment request", description = "Required details needed to create a new Comment")
public class CommentRequest {
    @NotBlank
    @ApiModelProperty(value = "description", required = true, notes = "The description of the Comment", example = "It is a good topic!", position = 1)
    private String description;
    @NotNull
    @ApiModelProperty(value = "topicId", required = true, notes = "The id of the Topic", example = "1", position = 1)
    private int topicId;
    @NotNull
    @ApiModelProperty(value = "userId", required = true, notes = "The id of the User", example = "1", position = 1)
    private int userId;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public CommentRequest() {
    }

    public CommentRequest(String description, int topicId, int userId) {
        this.description = description;
        this.topicId = topicId;
        this.userId = userId;
    }

}
