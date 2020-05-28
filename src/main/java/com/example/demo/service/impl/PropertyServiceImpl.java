package com.example.demo.service.impl;

import com.example.demo.persistense.models.Property;
import com.example.demo.persistense.repository.PropertyRepository;
import com.example.demo.service.PropertyService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


@Service
public class PropertyServiceImpl extends AbstractCRUDServiceImpl<Property> implements PropertyService
{
	public PropertyServiceImpl(
			final JpaRepository<Property, Long> repository)
	{
		super(repository);
	}

	@Override
	public String getString(final String name)
	{
		return getRepository().findByName(name).getValue();
	}

	@Override
	public Integer getInteger(final String name)
	{
		return Integer.valueOf(getString(name));
	}

	@Override
	public PropertyRepository getRepository()
	{
		return (PropertyRepository) super.getRepository();
	}
}
