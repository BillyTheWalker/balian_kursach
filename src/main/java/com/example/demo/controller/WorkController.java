package com.example.demo.controller;

import com.example.demo.persistense.models.Work;
import com.example.demo.persistense.models.enums.CalculationTypes;
import com.example.demo.service.CRUDService;
import com.example.demo.service.WorkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/work")
public class WorkController extends CRUDController<Work>
{

	private WorkService workService;

	public WorkController(final CRUDService<Work> crudService, final WorkService workService)
	{
		super(crudService);
		this.workService = workService;
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
	public ResponseEntity<List<Work>> search(@RequestParam(required = false) CalculationTypes calculationTypes,
			@RequestParam boolean isPattern)
	{
		if (calculationTypes == null)
			return ResponseEntity.ok(workService.findAllByPattern(isPattern));
		return ResponseEntity.ok(workService.findAllByCalculationTypeAndPattern(calculationTypes, isPattern));
	}

	@Override
	public ResponseEntity<List<Work>> getAll(final Principal principal)
	{
		if (isAdmin(principal))
			return super.getAll(principal);
		return search(null, false);
	}

	@Override
	public ResponseEntity<Work> save(final Work work, final Principal principal)
	{
		work.setPattern(isAdmin(principal));
		return super.save(work, principal);
	}
}
