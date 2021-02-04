package com.project.briefing.models;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.lang.NonNull;

@Entity
public class Certificados {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private UUID id;
	
	@NonNull
	@Column(name="certificados_name")
	private String name;
	
	@ManyToOne
	private Cartorio cartorio;

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Cartorio getCartorio() {
		return cartorio;
	}

	public void setCartorio(Cartorio cartorio) {
		this.cartorio = cartorio;
	}
	

}
