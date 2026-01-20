package com.example.employeemanagementsystem.model.dto;

import lombok.Data;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

@Data
public class DepartmentDto {

    private String name;
    private String description;
    private String departmentManager;
}
