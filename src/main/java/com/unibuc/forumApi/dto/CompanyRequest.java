package com.unibuc.forumApi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "Company request", description = "Required details needed to create a new Company")
public class CompanyRequest {

    @NotBlank
    @ApiModelProperty(value = "name", required = true, notes = "The name of the Company", example = "Ropharma SRL", position = 1)
    private String name;

    public CompanyRequest() {}

    public CompanyRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
