package com.unibuc.forumApi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "Category request", description = "Required details needed to create a new Category")
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
    @ApiModelProperty(value = "name", required = true, notes = "The name of the Category", example = "Lifestyle", position = 1)
    private String name;
}
