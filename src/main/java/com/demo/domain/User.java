package com.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "DEMO_USER")
@Data
public class User {

    @Id
    @Column(name = "NO")
    private String no;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "ACTIVE")
    private Boolean active;

    @Column(name = "DEPARTMENT_NO")
    private String departmentNo;
}
