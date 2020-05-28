package com.example.demo.persistense.repository;

import com.example.demo.persistense.models.LoadStandard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LoadStandardRepository extends JpaRepository<LoadStandard, Long>
{
}
