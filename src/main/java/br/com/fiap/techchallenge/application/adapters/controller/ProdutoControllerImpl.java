package br.com.fiap.techchallenge.application.adapters.controller;


import br.com.fiap.techchallenge.core.DTO.CadastrarProdutoRequestDTO;
import br.com.fiap.techchallenge.core.DTO.ProdutoResponseDTO;
import br.com.fiap.techchallenge.core.exception.ProdutoNaoEncontradoExeception;
import br.com.fiap.techchallenge.core.ports.controller.ProdutoController;
import br.com.fiap.techchallenge.core.services.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/produtos")
public class ProdutoControllerImpl implements ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Override
    @PostMapping
    public ResponseEntity<?> cadastrarProduto(@Valid @RequestBody CadastrarProdutoRequestDTO cadastrarProdutoRequestDTO){
        try{
            ProdutoResponseDTO produtoResponseDTO = produtoService.cadastrarProduto(cadastrarProdutoRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(produtoResponseDTO);

        }catch (Exception e){
            Map<String, List<String>> body = new HashMap<>();
            body.put("errors", List.of("Erro interno do servidor."));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<?> editarProduto(@PathVariable("id") String id, @Valid @RequestBody CadastrarProdutoRequestDTO cadastrarProdutoRequestDTO){
        try{
            ProdutoResponseDTO produtoResponseDTO = produtoService.editarProduto(id, cadastrarProdutoRequestDTO);
            return ResponseEntity.status(HttpStatus.OK).body(produtoResponseDTO);

        }catch (ProdutoNaoEncontradoExeception e){
            Map<String, List<String>> body = new HashMap<>();
            body.put("errors", List.of("Produto nao encontrado."));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
        }catch (Exception e){
            Map<String, List<String>> body = new HashMap<>();
            body.put("errors", List.of("Erro interno do servidor."));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarProduto(@PathVariable("id") String id){
        try{
            ProdutoResponseDTO produtoResponseDTO = produtoService.buscarProduto(id);
            return ResponseEntity.status(HttpStatus.OK).body(produtoResponseDTO);

        }catch (ProdutoNaoEncontradoExeception e){
            Map<String, List<String>> body = new HashMap<>();
            body.put("errors", List.of("Produto nao encontrado."));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
        }catch (Exception e){
            Map<String, List<String>> body = new HashMap<>();
            body.put("errors", List.of("Erro interno do servidor."));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirProduto(@PathVariable("id") String id){
        try{
            produtoService.excluirProduto(id);
            return ResponseEntity.status(HttpStatus.OK).build();

        }catch (ProdutoNaoEncontradoExeception e){
            Map<String, List<String>> body = new HashMap<>();
            body.put("errors", List.of("Produto nao encontrado."));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
        }catch (Exception e){
            Map<String, List<String>> body = new HashMap<>();
            body.put("errors", List.of("Erro interno do servidor."));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @Override
    @GetMapping
    public ResponseEntity<?> listarProdutosPorCategoria(@RequestParam("categoria") String categoria){
        try{
            List<ProdutoResponseDTO> produtoResponseDTOList = produtoService.listarProdutosPorCategoria(categoria);
            return ResponseEntity.status(HttpStatus.OK).body(produtoResponseDTOList);

        }catch (Exception e){
            Map<String, List<String>> body = new HashMap<>();
            body.put("errors", List.of("Erro interno do servidor."));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }
}
