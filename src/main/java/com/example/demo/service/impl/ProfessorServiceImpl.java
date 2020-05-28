package com.example.demo.service.impl;

import com.example.demo.persistense.models.Professor;
import com.example.demo.service.ProfessorService;
import com.example.demo.service.WorkService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


@Service
public class ProfessorServiceImpl extends AbstractCRUDServiceImpl<Professor> implements ProfessorService
{
	private final WorkService workService;

	public ProfessorServiceImpl(WorkService workService,
			final JpaRepository<Professor, Long> repository)
	{
		super(repository);
		this.workService = workService;
	}

	@Override
	public Double calculateTime(final Long id)
	{
		return getOne(id).orElseThrow(IllegalArgumentException::new).getWorks().stream().mapToDouble(workService::calculateTime)
				.sum();
	}

	@Override
	public Double calculateTime(final Professor professor)
	{
		return calculateTime(professor.getId());
	}
}
