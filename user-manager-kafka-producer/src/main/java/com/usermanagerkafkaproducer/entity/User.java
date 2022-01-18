package com.usermanagerkafkaproducer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue
    private int id;
    private String username;

    @Column(name = "first_name", nullable = false)
    private String first_name;

    @Column(name = "last_name", nullable = false)
    private String last_name;

    @Column(name = "email", nullable = false)
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

    public int getId(){ return id; }
    public String getUsername(){ return username; }
    public String getFirstName(){ return first_name; }
    public String getFullName(){ return first_name + " " + last_name; }
    public String getLastName(){ return last_name; }
    public String getEmail(){ return email; }
    public String getPassword(){ return password; }
    public String getProfession(){ return profession; }
    public String getRole(){ return role; }
    public String getEmploymentDetails(){ return employment_details; }
}
