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

	List<Work> findAllByCalculationTypeAndProfessor_Id(final CalculationTypes calculationType,
			final Long professor_id);

	List<Work> findAllByProfessor_Id(final Long professor_id);
}
