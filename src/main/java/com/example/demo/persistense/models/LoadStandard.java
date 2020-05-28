package com.example.demo.persistense.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoadStandard
{
	@Id
	@GeneratedValue
	private Long id;
	@Column(unique = true)
	private String name;
	@Column(name = "_load")
	private Integer load;
}
