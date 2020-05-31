package com.example.demo.persistense.repository;

import com.example.demo.persistense.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
	Optional<User> findByUsername(final String username);
}
