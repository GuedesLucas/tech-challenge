package br.com.fiap.techchallenge.infrastructure;

import br.com.fiap.techchallenge.core.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
