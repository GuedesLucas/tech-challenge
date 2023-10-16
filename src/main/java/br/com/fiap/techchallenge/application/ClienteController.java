package br.com.fiap.techchallenge.application;

import br.com.fiap.techchallenge.core.DTO.CadastrarClienteRequestDTO;
import br.com.fiap.techchallenge.core.DTO.ClienteResponseDTO;
import br.com.fiap.techchallenge.core.exception.ClienteJaExistenteException;
import br.com.fiap.techchallenge.core.exception.ClienteNaoEncontratoException;
import br.com.fiap.techchallenge.core.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.websocket.server.PathParam;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/clientes")
@Tag(name = "Clientes", description = "Gerenciamento de clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Operation(
            summary = "Cadastra um novo cliente.",
            description = "Cadastra um novo cliente passando como parametos o nome e o cpf")
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = ClienteResponseDTO.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }) })
    @PostMapping
    public ResponseEntity<?> cadastrarCliente(@Valid @RequestBody CadastrarClienteRequestDTO cadastrarclienteRequestDTO){
        try{
            ClienteResponseDTO clienteResponseDTO = clienteService.cadastrarCliente(cadastrarclienteRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteResponseDTO);

        }catch (ClienteJaExistenteException e){
            Map<String, List<String>> body = new HashMap<>();
            body.put("errors", List.of("Cliente já existente"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
        }
    }


    @Operation(
            summary = "Busca um cliente.",
            description = "Busca um  cliente passando como parametos o cpf")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ClienteResponseDTO.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/{cpf}")
    public ResponseEntity<?> buscarClientePorCpf(@PathVariable("cpf") String cpf){
        try{
            ClienteResponseDTO clienteResponseDTO = clienteService.buscarClientePorCpf(cpf);
            return ResponseEntity.ok(clienteResponseDTO);

        }catch (ClienteNaoEncontratoException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
