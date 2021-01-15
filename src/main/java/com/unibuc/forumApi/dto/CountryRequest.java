package com.unibuc.forumApi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "Country request", description = "Required details needed to create a new Country")
public class CountryRequest {
    public CountryRequest(@NotBlank String name) {
        this.name = name;
    }

    public CountryRequest() {
    }

    @NotBlank
    @ApiModelProperty(value = "name", required = true, notes = "The name of the Country", example = "America", position = 1)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
