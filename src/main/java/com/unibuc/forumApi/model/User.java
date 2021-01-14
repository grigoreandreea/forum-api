package com.unibuc.forumApi.model;


import java.sql.Date;

public class User {

    private int id;
    private String username;
    private Date date;
    private boolean gender;
    private int countryId;
    private int cityId;

    public User() {
    }

    public User(String username, Date date, boolean gender, int countryId, int cityId) {
        this.username = username;
        this.date = date;
        this.gender = gender;
        this.countryId = countryId;
        this.cityId = cityId;
    }

    public User(int id, String username, Date date, boolean gender, int countryId, int cityId) {
        this.id = id;
        this.username = username;
        this.date = date;
        this.gender = gender;
        this.countryId = countryId;
        this.cityId = cityId;
    }

    public User(int id, String username, int countryId, int cityId) {
        this.id = id;
        this.username = username;
        this.countryId = countryId;
        this.cityId = cityId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
}
