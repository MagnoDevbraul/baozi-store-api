package br.com.amw.baozistore.service;

import br.com.amw.baozistore.model.Pedido;
import br.com.amw.baozistore.repository.PedidoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido salvar(Pedido pedido) {
        pedido.setDataPedido(java.time.LocalDateTime.now());
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Pedido não encontrado."
                        )
                );
    }

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