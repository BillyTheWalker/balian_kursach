package com.example.demo.config;

import com.example.demo.persistense.models.enums.CalculationTypes;
import com.example.demo.service.strategies.calculation.CalculationStrategy;
import com.example.demo.service.strategies.calculation.impl.DefaultCalculationStrategy;
import com.example.demo.service.strategies.calculation.impl.PerGroupCalculationStrategy;
import com.example.demo.service.strategies.calculation.impl.PerPageCalculationStrategy;
import com.example.demo.service.strategies.calculation.impl.PerStudentCalculationStrategy;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class Beans implements ApplicationContextAware
{
	private ApplicationContext applicationContext;

	@Bean("calculationStrategies")
	public Map<CalculationTypes, CalculationStrategy> calculationStrategies()
	{
		final Map<CalculationTypes, CalculationStrategy> calculationStrategies = new HashMap<>();
		calculationStrategies.put(CalculationTypes.PER_GROUP, applicationContext.getBean(PerGroupCalculationStrategy.class));
		calculationStrategies.put(CalculationTypes.PER_STUDENT, applicationContext.getBean(PerStudentCalculationStrategy.class));
		calculationStrategies.put(CalculationTypes.PER_PAGE, applicationContext.getBean(PerPageCalculationStrategy.class));
		calculationStrategies.put(CalculationTypes.DEFAULT, applicationContext.getBean(DefaultCalculationStrategy.class));
		return calculationStrategies;
	}
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}
	@Override
	public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException
	{
		this.applicationContext = applicationContext;
	}
}
