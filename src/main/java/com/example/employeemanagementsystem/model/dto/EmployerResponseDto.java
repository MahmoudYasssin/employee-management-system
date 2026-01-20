package com.example.employeemanagementsystem.model.dto;

import com.example.employeemanagementsystem.entity.Employer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployerResponseDto {


    private int id;
    private String name;
    private String email;
    private DepartmentDto dept;

   /* public EmployerResponseDto(Employer employer)
    {
        this.id=employer.getId();
        this.name=employer.getName();
        this.email=employer.getEmail();
        this.department=employer.getDepartment();
    }*/

}
