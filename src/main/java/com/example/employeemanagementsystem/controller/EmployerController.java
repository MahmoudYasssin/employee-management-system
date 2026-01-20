package com.example.employeemanagementsystem.controller;

import com.example.employeemanagementsystem.model.dto.EmployerRequestDto;
import com.example.employeemanagementsystem.model.dto.EmployerResponseDto;
import com.example.employeemanagementsystem.model.dto.EmployerPatchRequest;
import com.example.employeemanagementsystem.service.EmployerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/employers")
@RequiredArgsConstructor
public class EmployerController {

    private final EmployerService employerService;


    @PostMapping // dto validation
    public ResponseEntity<EmployerResponseDto> saveEmployer(@Valid @RequestBody EmployerRequestDto employer) // how to do dto and map it to the entity useing map trakt
    {
        EmployerResponseDto dto = employerService.save(employer);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<EmployerResponseDto> findById(@PathVariable("id") int id )
    {
        EmployerResponseDto employerDto=employerService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(employerDto);
    }
    @GetMapping // modify findall with pagination
    public ResponseEntity<Page<EmployerResponseDto>> findAll(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size,
                                                             @RequestParam(required = false) String sort)
    {
        Pageable pageable = PageRequest.of(page, size,employerService.buildSort(sort));

        Page<EmployerResponseDto>employerDtos= employerService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(employerDtos);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EmployerResponseDto> partialUpdate(@PathVariable int id, @RequestBody EmployerPatchRequest patchPayload)
    {
        EmployerResponseDto employerDto= employerService.partialUpdate(id,patchPayload);
        return ResponseEntity.status(HttpStatus.OK).body(employerDto);
    }
//correct this apis to perfect way

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployer(@PathVariable int id) {
        employerService.deleteById(id);
        return ResponseEntity.noContent().build(); // 204
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployerResponseDto>fullyUpdate(@PathVariable int id, @Valid@RequestBody EmployerRequestDto employerCreateRequest)
    {
        EmployerResponseDto employerDto=employerService.fullyUpdate(id,employerCreateRequest);
        return  ResponseEntity.status(HttpStatus.OK).body(employerDto);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<EmployerResponseDto>> findByName(@PathVariable(name="name")String name)
    {
        return ResponseEntity.status(HttpStatus.OK).body(employerService.findDyName(name));
    }



}
