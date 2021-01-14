package com.unibuc.forumApi.exception;

public class TopicNotFoundException extends RuntimeException {
    public TopicNotFoundException(int id) {
        super("The topic with id " + id + " doesn't exist.");
    }
}
