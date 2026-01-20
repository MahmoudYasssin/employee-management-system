package com.example.employeemanagementsystem.mapper;


import com.example.employeemanagementsystem.entity.Department;
import com.example.employeemanagementsystem.model.dto.DepartmentDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    Department toDepartment(DepartmentDto departmentDto);
    DepartmentDto toDto(Department departmet);

}
