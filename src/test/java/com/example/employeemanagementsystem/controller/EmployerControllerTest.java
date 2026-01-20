package com.example.employeemanagementsystem.controller;

import com.example.employeemanagementsystem.entity.Department;
import com.example.employeemanagementsystem.mapper.DepartmentMapper;
import com.example.employeemanagementsystem.model.dto.EmployerRequestDto;
import com.example.employeemanagementsystem.model.dto.EmployerResponseDto;
import com.example.employeemanagementsystem.service.EmployerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class EmployerControllerTest {


    @Mock
    private DepartmentMapper departmentMapper;


    @Mock
    private EmployerService employerService;

    @InjectMocks
    private EmployerController employerController;


    @Test
    void saveEmployer()
    {
        EmployerRequestDto emp = EmployerRequestDto.builder()
                .name("Kassem")
                .email("Kassem@gmail.com")
                .password("54+d")
                .departmentId(2)
                .build();


        Department department = new Department();
        department.setName("AI");
        department.setDescription("We Handle AI Tools");
        department.setDepartmentManager("Ziad Abdallah");

       EmployerResponseDto employerResponseDto = new EmployerResponseDto();
       employerResponseDto.setId(1);
        employerResponseDto.setName("Kassem");
        employerResponseDto.setEmail("Kassem@gmail.com");
        employerResponseDto.setDept(departmentMapper.toDto(department));






        when(employerService.save(emp)).thenReturn(employerResponseDto);

        ResponseEntity<EmployerResponseDto> response = employerController.saveEmployer(emp);

        // Assert
        assertEquals(1, response.getBody().getId());
        assertEquals("Kassem", response.getBody().getName());
        assertEquals("Kassem@gmail.com", response.getBody().getEmail());






    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void partialUpdate() {
    }

    @Test
    void deleteEmployer() {
    }

    @Test
    void fullyUpdate() {
    }

    @Test
    void findByName() {
    }
}