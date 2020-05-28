package com.example.demo.persistense.repository;

import com.example.demo.persistense.models.Work;
import com.example.demo.persistense.models.enums.CalculationTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface WorkRepository extends JpaRepository<Work, Long>
{
	List<Work> findAllByPattern(final boolean pattern);
	List<Work> findAllByCalculationTypeAndPattern(final CalculationTypes calculationType, final boolean pattern);
}
