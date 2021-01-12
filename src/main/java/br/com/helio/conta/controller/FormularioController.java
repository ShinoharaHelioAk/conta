package br.com.helio.conta.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.helio.conta.form.AberturaContaForm;
import br.com.helio.conta.model.Conta;
import br.com.helio.conta.repository.ContaRepository;

@Controller
@RequestMapping("formulario")
public class FormularioController {
	@Autowired
	private ContaRepository contaRepository;
	
	@GetMapping
	public String carregaFormulario(AberturaContaForm form) {
		return "formulario";
	}
	
	@PostMapping("/novaConta")
	public String adicionaNovaConta(@Valid AberturaContaForm form, BindingResult result) {
		if (result.hasErrors())
			return "formulario";
		
		Conta conta = form.toConta();
		contaRepository.save(conta);
		
		conta = new Conta();
		
		return "formulario";
	}
}
