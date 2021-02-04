package com.project.briefing.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.briefing.models.Cartorio;
import com.project.briefing.models.Certificados;
import com.project.briefing.repository.CartorioRepository;
import com.project.briefing.repository.CertificadosRepository;

@Controller
public class CartorioController {

	private CartorioRepository cartorioRepository;
	
	private CertificadosRepository certificadosRepository;
	
	@RequestMapping("/cartorios")
	public ModelAndView listaCartorios(){
		ModelAndView modelAndView = new ModelAndView("listaCartorios");
		Iterable<Cartorio> cartorios = cartorioRepository.findAll();
		modelAndView.addObject("cartorios", cartorios);
		return modelAndView;
	}
	
	@RequestMapping(value="/cadastrarCartorio", method=RequestMethod.GET)
	public String form(){
		return "cartorio/formCartorio";
	}
	
	@RequestMapping(value="/cadastrarCartorio", method=RequestMethod.POST)
	public String form(@Validated Cartorio cartorio, BindingResult result, RedirectAttributes attributes){
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/cadastrarCartorio";
		}
		
		cartorioRepository.save(cartorio);
		attributes.addFlashAttribute("mensagem", "Cartorio cadastrado com sucesso!");
		return "redirect:/cadastrarCartorio";
	}
	
	@RequestMapping(value="/{uuid}", method=RequestMethod.GET)
	public ModelAndView detalhesCartorio(@PathVariable("uuid") UUID uuid){
		Cartorio cartorio = cartorioRepository.findByUUID(uuid);
		ModelAndView modelAndView = new ModelAndView("cartorio/detalhesEvento");
		modelAndView.addObject("cartorio", cartorio);
		
		Iterable<Certificados> certicados = certificadosRepository.findByCartorio(cartorio);
		modelAndView.addObject("certicados", certicados);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/{uuid}", method=RequestMethod.POST)
	public String detalhesCartorioPost(@PathVariable("uuid") UUID uuid, @Validated Certificados certificados,  BindingResult result, RedirectAttributes attributes){
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/{uuid}";
		}
		Cartorio cartorio = cartorioRepository.findByUUID(uuid);
		certificados.setCartorio(cartorio);
		certificadosRepository.save(certificados);
		attributes.addFlashAttribute("mensagem", "Cartorio adicionado com sucesso!");
		return "redirect:/{uuid}";
	}
	
	@RequestMapping("/deletarCartorio")
	public String deletarEvento(UUID uuid){
		Cartorio cartorio = cartorioRepository.findByUUID(uuid);
		List<Certificados> listCertificados = cartorio.getCertificados();
		if(!listCertificados.isEmpty()) {
			listCertificados.forEach(certificado -> {
			certificadosRepository.delete(certificado);
			});
		}
		cartorioRepository.delete(cartorio);
		return "redirect:/cartorios";
	}
	
	@RequestMapping("/deletarCertificado")
	public String deletarConvidado(UUID uuidCertificado){
		Certificados certicados = certificadosRepository.findByUUID(uuidCertificado);
		certificadosRepository.delete(certicados);
		Cartorio cartorio = certicados.getCartorio();
		return "redirect:/" + cartorio.getId().toString();
	}
}
