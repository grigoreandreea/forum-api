package com.unibuc.forumApi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CountryNotFoundException extends ResponseStatusException {
    public CountryNotFoundException(int id) {
        super(HttpStatus.NOT_FOUND, "country with id " + id + " not found");
    }
}
