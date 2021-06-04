package com.unibuc.forumApi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;

@ApiModel(value = "User request", description = "Required details needed to create a new User")
public class UserRequest {

    public UserRequest() {
    }

    public UserRequest(String username, int countryId, int cityId) {
        this.username = username;
        this.countryId = countryId;
        this.cityId = cityId;
    }

    public UserRequest(@NotBlank String username, @NotBlank String password, @NotNull int countryId, @NotNull int cityId, List<Integer> employers) {
        this.username = username;
        this.password = password;
        this.countryId = countryId;
        this.cityId = cityId;
        this.employers = employers;
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

    public List<Integer> getEmployers() {
        return employers;
    }

    public void setEmployers(List<Integer> employers) {
        this.employers = employers;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotBlank
    @ApiModelProperty(value = "username", required = true, notes = "The username of the User", example = "Diana", position = 1)
    private String username;
    @NotBlank
    @ApiModelProperty(value = "password", required = true, notes = "The password of the User", example = "password", position = 1)
    private String password;
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
    private List<Integer> employers;
}
