package com.example.demo.service;

import com.example.demo.persistense.models.Property;


public interface PropertyService extends CRUDService<Property>
{
	String getString(String name);

	Integer getInteger(String name);
}
