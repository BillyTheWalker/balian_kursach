package com.example.demo.controller;

import com.example.demo.persistense.models.LoadStandard;
import com.example.demo.persistense.repository.LoadStandardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/load")
@RequiredArgsConstructor
public class LoadStandardController
{
	private final LoadStandardRepository loadStandardRepository;

	@ResponseBody
	@GetMapping
	public List<LoadStandard> getAll(){
		return loadStandardRepository.findAll();
	}
}
