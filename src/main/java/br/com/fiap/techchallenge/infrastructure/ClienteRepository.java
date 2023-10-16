package br.com.fiap.techchallenge.infrastructure;

import br.com.fiap.techchallenge.core.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByCpf(Long cpf);
}
