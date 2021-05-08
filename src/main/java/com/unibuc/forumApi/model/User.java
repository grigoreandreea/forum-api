package com.unibuc.forumApi.model;


import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User {

    private int id;
    private String username;
    private String password;
    private Date date;
    private boolean gender;
    private boolean enabled;
    private int countryId;
    private int cityId;
    private List<Company> employers;
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.gender = true;
        this.countryId = 1;
        this.cityId = 1;
        this.enabled = true;
    }

    public User(String username, String password, Date date, boolean gender, int countryId, int cityId) {
        this.username = username;
        this.password = password;
        this.date = date;
        this.gender = gender;
        this.countryId = countryId;
        this.cityId = cityId;
    }

    public User(String username, Date date, boolean gender, int countryId, int cityId, List<Company> employers) {
        this.username = username;
        this.date = date;
        this.gender = gender;
        this.countryId = countryId;
        this.cityId = cityId;
        this.employers = employers;
    }

    public User(int id, String username, Date date, boolean gender, int countryId, int cityId) {
        this.id = id;
        this.username = username;
        this.date = date;
        this.gender = gender;
        this.countryId = countryId;
        this.cityId = cityId;
    }

    public User(String username, Date date, boolean gender, int countryId, int cityId) {
        this.username = username;
        this.date = date;
        this.gender = gender;
        this.countryId = countryId;
        this.cityId = cityId;
    }

    public User(int id, String username, int countryId, int cityId, List<Company> employers) {
        this.id = id;
        this.username = username;
        this.countryId = countryId;
        this.cityId = cityId;
        this.employers = employers;
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

    public List<Company> getEmployers() {
        return employers;
    }

    public void setEmployers(List<Company> employers) {
        this.employers = employers;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
