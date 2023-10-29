package br.com.fiap.techchallenge.infrastructure.repositories;

import br.com.fiap.techchallenge.core.model.ItemPedido;
import br.com.fiap.techchallenge.core.model.ItemPedidoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, ItemPedidoId> {
}
