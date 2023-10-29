package br.com.fiap.techchallenge.infrastructure.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.fiap.techchallenge.core.Enum.StatusPagamentoEnum;
import br.com.fiap.techchallenge.core.model.Pagamento;
import br.com.fiap.techchallenge.core.model.Pedido;

@Repository
public interface PagamentoRepository  extends JpaRepository<Pagamento, Long> {
    @Query("SELECT p FROM Pagamento p WHERE p.pedido = :pedido AND p.gateway.gatewayPagamentoID = :gatewayPagamentoID AND p.status != :status")
    Pagamento findByPedidoAndGatewayAndStatus(@Param("pedido") Pedido pedido, @Param("gatewayPagamentoID") UUID gatewayPagamentoID, @Param("status") StatusPagamentoEnum status);

    @Query("SELECT p FROM Pagamento p WHERE p.gateway.gatewayPagamentoID = :gatewayPagamentoID AND p.status = :status")
    Pagamento findByGatewayAndStatus(@Param("gatewayPagamentoID") UUID gatewayPagamentoID, @Param("status") StatusPagamentoEnum status);
}
