package br.com.fiap.techchallenge.application;


import br.com.fiap.techchallenge.core.DTO.CadastrarClienteRequestDTO;
import br.com.fiap.techchallenge.core.DTO.CadastrarProdutoRequestDTO;
import br.com.fiap.techchallenge.core.DTO.ClienteResponseDTO;
import br.com.fiap.techchallenge.core.DTO.ProdutoResponseDTO;
import br.com.fiap.techchallenge.core.exception.ClienteJaExistenteException;
import br.com.fiap.techchallenge.core.exception.ProdutoNaoEncontradoExeception;
import br.com.fiap.techchallenge.core.services.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/produtos")
@Tag(name = "Produtos", description = "Gerenciamento de produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Operation(
            summary = "Cadastra um novo produto.",
            description = "Cadastra um novo produto.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = ProdutoResponseDTO.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
    })
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

    @Operation(
            summary = "Edita um produto.",
            description = "Edita um produto.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ProdutoResponseDTO.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
    })
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

    @Operation(
            summary = "Busca um produto.",
            description = "Busca um produto por id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ProdutoResponseDTO.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
    })
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

    @Operation(
            summary = "Excluir um produto.",
            description = "Excluir um produto por id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
    })
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

    @Operation(
            summary = "Listar produtos por categoria.",
            description = "Listar produtos por categoria.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
    })
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
