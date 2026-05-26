package br.com.bakeflow.bakeflow.repository;

import br.com.bakeflow.bakeflow.model.FidelizacaoCliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FidelizacaoClienteRepository extends JpaRepository<FidelizacaoCliente, Long> {

    Optional<FidelizacaoCliente> findByClienteId(Long clienteId);
}