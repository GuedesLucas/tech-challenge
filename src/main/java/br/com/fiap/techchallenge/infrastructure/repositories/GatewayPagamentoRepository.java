package br.com.fiap.techchallenge.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.techchallenge.core.model.GatewayPagamento;

@Repository
public interface GatewayPagamentoRepository extends JpaRepository<GatewayPagamento, Long>{
}