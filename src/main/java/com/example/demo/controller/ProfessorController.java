package com.example.demo.controller;

import com.example.demo.controller.dto.ProfessorStatistics;
import com.example.demo.persistense.models.Professor;
import com.example.demo.persistense.models.enums.UserRole;
import com.example.demo.persistense.repository.UserRepository;
import com.example.demo.service.CRUDService;
import com.example.demo.service.ProfessorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/professor")
public class ProfessorController extends CRUDController<Professor>
{
	private final ProfessorService professorService;
	private final PasswordEncoder passwordEncoder;

	public ProfessorController(final ProfessorService professorService,
			final CRUDService<Professor> crudService, final UserRepository userRepository,
			final PasswordEncoder passwordEncoder)
	{
		super(crudService, userRepository);
		this.professorService = professorService;
		this.passwordEncoder = passwordEncoder;
	}

	@PostMapping("/calculate")
	@ResponseBody
	public Double calculate(@RequestBody Professor professor)
	{
		return professorService.calculateTime(professor);
	}

	@GetMapping("/calculate/{id}")
	@ResponseBody
	public Double calculate(@PathVariable Long id)
	{
		return professorService.calculateTime(id);
	}

	@GetMapping("/calculate")
	@ApiOperation(notes = "Returns all professors with actual load", value = "Get professors statistics")
	@ResponseBody
	public List<ProfessorStatistics> calculate()
	{
		return professorService.getAll().stream().map(professor -> {
			ProfessorStatistics professorStatistics = new ProfessorStatistics();
			professor.setPassword(null);
			professorStatistics.setProfessor(professor);
			professorStatistics.setCalculatedLoad(professorService.calculateTime(professor.getId()));
			return professorStatistics;
		}).collect(Collectors.toList());
	}

	@Override
	@PostMapping
	@ResponseBody
	public Professor save(@RequestBody final Professor professor, final Principal principal)
	{
		professor.setUserRole(UserRole.PROFESSOR);
		professor.setPassword(passwordEncoder.encode(professor.getPassword()));
		Professor saved = super.save(professor, principal);
		saved.setPassword(null);
		return saved;
	}
}
