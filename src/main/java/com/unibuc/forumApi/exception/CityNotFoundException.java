package com.unibuc.forumApi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CityNotFoundException extends ResponseStatusException {
    public CityNotFoundException(int id) {
        super(HttpStatus.NOT_FOUND, "city with id " + id + " not found");
    }
}
