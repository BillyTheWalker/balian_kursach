package com.example.demo.persistense.repository;

import com.example.demo.persistense.models.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PropertyRepository extends JpaRepository<Property, Long>
{
	Property findByName(final String name);
}
