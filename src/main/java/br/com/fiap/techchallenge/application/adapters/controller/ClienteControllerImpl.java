package br.com.fiap.techchallenge.application.adapters.controller;

import br.com.fiap.techchallenge.core.DTO.CadastrarClienteRequestDTO;
import br.com.fiap.techchallenge.core.DTO.ClienteResponseDTO;
import br.com.fiap.techchallenge.core.exception.ClienteJaExistenteException;
import br.com.fiap.techchallenge.core.exception.ClienteNaoEncontratoException;
import br.com.fiap.techchallenge.core.ports.controller.ClienteController;
import br.com.fiap.techchallenge.core.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/clientes")
public class ClienteControllerImpl implements ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Override
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

    @Override
    @GetMapping("/{cpf}")
    public ResponseEntity<?> buscarClientePorCpf(@PathVariable("cpf") String cpf){
        try{
            ClienteResponseDTO clienteResponseDTO = clienteService.buscarClientePorCpf(cpf);
            return ResponseEntity.ok(clienteResponseDTO);

        }catch (ClienteNaoEncontratoException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<?> editarCliente(@PathVariable("id") String id, @Valid @RequestBody CadastrarClienteRequestDTO cadastrarclienteRequestDTO){
        try{
            ClienteResponseDTO clienteResponseDTO = clienteService.editarCliente(id, cadastrarclienteRequestDTO);
            return ResponseEntity.ok(clienteResponseDTO);

        }catch (ClienteNaoEncontratoException e){
            Map<String, List<String>> body = new HashMap<>();
            body.put("errors", List.of("Cliente nao encontrado."));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
        }catch (Exception e){
            Map<String, List<String>> body = new HashMap<>();
            body.put("errors", List.of("Erro interno do servidor."));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @Override
    @GetMapping
    public ResponseEntity<?> listarClientes(){
        try{
            return ResponseEntity.ok(clienteService.listarClientes());

        }catch (Exception e){
            Map<String, List<String>> body = new HashMap<>();
            body.put("errors", List.of("Erro interno do servidor."));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }
}
