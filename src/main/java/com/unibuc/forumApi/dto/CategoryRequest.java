package com.unibuc.forumApi.dto;

import javax.validation.constraints.NotBlank;

public class CategoryRequest {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryRequest() {
    }

    public CategoryRequest(@NotBlank String name) {
        this.name = name;
    }

    @NotBlank
    private String name;
}
