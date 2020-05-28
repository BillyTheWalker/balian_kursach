package com.example.demo.controller;

import com.example.demo.persistense.models.enums.UserRole;
import com.example.demo.service.CRUDService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;
import java.util.List;
import java.util.Optional;


public abstract class CRUDController<T>
{
	private final CRUDService<T> crudService;

	protected CRUDController(final CRUDService<T> crudService)
	{
		this.crudService = crudService;
	}

	@PostMapping
	public ResponseEntity<T> save(@RequestBody T t, Principal principal)
	{
		return ResponseEntity.ok(crudService.save(t));
	}

	@GetMapping("/{id}")
	public ResponseEntity<T> getOne(@PathVariable Long id, Principal principal)
	{
		return ResponseEntity.of(crudService.getOne(id));
	}

	@GetMapping
	public ResponseEntity<List<T>> getAll(Principal principal)
	{
		return ResponseEntity.ok(crudService.getAll());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable Long id, Principal principal)
	{
		crudService.delete(id);
		return ResponseEntity.ok().build();
	}


	protected Boolean isAdmin(final Principal principal)
	{
		return Optional.ofNullable(principal).map(Principal::getName).map(UserRole::valueOf).map(UserRole.ADMIN::equals)
				.orElse(Boolean.FALSE);
	}

}
