package com.unibuc.forumApi.dto;

import com.unibuc.forumApi.model.Topic;
import com.unibuc.forumApi.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserWithTopics {
    private User user;
    private List<Topic> topics;

    public UserWithTopics() {
    }

    public UserWithTopics(User user, List<Topic> topics) {
        this.user = user;
        this.topics = topics;
    }

    public UserWithTopics(User user) {
        this.user = user;
        this.topics = new ArrayList<>();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }
}
