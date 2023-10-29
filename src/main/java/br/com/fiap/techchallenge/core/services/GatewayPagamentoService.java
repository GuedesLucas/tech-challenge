package br.com.fiap.techchallenge.core.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.fiap.techchallenge.core.model.GatewayPagamento;
import br.com.fiap.techchallenge.infrastructure.repositories.GatewayPagamentoRepository;

@Service
public class GatewayPagamentoService {
    private final GatewayPagamentoRepository repository;

    public GatewayPagamentoService(GatewayPagamentoRepository repository) {
        this.repository = repository;
    }

    public GatewayPagamento salvarGatewayPagamento(UUID gatewayPagamentoID) {
        final var gp = new GatewayPagamento(gatewayPagamentoID);
        return repository.save(gp);
    }
}
