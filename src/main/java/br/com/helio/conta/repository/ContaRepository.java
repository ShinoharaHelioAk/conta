package br.com.helio.conta.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.helio.conta.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

	Page<Conta> findByCpf(String cpf, Pageable paginacao);
	
}
