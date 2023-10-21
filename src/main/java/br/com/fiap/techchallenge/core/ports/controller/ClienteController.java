package br.com.fiap.techchallenge.core.ports.controller;

import br.com.fiap.techchallenge.core.DTO.CadastrarClienteRequestDTO;
import br.com.fiap.techchallenge.core.DTO.ClienteResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Clientes", description = "Gerenciamento de clientes")
public interface ClienteController {

    @Operation(
            summary = "Cadastra um novo cliente.",
            description = "Cadastra um novo cliente passando como parametos o nome e o cpf")
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = ClienteResponseDTO.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }) })
    public ResponseEntity<?> cadastrarCliente(CadastrarClienteRequestDTO cadastrarclienteRequestDTO);


    @Operation(
            summary = "Busca um cliente.",
            description = "Busca um  cliente passando como parametos o cpf")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ClienteResponseDTO.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }) })
    public ResponseEntity<?> buscarClientePorCpf(String cpf);

    @Operation(
            summary = "Edita um cliente.",
            description = "Edita um  cliente.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
    })
    public ResponseEntity<?> editarCliente(String id,CadastrarClienteRequestDTO cadastrarclienteRequestDTO);

    @Operation(
            summary = "Listar clientes.",
            description = "Listar clientes.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
    })
    public ResponseEntity<?> listarClientes();
}
