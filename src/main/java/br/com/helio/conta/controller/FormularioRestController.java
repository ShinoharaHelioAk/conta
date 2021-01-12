package br.com.helio.conta.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.helio.conta.dto.DetalharContaDto;
import br.com.helio.conta.form.AberturaContaForm;
import br.com.helio.conta.dto.ListaContasDto;
import br.com.helio.conta.model.Conta;
import br.com.helio.conta.repository.ContaRepository;

@RestController
@RequestMapping("api/contas")
public class FormularioRestController {
	@Autowired
	private ContaRepository contaRepository;
	
	@GetMapping
	public Page<ListaContasDto> listarContasAbertas(//@RequestParam(required = false) String cpf
		//, @PageableDefault(direction = Direction.ASC, sort = "id", page = 0, size = 10) Pageable paginacao
		@PageableDefault(direction = Direction.ASC, sort = "id", page = 0, size = 10) Pageable paginacao
	) {
		
		Page<Conta> contas = null;
		//if (cpf == null) {
			contas = contaRepository.findAll(paginacao);
		//} else {
			//contas = contaRepository.findByCpf(cpf, paginacao);
		//}
		
		return ListaContasDto.converter(contas);
	}
	
	@PostMapping("novaConta")
	public ResponseEntity<AberturaContaForm> cadastrarConta(
			@RequestBody @Valid AberturaContaForm form
			, UriComponentsBuilder uriBuilder
	) {
		Conta conta = form.toConta();
		contaRepository.save(conta);
		URI uri = uriBuilder.path("/api/contas/{id}").buildAndExpand(conta.getId()).toUri();
		return ResponseEntity.created(uri).body(new AberturaContaForm(conta));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetalharContaDto> detalharConta(@PathVariable Long id) {
		Optional<Conta> conta = contaRepository.findById(id);
		
		if(conta.isPresent()) {
			return ResponseEntity.ok(new DetalharContaDto(conta.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
}
