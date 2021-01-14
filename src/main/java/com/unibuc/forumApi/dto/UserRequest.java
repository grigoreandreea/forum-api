package com.unibuc.forumApi.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;

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
    private String username;
    private Date date;
    private boolean gender;
    @NotNull
    private int countryId;
    @NotNull
    private int cityId;
}
