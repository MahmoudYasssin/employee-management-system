package com.example.employeemanagementsystem.service;

import com.example.employeemanagementsystem.model.dto.EmployerRequestDto;
import com.example.employeemanagementsystem.model.dto.EmployerResponseDto;
import com.example.employeemanagementsystem.model.dto.EmployerPatchRequest;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface EmployerService {

    EmployerResponseDto save(EmployerRequestDto employer);
    EmployerResponseDto findById(int id);
    Page<EmployerResponseDto> findAll(Pageable pageable);
    EmployerResponseDto partialUpdate(int id, EmployerPatchRequest stringObjectMap);
    void deleteById(int id);
    EmployerResponseDto fullyUpdate(int id, EmployerRequestDto employerCreateRequest);
    List<EmployerResponseDto> findDyName(String name);
    public Sort buildSort(String sortParam);
    public void validateSortField(String field);
}
