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
import java.util.Optional;

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

    public ClienteResponseDTO buscarClientePorCpf(String cpf) throws ClienteNaoEncontratoException {
        Long cpfLong = this.cpfStringToLong(cpf);

        Optional<Cliente> clienteOptional = clienteRepository.findByCpf(cpfLong);

        Cliente cliente = clienteOptional.orElseThrow(ClienteNaoEncontratoException::new);

        return this.clienteToClienteResponseDTO(cliente);
    }

    private Long cpfStringToLong(String cpf){
        return Long.valueOf(cpf.replaceAll("\\.", "").replaceAll("-",""));
    }

    private ClienteResponseDTO clienteToClienteResponseDTO(Cliente cliente){
        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getCreateDate()
        );
    }
}
