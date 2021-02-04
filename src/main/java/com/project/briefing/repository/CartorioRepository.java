package com.project.briefing.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;


import com.project.briefing.models.Cartorio;

public interface CartorioRepository extends CrudRepository<Cartorio, String>{

	Cartorio findByUUID(UUID id);
	
}
