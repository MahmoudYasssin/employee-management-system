package com.example.employeemanagementsystem.service.impl;

import com.example.employeemanagementsystem.entity.Department;
import com.example.employeemanagementsystem.entity.Employer;
import com.example.employeemanagementsystem.exception.EmployerNotFoundException;
import com.example.employeemanagementsystem.mapper.DepartmentMapper;
import com.example.employeemanagementsystem.mapper.EmployerMapper;
import com.example.employeemanagementsystem.model.dto.DepartmentDto;
import com.example.employeemanagementsystem.model.dto.EmployerPatchRequest;
import com.example.employeemanagementsystem.model.dto.EmployerRequestDto;
import com.example.employeemanagementsystem.model.dto.EmployerResponseDto;
import com.example.employeemanagementsystem.repository.DepartmentRepository;
import com.example.employeemanagementsystem.repository.EmployerRepository;
import com.example.employeemanagementsystem.service.EmployerService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)
@DisplayName("To Test Employer Services")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SpringBootTest
@ActiveProfiles("test")

class EmployerServiceImplIntegrationTest {

    @Autowired
    private EmployerService employerService;

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private DepartmentMapper departmentMapper;
    private EmployerRequestDto employerRequestDto;
   private EmployerResponseDto employerResponseDto;


    private Department department;
    @BeforeEach
    void setUp()
    {
        department = new Department();
        department.setName("AI");
        department.setDescription("We Handle AI Tools");
        department.setDepartmentManager("Ziad Abdallah");
        Department savedDepartment =
                departmentRepository.save(department);
         employerRequestDto=EmployerRequestDto.builder()
                .name("Kassem")
                .email("kassem@gmail.com")
                .password("654+-sae")
                .departmentId(savedDepartment.getId())
                .build();

        EmployerRequestDto employerRequestDto2 = EmployerRequestDto.builder()
                .name("Kassem")
                .email("kassem@gmail.com")
                .password("654+-sae")
                .departmentId(savedDepartment.getId())
                .build();

        EmployerRequestDto employerRequestDto3 = EmployerRequestDto.builder()
                .name("Kassem")
                .email("kassem@gmail.com")
                .password("654+-sae")
                .departmentId(savedDepartment.getId())
                .build();

        employerService.save(employerRequestDto);
        employerService.save(employerRequestDto2);
        employerService.save(employerRequestDto3);

        employerResponseDto = new EmployerResponseDto();
        employerResponseDto.setName("Kassem");
        employerResponseDto.setEmail("Kassem@gmail.com");
        employerResponseDto.setDept(departmentMapper.toDto(department));


    }

    @Test
    void shouldSaveEmployer_whenDepartmentExists() {

        // Arrange - save department
        // Act
        EmployerResponseDto response =
                employerService.save(employerRequestDto);

        // Assert
        assertNotNull(response);
        assertEquals("Kassem", response.getName());
        assertEquals("kassem@gmail.com", response.getEmail());

    }

    @Test
    void shouldThrowEmployerNotFoundException_whenDepartmentIdDoesNotExists() {

        employerRequestDto = EmployerRequestDto.builder()
                .name("Kassem")
                .email("kassem@gmail.com")
                .password("654+-sae")
                .departmentId(99)
                .build();


        EmployerNotFoundException exception =
                assertThrows(
                        EmployerNotFoundException.class,
                        () -> employerService.save(employerRequestDto)
                );

        assertEquals("Department Not Found", exception.getMessage());
    }

    @Test
    void shouldFindEmployer_whenEmployerIdExist()
    {
        int id=2;
        EmployerResponseDto employerResponseDto1=employerService.findById(id);
        assertEquals(employerResponseDto1.getId(),id);
    }

    @Test
    void shouldThrowEmployerNotFoundException_whenEmployeridDoesNotExist()
    {
        int id=99;
        EmployerNotFoundException exception=
                assertThrows(
                        EmployerNotFoundException.class,
                ()-> employerService.findById(id)
        );
        assertEquals(exception.getMessage(),"Employer not found with id: " + id);
    }

}


