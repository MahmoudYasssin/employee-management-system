package com.example.employeemanagementsystem.service.impl;

import com.example.employeemanagementsystem.entity.Department;
import com.example.employeemanagementsystem.entity.Employer;
import com.example.employeemanagementsystem.exception.EmployerNotFoundException;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("To Test Employer Services")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class EmployerServiceImplTest
{

    @Mock
    private EmployerRepository employerRepository;
    @Mock
    private DepartmentRepository departmentRepository;
    @Mock
    private EmployerMapper employerMapper;

    @Mock
    private EmployerPatchRequest employerPatchRequest;

    @Mock
    private Pageable pageable;

    @InjectMocks
    private EmployerServiceImpl employerService;

    private EmployerRequestDto employerRequestDto;
    private EmployerResponseDto employerResponseDto;
    private Department department;
    private Employer employer;
    private DepartmentDto departmentDto;
    private int id;



    @BeforeEach
    void setUp() {
        department = new Department();
        department.setDepartmentManager("Ziad Abdallah");
        department.setDescription("We Handel Ai Tools");
        department.setName("Ai");

       employerRequestDto = EmployerRequestDto.builder()
                .departmentId(2)
                .email("Kassem@gmail.com")
                .name("Kassem")
                .password("654+-sae")
                .build();

        employer = new Employer();
        employer.setName("Ali");
        employer.setEmail("Ali@email.com");
        employer.setPassword("654+-sae");
        employer.setDepartment(department);

        employerResponseDto = new EmployerResponseDto();
        employerResponseDto.setName("Kassem");
        employerResponseDto.setEmail("Kassem@gmail.com");
        departmentDto=new DepartmentDto();
        departmentDto.setDescription("ddd");
        departmentDto.setDepartmentManager("OSOS");
        departmentDto.setName("AI");
        employerResponseDto.setDept(departmentDto);

        id=2;
        employerPatchRequest=new EmployerPatchRequest();




    }

    @Test
   /// @DisplayName("To Test Create and Save Employer")
    void saveEmployer()
    {

        when(departmentRepository.findById(employerRequestDto.getDepartmentId()))
                .thenReturn(Optional.of(department));

        when(employerMapper.toEmployee(employerRequestDto))
                .thenReturn(employer);
        when(employerRepository.save(employer))
                .thenReturn(employer);
        when(employerMapper.toEmployeeResponseDto(employer))
                .thenReturn(employerResponseDto);


        EmployerResponseDto result = employerService.save(employerRequestDto);

        assertEquals(employerResponseDto, result);

    }


   // @DisplayName("To Test Exist Employer By Id")


    @Test
    void shouldFindEmployer_whenEmployerIdExists()
    {
        when(employerRepository.findById(id)).thenReturn(Optional.of(employer));
        when(employerMapper.toEmployeeResponseDto(employer)).thenReturn(employerResponseDto);
        EmployerResponseDto result = employerService.findById(id);

        assertEquals(employerResponseDto, result);
    }
    @Test
    //@DisplayName("To Test Not Exist Employer By Id")
    void shouldThrowEmployerNotFoundException_whenEmployerIdDoesNotExist()
    {
        when(employerRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(
                EmployerNotFoundException.class, // or EntityNotFoundException
                () -> employerService.findById(id)
        );
    }






    @Test
 //   @DisplayName("To Test Find All Employers")
    void shouldFindAllEmployers() {

        // given
        Pageable pageable = PageRequest.of(0, 5);

        Page<Employer> employerPage =
                new PageImpl<>(List.of(employer), pageable, 1);

        when(employerRepository.findAll(pageable)).thenReturn(employerPage);
        when(employerMapper.toEmployeeResponseDto(employer))
                .thenReturn(employerResponseDto);

        // when
        Page<EmployerResponseDto> result = employerService.findAll(pageable);

        // then
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(
                employerResponseDto.getName(),
                result.getContent().get(0).getName()
        );



    }

        @Test
            //  @DisplayName("Test Delete Exist Employer With Id")
        void shouldDeleteEmployerById_whenEmployerIdExists()
        {
            when(employerRepository.findById(id)).thenReturn(Optional.of(employer));
            employerService.deleteById(id);

            verify(employerRepository).findById(id);
            verify(employerRepository).deleteById(id);

        }
        @Test
            //   @DisplayName("Test Delete Not Exist Employer")
        void shouldThrowEmployerNotFoundException_whenEmployerIdDoseNotExist()
        {

            when(employerRepository.findById(id)).thenReturn(Optional.empty());
            EmployerNotFoundException exception=assertThrows(
                    EmployerNotFoundException.class,()->employerService.deleteById(id)
            );
            assertEquals(exception.getMessage(),"Employer Not Found "+id);
            verify(employerRepository,never()).deleteById(id);
            verify(employerRepository,times(1)).findById(id);


        }





    @Test
    @DisplayName("Test Find By Name For Exist Employer")
    void findExistEmployerByName()
    {
        String name="Ali";
        List<Employer>employerList = List.of(employer);
        when( employerRepository.findDyName(name)).thenReturn(employerList);
        when(employerMapper.toEmployeeResponseDto(employer)).thenReturn(employerResponseDto);

        List<EmployerResponseDto> employerResponseDtos=employerService.findDyName(name);
        assertNotNull(employerResponseDtos);

    }

    @Test
    @DisplayName("Test Find By Name For Not Exist Employer")
    void shouldThrowEmployerNotFoundException_whenEmployerNameDoseNotExist()
    {
        String name="Ali";
        List<Employer> employerList= List.of();
        when( employerRepository.findDyName(name)).thenReturn(employerList);
      EmployerNotFoundException exception=
        assertThrows(EmployerNotFoundException.class,
                ()->employerService.findDyName(name));

       assertEquals(exception.getMessage(),"This name Dose not exist");

    }

    @Test
    @DisplayName("Update Employer Name If only Exist")
    void whenEmployerNameAndIdAreExist_shouldUpdateIt()
    {
        employerPatchRequest=new EmployerPatchRequest();
        employerPatchRequest.setName("Kassem");

        //List<Employer>employerList=List.of(employer);
        when(employerRepository.findById(id)).thenReturn(Optional.of(employer));
        when(employerRepository.save(employer)).thenReturn(employer);
        when(employerMapper.toEmployeeResponseDto(employer)).thenReturn(employerResponseDto);

        EmployerResponseDto employerResponseDto1=employerService.partialUpdate(id,employerPatchRequest);

        assertEquals(employerResponseDto1.getName(),employerPatchRequest.getName());

    }

    @Test
    @DisplayName("Update Employer Email If only Exist")
    void whenEmployerEmailAndIdAreExist_shouldUpdateIt()
    {
        employerPatchRequest.setEmail("Kassem@gmail.com");

        when(employerRepository.findById(id)).thenReturn(Optional.of(employer));
        when(employerRepository.save(employer)).thenReturn(employer);
        when(employerMapper.toEmployeeResponseDto(employer)).thenReturn(employerResponseDto);

        EmployerResponseDto employerResponseDto1=employerService.partialUpdate(id,employerPatchRequest);

        assertEquals(employerResponseDto1.getEmail(),employerPatchRequest.getEmail());
    }

    @Test
    @DisplayName("Update Employer Email And Name If only Exist")
    void whenEmployerEmailAndNameAndIdAreExist_shouldUpdateIt()
    {
        employerPatchRequest.setEmail("Kassem@gmail.com");
        employerPatchRequest.setName("Kassem");


        when(employerRepository.findById(id)).thenReturn(Optional.of(employer));
        when(employerRepository.save(employer)).thenReturn(employer);
        when(employerMapper.toEmployeeResponseDto(employer)).thenReturn(employerResponseDto);
        EmployerResponseDto employerResponseDto1=employerService.partialUpdate(id,employerPatchRequest);


        assertEquals(employerResponseDto1.getEmail(),employerPatchRequest.getEmail());
        assertEquals(employerResponseDto1.getName(),employerPatchRequest.getName());
    }

    @Test
    @DisplayName("Update Employer Email Or Name If Id Does Not Exist")
    void whenEmployerEmailOrNameExistAndIdDOesNotExist_shouldThrowEmployerNotFoundException()
    {

        when(employerRepository.findById(id)).thenReturn(Optional.empty());
        EmployerNotFoundException exception= assertThrows(EmployerNotFoundException.class,
                ()->employerService.partialUpdate(id,employerPatchRequest)
        );
        assertEquals(exception.getMessage(),"Employer not found with id: " + id);


    }

    @Test
    @DisplayName("Update All Employer Data And Id Not Exists")
    void whenEmployerIdDoseNotExists_shouldUpdateEmployerData()
    {
        when(employerRepository.findById(id)).thenReturn(Optional.empty());
        EmployerNotFoundException exception=assertThrows(
                EmployerNotFoundException.class,
                ()->employerService.fullyUpdate(id,employerRequestDto)
        );
        assertEquals(exception.getMessage(),"Employer with this id not found "+id);
        verify(employerRepository,times(1)).findById(id);
        verifyNoInteractions(employerMapper);
        verify(employerRepository, never()).save(any());

    }

    @Test
    @DisplayName("Update All Employer Data And Id Exists")
    void whenEmployerIdExists_shouldUpdateEmployerData()
    {
        when(employerRepository.findById(id)).thenReturn(Optional.of(employer));
        when(employerRepository.save(employer)).thenReturn(employer);
        when(employerMapper.toEmployeeResponseDto(employer)).thenReturn(employerResponseDto);

        employerResponseDto=employerService.fullyUpdate(id,employerRequestDto);
        assertEquals(employerRequestDto.getName(),employerResponseDto.getName());

        verify(employerRepository).findById(id);
        verify(employerMapper).toEmployeeResponseDto(employer);
        verify(employerRepository).save(employer);

    }



}


