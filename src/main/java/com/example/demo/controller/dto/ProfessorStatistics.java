package com.example.demo.controller.dto;

import com.example.demo.persistense.models.Professor;
import lombok.Data;


@Data
public class ProfessorStatistics
{
	private Professor professor;
	private Double calculatedLoad;
}
