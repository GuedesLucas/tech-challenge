package br.com.fiap.techchallenge.core.services;

import br.com.fiap.techchallenge.core.DTO.CadastrarProdutoRequestDTO;
import br.com.fiap.techchallenge.core.DTO.ProdutoResponseDTO;
import br.com.fiap.techchallenge.core.Enum.CategoriaEnum;
import br.com.fiap.techchallenge.core.exception.ProdutoNaoEncontradoExeception;
import br.com.fiap.techchallenge.core.model.Produto;
import br.com.fiap.techchallenge.infrastructure.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public ProdutoResponseDTO cadastrarProduto(CadastrarProdutoRequestDTO cadastrarProdutoRequestDTO){
        Produto produto = toProduto(cadastrarProdutoRequestDTO);
        produtoRepository.save(produto);
        return toProdutoResponseDTO(produto);
    }

    public ProdutoResponseDTO editarProduto(String id, CadastrarProdutoRequestDTO cadastrarProdutoRequestDTO) throws ProdutoNaoEncontradoExeception {
        Optional<Produto> produtoOptional = produtoRepository.findById(Long.valueOf(id));

        if(produtoOptional.isEmpty()){
            throw new ProdutoNaoEncontradoExeception();
        }

        Produto produto = produtoOptional.get();
        produto.setNome(cadastrarProdutoRequestDTO.getNome());
        produto.setDescricao(cadastrarProdutoRequestDTO.getDescricao());
        produto.setCategoria(CategoriaEnum.valueOf(cadastrarProdutoRequestDTO.getCategoria()));
        produto.setPreco(new BigDecimal(cadastrarProdutoRequestDTO.getPreco()));
        produto.setUpdateAt(LocalDateTime.now());

        produtoRepository.save(produto);
        return toProdutoResponseDTO(produto);
    }

    public ProdutoResponseDTO buscarProduto(String id) throws ProdutoNaoEncontradoExeception {
        Optional<Produto> produtoOptional = produtoRepository.findById(Long.valueOf(id));

        if(produtoOptional.isEmpty()){
            throw new ProdutoNaoEncontradoExeception();
        }

        return toProdutoResponseDTO(produtoOptional.get());
    }

    public void excluirProduto(String id) throws ProdutoNaoEncontradoExeception {
        Optional<Produto> produtoOptional = produtoRepository.findById(Long.valueOf(id));

        if(produtoOptional.isEmpty()){
            throw new ProdutoNaoEncontradoExeception();
        }

        produtoRepository.delete(produtoOptional.get());
    }

    public List<ProdutoResponseDTO> listarProdutosPorCategoria(String categoria) throws ProdutoNaoEncontradoExeception {
        List<Produto> produtoList = produtoRepository.findByCategoria(CategoriaEnum.valueOf(categoria));

        return produtoList.stream().map(this::toProdutoResponseDTO).collect(Collectors.toList());
    }

    private Produto toProduto(CadastrarProdutoRequestDTO cadastrarProdutoRequestDTO){
        Produto produto = new Produto();
        produto.setNome(cadastrarProdutoRequestDTO.getNome());
        produto.setDescricao(cadastrarProdutoRequestDTO.getDescricao());
        produto.setCategoria(CategoriaEnum.valueOf(cadastrarProdutoRequestDTO.getCategoria()));
        produto.setPreco(new BigDecimal(cadastrarProdutoRequestDTO.getPreco()));
        produto.setCreateAt(LocalDateTime.now());
        produto.setUpdateAt(LocalDateTime.now());
        return produto;
    }

    private ProdutoResponseDTO toProdutoResponseDTO(Produto produto){
        ProdutoResponseDTO produtoResponseDTO = new ProdutoResponseDTO();
        produtoResponseDTO.setId(produto.getId());
        produtoResponseDTO.setNome(produto.getNome());
        produtoResponseDTO.setDescricao(produto.getDescricao());
        produtoResponseDTO.setPreco(produto.getPreco());
        produtoResponseDTO.setCategoria(produto.getCategoria().name());
        return produtoResponseDTO;
    }
}
