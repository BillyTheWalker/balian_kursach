package com.example.demo.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface CRUDService<T>
{
	Optional<T> getOne(Long id);

	List<T> getAll();

	T save(T t);

	void delete(Long id);

	void delete(T t);

	JpaRepository<T, Long> getRepository();
}
