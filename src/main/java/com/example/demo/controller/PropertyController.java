package com.example.demo.controller;

import com.example.demo.persistense.models.Property;
import com.example.demo.service.CRUDService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/property")
public class PropertyController extends CRUDController<Property>
{
	public PropertyController(final CRUDService<Property> crudService)
	{
		super(crudService);
	}
}
