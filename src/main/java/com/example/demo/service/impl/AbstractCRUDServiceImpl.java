package com.example.demo.service.impl;

import com.example.demo.service.CRUDService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public abstract class AbstractCRUDServiceImpl<T> implements CRUDService<T>
{

	private final JpaRepository<T, Long> repository;

	protected AbstractCRUDServiceImpl(final JpaRepository<T, Long> repository)
	{
		this.repository = repository;
	}

	@Override
	public Optional<T> getOne(final Long id)
	{
		return getRepository().findById(id);
	}

	@Override
	public List<T> getAll()
	{
		return getRepository().findAll();
	}

	@Override
	public T save(final T t)
	{
		return getRepository().save(t);
	}

	@Override
	public void delete(final Long id)
	{
		getRepository().deleteById(id);
	}

	@Override
	public void delete(final T t)
	{
		getRepository().delete(t);
	}

	@Override
	public JpaRepository<T, Long> getRepository()
	{
		return repository;
	}
}
