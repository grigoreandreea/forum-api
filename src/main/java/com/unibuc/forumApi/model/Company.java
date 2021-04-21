package com.unibuc.forumApi.model;

import java.util.List;

public class Company {

    private int id;
    private String name;
    private List<User> employees;

    public Company() {
    }

    public Company(String name) {
        this.name = name;
    }

    public Company(String name, List<User> employees) {
        this.name = name;
        this.employees = employees;
    }

    public Company(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Company(int id, String name, List<User> employees) {
        this.id = id;
        this.name = name;
        this.employees = employees;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<User> getEmployees() {
        return employees;
    }

    public void setEmployees(List<User> employees) {
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
