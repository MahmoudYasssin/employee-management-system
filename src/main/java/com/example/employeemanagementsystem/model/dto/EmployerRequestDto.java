package com.example.employeemanagementsystem.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC) // make it public

public class EmployerRequestDto {


    @NotBlank(message = "Name is required")
    private String name;


    @Email
    private String email;


    private Integer departmentId;



    private String password;
}

