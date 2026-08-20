
package br.com.amw.baozistore.controller;

import br.com.amw.baozistore.model.Cliente;
import br.com.amw.baozistore.service.ClienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// recurso do Cliente
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    // Injeção de dependência via construtor
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // POST /clientes - Cadastra um novo cliente
    @PostMapping
    public Cliente salvar(@RequestBody Cliente cliente) {
        return clienteService.salvar(cliente);
    }

    // GET /clientes - Lista todos os clientes
    @GetMapping
    public List<Cliente> listar() {
        return clienteService.listarTodos();
    }

    // GET /clientes/{id} - Busca cliente por ID
    @GetMapping("/{id}")
    public Cliente buscarporId(@PathVariable Long id) {
        return clienteService.buscarPorId(id);
    }

    // PUT /clientes/{id} - Atualiza cliente por ID
    @PutMapping("/{id}")
    public Cliente atualizar(
            @PathVariable Long id,
            @RequestBody Cliente cliente) {
        return clienteService.atualizar(id, cliente);
    }

    // DELETE /clientes/{id} - Exclui cliente por ID
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {

        clienteService.excluir(id);
    }
}