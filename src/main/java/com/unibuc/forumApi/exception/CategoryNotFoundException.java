package com.unibuc.forumApi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CategoryNotFoundException extends ResponseStatusException {
    public CategoryNotFoundException(int id) {
        super(HttpStatus.NOT_FOUND, "category with id " + id + " not found");
    }
}
