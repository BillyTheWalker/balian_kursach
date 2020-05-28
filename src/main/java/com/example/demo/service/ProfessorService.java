package com.example.demo.service;

import com.example.demo.persistense.models.Professor;


public interface ProfessorService extends CRUDService<Professor>
{
	Double calculateTime(Long id);

	Double calculateTime(Professor professor);
}
