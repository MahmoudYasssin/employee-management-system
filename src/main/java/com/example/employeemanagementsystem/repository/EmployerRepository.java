package com.example.employeemanagementsystem.repository;

import com.example.employeemanagementsystem.entity.Employer;
import com.example.employeemanagementsystem.model.dto.EmployerResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployerRepository extends JpaRepository<Employer,Integer> {

    @Query("SELECT e FROM Employer e WHERE e.name=:name")
    List<Employer> findDyName(@Param("name") String name);


    /*

    find by name/ email /department

   find all + paginaation + sort  + search = link

   delete by name + id

     */
}
