package com.example.employeemanagementsystem.mapper;

import com.example.employeemanagementsystem.entity.Employer;
import com.example.employeemanagementsystem.model.dto.EmployerRequestDto;
import com.example.employeemanagementsystem.model.dto.EmployerResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployerMapper {

    @Mapping(source = "department",target = "dept")
    EmployerResponseDto toEmployeeResponseDto(Employer employer);

    Employer toEmployee(EmployerRequestDto employerRequestDto);
}

