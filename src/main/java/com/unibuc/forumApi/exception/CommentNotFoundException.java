package com.unibuc.forumApi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CommentNotFoundException extends ResponseStatusException {
    public CommentNotFoundException(int id) {
        super(HttpStatus.NOT_FOUND, "comment with id " + id + " not found");
    }
}
