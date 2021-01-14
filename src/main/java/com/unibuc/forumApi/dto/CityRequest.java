package com.unibuc.forumApi.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CityRequest {
    @NotBlank
    private String name;
    @NotNull
    private int countryId;

    public CityRequest() {
    }

    public CityRequest(@NotBlank String name, @NotNull int countryId) {
        this.name = name;
        this.countryId = countryId;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public CityRequest(@NotBlank String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
