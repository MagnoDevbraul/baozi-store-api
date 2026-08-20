package br.com.amw.baozistore.controller;

import br.com.amw.baozistore.model.Pedido;
import br.com.amw.baozistore.service.PedidoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Recurso do Pedido
@RestController
@RequestMapping("/pedidos")
public class PedidosController {

    private final PedidoService pedidoService;

    // Injeção de dependência via construtor
    public PedidosController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    // POST /pedidos - Cria um novo pedido
    @PostMapping
    public Pedido salvar(@RequestBody Pedido pedido) {
        return pedidoService.salvar(pedido);
    }

    // GET /pedidos - Lista todos os pedidos
    @GetMapping
    public List<Pedido>listarTodos(){
        return pedidoService.listarTodos();
    }

    // GET /pedidos/{id} - Busca pedido por ID
    @GetMapping("/{id}")
    public Pedido buscarPorId(@PathVariable Long id) {
        return pedidoService.buscarPorId(id);
    }

    // PUT /pedidos/{id} - Atualiza pedido por ID
    @PutMapping("/{id}")
    public Pedido atualizar(
            @PathVariable Long id,
            @RequestBody Pedido pedido) {
        return pedidoService.atualizar(id, pedido);
    }

    // DELETE /pedidos/{id} - Exclui pedido por ID
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        pedidoService.excluir(id);
    }
}
