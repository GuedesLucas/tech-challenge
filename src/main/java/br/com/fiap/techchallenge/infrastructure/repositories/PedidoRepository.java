package br.com.fiap.techchallenge.infrastructure.repositories;

import br.com.fiap.techchallenge.core.Enum.StatusPedidoEnum;
import br.com.fiap.techchallenge.core.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findAllByStatus(StatusPedidoEnum status);
}
