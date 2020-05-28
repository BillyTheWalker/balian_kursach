package com.example.demo.service.strategies.calculation.impl;

import com.example.demo.persistense.models.Work;
import com.example.demo.service.strategies.calculation.CalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PerStudentCalculationStrategy implements CalculationStrategy
{
	@Override
	public Double calculate(final Work work)
	{
		return work.getCoefficient() * work.getStudentsCount();
	}
}
