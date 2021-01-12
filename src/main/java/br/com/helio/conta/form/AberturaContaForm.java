package br.com.helio.conta.form;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;

import br.com.helio.conta.model.Conta;

public class AberturaContaForm {
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	@NotBlank(message = "Este campo não pode estar vazio.")
	@Length(min = 2, max = 100)
	private String nome;
	
	//Poderia ter colocado um pattern para validar o preenchimento do padrão do CPF, mas por questão de usabilidade, 
	//utilizei máscara de digitação no HTML.
	@NotBlank(message = "Este campo não pode estar vazio.")
	@Length(max = 14)
	private String cpf;
	
	@NotBlank(message = "Este campo não pode estar vazio.")
	@Length(min = 7, max = 100)
	private String email;
	
	//Aqui fiz a validação da consistência do conteúdo digitado, enquanto no HTML, por questão de usabilidade, utilizei máscara de digitação de data.
	@Length(max = 10)
	@NotBlank(message = "Este campo não pode estar vazio.")
	@Pattern(message = "Favor informar uma data válida."
			, regexp = "(^(((0[1-9]|1[0-9]|2[0-8])[\\/](0[1-9]|1[012]))|((29|30|31)[\\/](0[13578]|1[02]))|((29|30)[\\/](0[4,6,9]|11)))[\\/](19|[2-9][0-9])\\d\\d$)"
			+ "|(^29[\\/]02[\\/](19|[2-9][0-9])(00|04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)$)")
	private String dataNascimento;
	
	
	public AberturaContaForm() {}

	public AberturaContaForm(Conta conta) {
		this.nome = conta.getNome();
		this.cpf = conta.getCpf();
		this.email = conta.getEmail();
		this.dataNascimento = conta.getDataNascimento().format(formatter);
	}

	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Conta toConta() {
		Conta conta = new Conta();
		conta.setNome(nome);
		conta.setEmail(email);
		conta.setCpf(cpf);
		conta.setDataNascimento(LocalDate.parse(dataNascimento, formatter));
		return conta;
	}
}
