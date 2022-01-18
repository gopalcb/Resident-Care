package com.emailmanager.emailmanagerspringbootkafka.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class User {
    private int id;
    private String username;
    private String first_name;
    private String last_name;
    private String email;

    private Integer resident_id;
    private Integer building_id;
    private Integer house_id;
    private Integer apartment_id;

    private String password;
    private String profession;
    private String role; // manager, customer-service, cashier, inspector, property-viewer, visitor, tenant
    private String employment_details;

    private boolean is_employee;
    private boolean is_admin;
    private boolean is_deleted;

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

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getResident_id() {
        return resident_id;
    }

    public void setResident_id(Integer resident_id) {
        this.resident_id = resident_id;
    }

    public Integer getBuilding_id() {
        return building_id;
    }

    public void setBuilding_id(Integer building_id) {
        this.building_id = building_id;
    }

    public Integer getHouse_id() {
        return house_id;
    }

    public void setHouse_id(Integer house_id) {
        this.house_id = house_id;
    }

    public Integer getApartment_id() {
        return apartment_id;
    }

    public void setApartment_id(Integer apartment_id) {
        this.apartment_id = apartment_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmployment_details() {
        return employment_details;
    }

    public void setEmployment_details(String employment_details) {
        this.employment_details = employment_details;
    }

    public boolean isIs_employee() {
        return is_employee;
    }

    public void setIs_employee(boolean is_employee) {
        this.is_employee = is_employee;
    }

    public boolean isIs_admin() {
        return is_admin;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }

    public boolean isIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }
}
