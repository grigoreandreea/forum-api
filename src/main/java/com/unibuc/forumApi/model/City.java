package com.unibuc.forumApi.model;

public class City {

    private int id;
    private String name;

    public City() {
    }

    public City(String name, int countryId) {
        this.name = name;
        this.countryId = countryId;
    }

    private int countryId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public City(int id, String name, int countryId) {
        this.id = id;
        this.name = name;
        this.countryId = countryId;
    }
}
