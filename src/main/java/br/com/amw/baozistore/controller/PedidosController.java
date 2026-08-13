package br.com.amw.baozistore.controller;

import br.com.amw.baozistore.model.Pedido;
import br.com.amw.baozistore.service.PedidoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidosController {

    private final PedidoService pedidoService;

    public PedidosController(PedidoService pedidoService) {

        this.pedidoService = pedidoService;
    }

    @PostMapping
    public Pedido salvar(@RequestBody Pedido pedido) {

        return pedidoService.salvar(pedido);
    }

    @GetMapping
    public List<Pedido>listarTodos(){

        return pedidoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Pedido buscarPorId(@PathVariable Long id) {

        return pedidoService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Pedido atualizar(
            @PathVariable Long id,
            @RequestBody Pedido pedido) {

        return pedidoService.atualizar(id, pedido);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        pedidoService.excluir(id);
    }
}
