package br.com.helio.conta.dto;

import java.time.LocalDate;

import br.com.helio.conta.model.Conta;

public class DetalharContaDto {
	private Long id;
	private String nome;
	private String cpf;
	private String email;
	private LocalDate dataNascimento;

	public DetalharContaDto() {
	}
	
	public DetalharContaDto(Conta conta) {
		this.id = conta.getId();
		this.nome = conta.getNome();
		this.cpf = conta.getCpf();
		this.email = conta.getEmail();
		this.dataNascimento = conta.getDataNascimento();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getEmail() {
		return email;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

}
