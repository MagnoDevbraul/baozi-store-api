package br.com.amw.baozistore.service;

import br.com.amw.baozistore.model.Cliente;
import br.com.amw.baozistore.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

// Classe de serviço responsável pelas regras de negócio do recurso Cliente
@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    // Injeção de dependência via construtor
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // Salva um novo cliente com validação e obrigatoriedade do nome
    public Cliente salvar(Cliente cliente) {
        if (cliente.getNome() == null || cliente.getNome().isBlank()) {
            throw new IllegalArgumentException("É obrigatório o nome do cliente!");
        }
        return clienteRepository.save(cliente);
    }

    // Retorna a lista completa de clientes
    public List<Cliente> listarTodos() {

        return clienteRepository.findAll();
    }

    // Busca cliente por ID ou lança exceção NOT_FOUND (404)
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Cliente não encontrado."
                        )
                );
    }

    // Atualiza os dados de um cliente existente
    public Cliente atualizar(Long id, Cliente cliente) {
        Cliente clienteExixtente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));

        clienteExixtente.setNome(cliente.getNome());
        clienteExixtente.setClienteDesde(cliente.getClienteDesde());

        return clienteRepository.save(clienteExixtente);
    }

    // Exclui um cliente pelo ID
    public void excluir(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new RuntimeException("Cliente naõ encontrado!");
        }

        clienteRepository.deleteById(id);
    }
}
