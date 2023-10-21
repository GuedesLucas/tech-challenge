package br.com.fiap.techchallenge.core.services;

import br.com.fiap.techchallenge.core.DTO.CadastrarClienteRequestDTO;
import br.com.fiap.techchallenge.core.DTO.ClienteResponseDTO;
import br.com.fiap.techchallenge.core.exception.ClienteJaExistenteException;
import br.com.fiap.techchallenge.core.exception.ClienteNaoEncontratoException;
import br.com.fiap.techchallenge.core.model.Cliente;
import br.com.fiap.techchallenge.infrastructure.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteResponseDTO cadastrarCliente(CadastrarClienteRequestDTO cadastrarclienteRequestDTO) throws ClienteJaExistenteException {
        Long cpf = this.cpfStringToLong(cadastrarclienteRequestDTO.getCpf());

        if(clienteRepository.findByCpf(cpf).isPresent()){
            throw new ClienteJaExistenteException();
        }

        Cliente cliente = new Cliente(
                cadastrarclienteRequestDTO.getNome(),
                cpf,
                LocalDateTime.now()
        );

        clienteRepository.save(cliente);

        return this.clienteToClienteResponseDTO(cliente);
    }

    public ClienteResponseDTO editarCliente(String id, CadastrarClienteRequestDTO cadastrarclienteRequestDTO) throws ClienteNaoEncontratoException {
        Long cpf = this.cpfStringToLong(cadastrarclienteRequestDTO.getCpf());
        Optional<Cliente> clienteOptional = clienteRepository.findById(Long.valueOf(id));

        if(clienteOptional.isEmpty()){
            throw new ClienteNaoEncontratoException();
        }

        Cliente cliente = clienteOptional.get();
        cliente.setNome(cadastrarclienteRequestDTO.getNome());
        cliente.setCpf(cpf);
        clienteRepository.save(cliente);
        return clienteToClienteResponseDTO(cliente);
    }

    public ClienteResponseDTO buscarClientePorCpf(String cpf) throws ClienteNaoEncontratoException {
        Long cpfLong = this.cpfStringToLong(cpf);

        Optional<Cliente> clienteOptional = clienteRepository.findByCpf(cpfLong);

        Cliente cliente = clienteOptional.orElseThrow(ClienteNaoEncontratoException::new);

        return this.clienteToClienteResponseDTO(cliente);
    }

    public List<ClienteResponseDTO> listarClientes(){
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream().map(this::clienteToClienteResponseDTO).collect(Collectors.toList());
    }

    private Long cpfStringToLong(String cpf){
        return Long.valueOf(cpf.replaceAll("\\.", "").replaceAll("-",""));
    }

    private ClienteResponseDTO clienteToClienteResponseDTO(Cliente cliente){
        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf()
        );
    }

    public Cliente buscarClientePorId(Long id) {
        final var clienteOptional = clienteRepository.findById(id);

        return clienteOptional.orElseThrow(ClienteNaoEncontratoException::new);
    }
}
