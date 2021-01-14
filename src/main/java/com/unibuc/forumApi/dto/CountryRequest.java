package com.unibuc.forumApi.dto;

import javax.validation.constraints.NotBlank;

public class CountryRequest {
    public CountryRequest(@NotBlank String name) {
        this.name = name;
    }

    public CountryRequest() {
    }

    @NotBlank
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
