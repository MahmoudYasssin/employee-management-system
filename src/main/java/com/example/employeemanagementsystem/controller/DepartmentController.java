package com.example.employeemanagementsystem.controller;

import com.example.employeemanagementsystem.model.dto.DepartmentDto;
import com.example.employeemanagementsystem.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/departments")
public class DepartmentController {

    private final DepartmentService departmentService;


    @PostMapping
    public DepartmentDto createNewDepartment(@RequestBody DepartmentDto departmentDto)
    {
        return departmentService.createDepartment(departmentDto);
    }
}
