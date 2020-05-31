package com.example.demo.service.impl;

import com.example.demo.persistense.models.Work;
import com.example.demo.persistense.models.enums.CalculationTypes;
import com.example.demo.persistense.repository.WorkRepository;
import com.example.demo.service.WorkService;
import com.example.demo.service.strategies.calculation.CalculationStrategy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class WorkServiceImpl extends AbstractCRUDServiceImpl<Work> implements WorkService
{
	private final Map<CalculationTypes, CalculationStrategy> calculationStrategies;

	public WorkServiceImpl(Map<CalculationTypes, CalculationStrategy> calculationStrategies,
			final JpaRepository<Work, Long> repository)
	{
		super(repository);
		this.calculationStrategies = calculationStrategies;
	}

	@Override
	public Double calculateTime(final Work work)
	{
		return calculateTime(work.getId());
	}

	@Override
	public Double calculateTime(final Long workId)
	{
		Work work = getOne(workId).orElseThrow(() -> new IllegalArgumentException("No work found for id " + workId));
		return calculationStrategies.get(work.getCalculationType()).calculate(work);
	}

	@Override
	public List<Work> findAllByPattern(final boolean pattern)
	{
		return getRepositoryInternal().findAllByPattern(pattern);
	}

	@Override
	public List<Work> findAllByProfessorAndCalculation(final Long professorId, final CalculationTypes calculationType)
	{
		return getRepositoryInternal().findAllByCalculationTypeAndProfessor_Id(calculationType, professorId);
	}

	private WorkRepository getRepositoryInternal()
	{
		return (WorkRepository) getRepository();
	}
}
