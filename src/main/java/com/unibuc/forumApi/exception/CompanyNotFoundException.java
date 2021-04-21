package com.unibuc.forumApi.exception;

public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException(int id) {
        super("The company with id " + id + " doesn't exist.");
    }
}
