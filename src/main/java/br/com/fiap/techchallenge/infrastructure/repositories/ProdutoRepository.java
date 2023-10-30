package br.com.fiap.techchallenge.infrastructure.repositories;

import br.com.fiap.techchallenge.core.Enum.CategoriaEnum;
import br.com.fiap.techchallenge.core.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByCategoria(CategoriaEnum categoria);
}
