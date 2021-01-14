package com.unibuc.forumApi.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(int id) {
        super("The user with id " + id + " doesn't exist.");
    }
}
