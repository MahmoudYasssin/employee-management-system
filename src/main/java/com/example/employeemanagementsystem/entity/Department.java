package com.example.employeemanagementsystem.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Entity
@Table(name="departments")
@Data

public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
    @Column(name = "departmentManager")
    private String departmentManager;

    @OneToMany(mappedBy = "department")
   private List<Employer> employees;



}
