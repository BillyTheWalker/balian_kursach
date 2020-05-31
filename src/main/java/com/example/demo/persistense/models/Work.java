package com.example.demo.persistense.models;

import com.example.demo.persistense.models.enums.CalculationTypes;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Work
{
	@Id
	@GeneratedValue
	private Long id;

	@JsonIgnore
	@ManyToOne
	private Professor professor;
	private String name;
	private String description;
	private Double coefficient;
	private Integer groupsCount;
	private Integer studentsCount;
	private Integer pages;
	private Integer authors;
	private CalculationTypes calculationType;
	private boolean pattern;
}
