package com.example.demo.service.strategies.calculation.impl;

import com.example.demo.persistense.models.Work;
import com.example.demo.service.strategies.calculation.CalculationStrategy;
import org.springframework.stereotype.Service;


@Service
public class DefaultCalculationStrategy implements CalculationStrategy
{
	@Override
	public Double calculate(final Work work)
	{
		return work.getCoefficient();
	}
}
