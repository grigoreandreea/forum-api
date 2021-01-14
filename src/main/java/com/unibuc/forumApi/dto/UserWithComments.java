package com.unibuc.forumApi.dto;

import com.unibuc.forumApi.model.Comment;
import com.unibuc.forumApi.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserWithComments {
    private User user;
    private List<Comment> comments;

    public UserWithComments() {
    }

    public UserWithComments(User user, List<Comment> Comments) {
        this.user = user;
        this.comments = Comments;
    }

    public UserWithComments(User user) {
        this.user = user;
        this.comments = new ArrayList<>();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> Comments) {
        this.comments = Comments;
    }
}
