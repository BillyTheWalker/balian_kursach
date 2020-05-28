package com.example.demo.controller;

import com.example.demo.persistense.models.Professor;
import com.example.demo.service.CRUDService;
import com.example.demo.service.ProfessorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/professor")
public class ProfessorController extends CRUDController<Professor>
{
	private final ProfessorService professorService;

	public ProfessorController(final ProfessorService professorService,
			final CRUDService<Professor> crudService)
	{
		super(crudService);
		this.professorService = professorService;
	}

	@PostMapping("/calculate")
	public ResponseEntity<Double> calculate(@RequestBody Professor professor)
	{
		return ResponseEntity.ok(professorService.calculateTime(professor));
	}

	@GetMapping("/calculate/{id}")
	public ResponseEntity<Double> calculate(@PathVariable Long id)
	{
		return ResponseEntity.ok(professorService.calculateTime(id));
	}
}
