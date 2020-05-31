package com.example.demo.controller;

import com.example.demo.persistense.models.User;
import com.example.demo.persistense.models.enums.UserRole;
import com.example.demo.persistense.repository.UserRepository;
import com.example.demo.service.CRUDService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;
import java.util.Optional;


public abstract class CRUDController<T>
{
	private final CRUDService<T> crudService;
	private final UserRepository userRepository;

	protected CRUDController(final CRUDService<T> crudService,
			final UserRepository userRepository)
	{
		this.crudService = crudService;
		this.userRepository = userRepository;
	}

	@PostMapping
	@ResponseBody
	public T save(@RequestBody T t, Principal principal)
	{
		return crudService.save(t);
	}

	@GetMapping("/{id}")
	@ResponseBody
	public T getOne(@PathVariable Long id, Principal principal)
	{
		return crudService.getOne(id).orElseThrow(IllegalArgumentException::new);
	}

	@GetMapping
	@ResponseBody
	public List<T> getAll(Principal principal)
	{
		return crudService.getAll();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable Long id, Principal principal)
	{
		crudService.delete(id);
		return ResponseEntity.ok().build();
	}


	protected Boolean isAdmin(final Principal principal)
	{
		return Optional.ofNullable(principal).map(Principal::getName).flatMap(userRepository::findByUsername).map(User::getUserRole).map(UserRole.ADMIN::equals)
				.orElse(Boolean.FALSE);
	}

	protected UserRepository getUserRepository()
	{
		return userRepository;
	}
}
