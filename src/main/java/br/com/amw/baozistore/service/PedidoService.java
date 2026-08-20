package br.com.amw.baozistore.service;

import br.com.amw.baozistore.model.Pedido;
import br.com.amw.baozistore.repository.PedidoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

// Classe de serviço responsável pelas regras de negócio do recurso Pedido
@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    // Injeção de dependência via construtor
    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    // Salva um novo pedido e define automaticamente a data e hora atual
    public Pedido salvar(Pedido pedido) {
        pedido.setDataPedido(java.time.LocalDateTime.now());
        return pedidoRepository.save(pedido);
    }

    // Retorna a lista completa de todos os pedidos
    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    // Busca pedido por ID ou lança exceção NOT_FOUND (404)
    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Pedido não encontrado."
                        )
                );
    }

    // Atualiza os dados de um pedido existente
    public Pedido atualizar(Long id, Pedido pedido) {

        Pedido pedidoExistente = pedidoRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Pedido não encontrado."
                        )
                );

        pedidoExistente.setCliente(pedido.getCliente());
        pedidoExistente.setProduto(pedido.getProduto());
        pedidoExistente.setQuantidade(pedido.getQuantidade());
        pedidoExistente.setDataPedido(pedido.getDataPedido());

        return pedidoRepository.save(pedidoExistente);
    }

    // Exclui um pedido pelo ID após a verificação da existência
    public void excluir(Long id) {

        if (!pedidoRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Pedido não encontrado."
            );
        }

        pedidoRepository.deleteById(id);
    }
}