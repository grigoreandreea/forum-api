package com.unibuc.forumApi.model;


public class Topic {
    private int id;
    private String name;
    private String description;
    private int userId;
    private int categoryId;

    public Topic() {
    }

    public Topic(int id, String name, String description, int userId, int categoryId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.userId = userId;
        this.categoryId = categoryId;
    }

    public Topic(String name, String description, int userId, int categoryId) {
        this.name = name;
        this.description = description;
        this.userId = userId;
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", userId=" + userId +
                ", categoryId=" + categoryId +
                '}';
    }
}
