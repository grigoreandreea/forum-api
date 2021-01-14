package com.unibuc.forumApi.dto;

import com.unibuc.forumApi.model.City;
import com.unibuc.forumApi.model.Country;

import java.util.List;

public class CountryWithCities {
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    private Country country;
    private List<City> cities;

    public CountryWithCities(Country country, List<City> cities) {
        this.country = country;
        this.cities = cities;
    }

    public CountryWithCities(Country country) {
        this.country = country;
    }
}
