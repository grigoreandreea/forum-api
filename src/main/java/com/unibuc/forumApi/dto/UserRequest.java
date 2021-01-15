package com.unibuc.forumApi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@ApiModel(value = "User request", description = "Required details needed to create a new User")
public class UserRequest {

    public UserRequest() {
    }

    public UserRequest(String username, Date date, boolean gender, int countryId, int cityId) {
        this.username = username;
        this.date = date;
        this.gender = gender;
        this.countryId = countryId;
        this.cityId = cityId;
    }

    public UserRequest(String username, int countryId, int cityId) {
        this.username = username;
        this.countryId = countryId;
        this.cityId = cityId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    @NotBlank
    @ApiModelProperty(value = "username", required = true, notes = "The username of the User", example = "Diana", position = 1)
    private String username;
    @ApiModelProperty(value = "date", required = false, notes = "The date of birth of the User", example = "1993-05-05", position = 1)
    private Date date;
    @ApiModelProperty(value = "gender", required = false, notes = "The gender of the User. (0-male, 1-female)", example = "1", position = 1)
    private boolean gender;
    @NotNull
    @ApiModelProperty(value = "countryId", required = true, notes = "The id of the Country", example = "2", position = 1)
    private int countryId;
    @NotNull
    @ApiModelProperty(value = "cityId", required = true, notes = "The id of the City", example = "2", position = 1)
    private int cityId;
}
