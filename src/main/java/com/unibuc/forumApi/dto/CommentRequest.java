package com.unibuc.forumApi.dto;

public class CommentRequest {
    private String description;

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

    private int topicId;

    public CommentRequest() {
    }

    private int userId;

    public CommentRequest(String description, int topicId, int userId) {
        this.description = description;
        this.topicId = topicId;
        this.userId = userId;
    }

}
