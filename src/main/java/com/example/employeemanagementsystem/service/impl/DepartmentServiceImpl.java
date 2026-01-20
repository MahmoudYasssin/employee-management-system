package com.example.employeemanagementsystem.service.impl;

import com.example.employeemanagementsystem.entity.Department;
import com.example.employeemanagementsystem.mapper.DepartmentMapper;
import com.example.employeemanagementsystem.model.dto.DepartmentDto;
import com.example.employeemanagementsystem.repository.DepartmentRepository;
import com.example.employeemanagementsystem.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;


    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        Department departmet=departmentMapper.toDepartment(departmentDto);
        departmentRepository.save(departmet);
        return departmentMapper.toDto(departmet);

    }
}
