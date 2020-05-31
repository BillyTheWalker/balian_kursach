package com.example.demo.service;

import com.example.demo.persistense.models.Work;
import com.example.demo.persistense.models.enums.CalculationTypes;

import java.util.List;


public interface WorkService extends CRUDService<Work>
{
	Double calculateTime(Work work);

	Double calculateTime(Long workId);

	List<Work> findAllByPattern(final boolean pattern);

	List<Work> findAllByProfessorAndCalculation(final Long professorId, final CalculationTypes calculationType);
}
