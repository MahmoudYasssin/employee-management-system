package com.example.employeemanagementsystem.service.impl;

import com.example.employeemanagementsystem.entity.Department;
import com.example.employeemanagementsystem.exception.EmployerNotFoundException;
import com.example.employeemanagementsystem.mapper.DepartmentMapper;
import com.example.employeemanagementsystem.mapper.EmployerMapper;
import com.example.employeemanagementsystem.model.dto.EmployerRequestDto;
import com.example.employeemanagementsystem.model.dto.EmployerResponseDto;
import com.example.employeemanagementsystem.repository.DepartmentRepository;
import com.example.employeemanagementsystem.repository.EmployerRepository;
import com.example.employeemanagementsystem.entity.Employer;
import com.example.employeemanagementsystem.model.dto.EmployerPatchRequest;
import com.example.employeemanagementsystem.service.EmployerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployerServiceImpl implements EmployerService {


    private final EmployerRepository employerRepository;
    private final EmployerMapper employerMapper;
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;


    @Override
    public EmployerResponseDto save(EmployerRequestDto employerRequestDto) {

        Department department = departmentRepository.findById(employerRequestDto.getDepartmentId()).orElseThrow(()->new EmployerNotFoundException("Department Not Found"));
        Employer employer=employerMapper.toEmployee(employerRequestDto);
        employer.setDepartment(department);
        return employerMapper.toEmployeeResponseDto(employerRepository.save(employer));

    }

    @Override
    public EmployerResponseDto findById(int id) {
      Employer employer=employerRepository.findById(id).orElseThrow(() -> new EmployerNotFoundException("Employer not found with id: " + id));
      return  employerMapper.toEmployeeResponseDto(employer);
    }

    @Override
    public  Page<EmployerResponseDto> findAll(Pageable pageable) {
      return employerRepository.findAll(pageable)
                .map(employer -> employerMapper.toEmployeeResponseDto(employer));

    }

    @Override
    public EmployerResponseDto partialUpdate(int id, EmployerPatchRequest patchPayload)
    {
        Employer employer=employerRepository.findById(id).orElseThrow(() -> new EmployerNotFoundException("Employer not found with id: " + id));

        if (patchPayload.getEmail()!=null)
        {
            employer.setEmail(patchPayload.getEmail());
        }
        if (patchPayload.getName()!=null)
        {
            employer.setName(patchPayload.getName());
        }
        return employerMapper.toEmployeeResponseDto(employerRepository.save(employer));
    }

    @Override
    public void deleteById(int id) {

        employerRepository.findById(id)
                .orElseThrow(()->new EmployerNotFoundException("Employer Not Found "+id));
        employerRepository.deleteById(id);

    }

    @Override
    public EmployerResponseDto fullyUpdate(int id, EmployerRequestDto employerCreateRequest) {
        Employer employer=employerRepository.findById(id).orElseThrow(() -> new EmployerNotFoundException("Employer with this id not found "+id));
      //  employer.setDepartment(employerCreateRequest.getDepartment());
        employer.setPassword(employerCreateRequest.getPassword());
        employer.setEmail(employerCreateRequest.getEmail());
        employer.setName(employerCreateRequest.getName());
       // return new EmployerResponseDto(employerRepository.save(employer));
        return employerMapper.toEmployeeResponseDto(employerRepository.save(employer));

    }

    @Override
    public List<EmployerResponseDto> findDyName(String name) {
        if(employerRepository.findDyName(name).isEmpty())
        {
            throw new EmployerNotFoundException("This name Dose not exist");
        }
       List<Employer>employerList= employerRepository.findDyName(name);
        return employerList.stream().map(employer -> employerMapper.toEmployeeResponseDto(employer)).toList();

    }

    public Sort buildSort(String sortParam) {

        if (sortParam == null || sortParam.isBlank()) {
            return Sort.by(Sort.Order.asc("id")); // default sort
        }

        List<Sort.Order> orders = new ArrayList<>();

        String[] fields = sortParam.split(",");

        for (String field : fields) {

            char direction = field.charAt(0);
            String property = field.substring(1);

            validateSortField(property);

            if (direction == '+') {
                orders.add(Sort.Order.asc(property));
            } else if (direction == '-') {
                orders.add(Sort.Order.desc(property));
            } else {
                throw new IllegalArgumentException(
                        "Invalid sort format. Use +field or -field"
                );
            }
        }

        return Sort.by(orders);
    }
    public void validateSortField(String field) {
        List<String> allowedFields = List.of(
                "id",
                "name",
                "email",
                "salary",
                "jobTitle",
                "department.name"
        );

        if (!allowedFields.contains(field)) {
            throw new IllegalArgumentException(
                    "Invalid sort field: " + field
            );
        }
    }



}
