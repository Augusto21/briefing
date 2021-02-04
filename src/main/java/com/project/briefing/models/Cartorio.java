package com.project.briefing.models;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.lang.NonNull;
@Entity
public class Cartorio implements Serializable{

	private static final long serialVersionUID = -4542582987170791772L;

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private UUID id;
	
	@NonNull
	@Column(name="cartorio_name")
	private String name;
	
	@NonNull
	@Column(name="cartorio_endereco")
	private String endereco;
	
	@OneToMany
	private List<Certificados> certificados;

	public UUID getId() {
		return id;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public List<Certificados> getCertificados() {
		return certificados;
	}

	public void setCertificados(List<Certificados> certificados) {
		this.certificados = certificados;
	}
	
	
	
}
