package com.project.briefing.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.project.briefing.models.Cartorio;
import com.project.briefing.models.Certificados;

public interface CertificadosRepository extends CrudRepository<Certificados, String>{

	Certificados findByUUID(UUID id);

	Iterable<Certificados> findByCartorio(Cartorio cartorio);

}

