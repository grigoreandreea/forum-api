package com.unibuc.forumApi.model;

public class Comment {
    private int id;
    private String description;
    private int topicId;
    private int userId;

    public Comment() {
    }

    public Comment(int id, String description, int topicId, int userId) {
        this.id = id;
        this.description = description;
        this.topicId = topicId;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
}
