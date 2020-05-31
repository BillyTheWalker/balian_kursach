package com.example.demo.controller;

import com.example.demo.persistense.models.User;
import com.example.demo.persistense.models.Work;
import com.example.demo.persistense.models.enums.CalculationTypes;
import com.example.demo.persistense.models.enums.UserRole;
import com.example.demo.persistense.repository.UserRepository;
import com.example.demo.service.CRUDService;
import com.example.demo.service.ProfessorService;
import com.example.demo.service.WorkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/work")
public class WorkController extends CRUDController<Work>
{

	private WorkService workService;
	private ProfessorService professorService;

	public WorkController(final CRUDService<Work> crudService, final WorkService workService, final UserRepository userRepository,
			final ProfessorService professorService)
	{
		super(crudService, userRepository);
		this.workService = workService;
		this.professorService = professorService;
	}

	@PostMapping("/calculate")
	public ResponseEntity<Double> calculate(@RequestBody Work work)
	{
		return ResponseEntity.ok(workService.calculateTime(work));
	}

	@GetMapping("/calculate/{id}")
	public ResponseEntity<Double> calculate(@PathVariable Long id)
	{
		return ResponseEntity.ok(workService.calculateTime(id));
	}

	@GetMapping("/search")
	@ResponseBody
	public List<Work> search(@RequestParam(required = false) CalculationTypes calculationTypes,
			@RequestParam Long professorId)
	{
		return workService.findAllByProfessorAndCalculation(professorId, calculationTypes);
	}

	@GetMapping("/patterns")
	@ResponseBody
	public List<Work> search()
	{
		return workService.findAllByPattern(true);
	}

	@GetMapping("/categories")
	@ResponseBody
	public List<String> getCategories()
	{
		return Arrays.stream(CalculationTypes.values()).map(Enum::toString).collect(Collectors.toList());
	}

	@Override
	public List<Work> getAll(final Principal principal)
	{
		if (isAdmin(principal))
			return super.getAll(principal);
		final Long userId = getProfessorId(principal);
		return search(null, userId);
	}

	private Long getProfessorId(final Principal principal)
	{
		return Optional.ofNullable(principal).map(Principal::getName)
				.flatMap(s -> getUserRepository().findByUsername(s)).filter(user -> UserRole.PROFESSOR.equals(user.getUserRole()))
				.map(User::getId).orElseThrow(IllegalArgumentException::new);
	}

	@Override
	@PostMapping
	@ResponseBody
	public Work save(@RequestBody final Work work, final Principal principal)
	{
		work.setPattern(isAdmin(principal));
		if(!work.isPattern()){
			work.setProfessor(professorService.getOne(getProfessorId(principal)).orElseThrow(IllegalArgumentException::new));
		}
		return super.save(work, principal);
	}
}
