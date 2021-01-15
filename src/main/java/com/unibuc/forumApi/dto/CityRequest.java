package com.unibuc.forumApi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel(value = "City request", description = "Required details needed to create a new City")
public class CityRequest {
    @NotBlank
    @ApiModelProperty(value = "name", required = true, notes = "The name of the City", example = "New York", position = 1)
    private String name;
    @NotNull
    @ApiModelProperty(value = "countryId", required = true, notes = "The id of the Country", example = "1", position = 1)
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
