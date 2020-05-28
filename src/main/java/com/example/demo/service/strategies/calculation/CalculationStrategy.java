package com.example.demo.service.strategies.calculation;

import com.example.demo.persistense.models.Work;


public interface CalculationStrategy
{
	Double calculate(Work work);
}
