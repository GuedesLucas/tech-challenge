package br.com.fiap.techchallenge.core.ports.controller;

import br.com.fiap.techchallenge.core.DTO.CadastrarProdutoRequestDTO;
import br.com.fiap.techchallenge.core.DTO.ProdutoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Produtos", description = "Gerenciamento de produtos")
public interface ProdutoController {

    @Operation(
            summary = "Cadastra um novo produto.",
            description = "Cadastra um novo produto.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = ProdutoResponseDTO.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
    })
    public ResponseEntity<?> cadastrarProduto(CadastrarProdutoRequestDTO cadastrarProdutoRequestDTO);

    @Operation(
            summary = "Edita um produto.",
            description = "Edita um produto.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ProdutoResponseDTO.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
    })
    public ResponseEntity<?> editarProduto(String id, CadastrarProdutoRequestDTO cadastrarProdutoRequestDTO);

    @Operation(
            summary = "Busca um produto.",
            description = "Busca um produto por id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ProdutoResponseDTO.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
    })
    public ResponseEntity<?> buscarProduto(String id);

    @Operation(
            summary = "Excluir um produto.",
            description = "Excluir um produto por id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
    })
    public ResponseEntity<?> excluirProduto(String id);

    @Operation(
            summary = "Listar produtos por categoria.",
            description = "Listar produtos por categoria.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
    })
    public ResponseEntity<?> listarProdutosPorCategoria(String categoria);
}
