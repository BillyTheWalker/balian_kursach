package com.example.demo.persistense.repository;

import com.example.demo.persistense.models.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProfessorRepository extends JpaRepository<Professor,Long>
{
}
