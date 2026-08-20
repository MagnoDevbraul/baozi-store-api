package br.com.amw.baozistore.controller;

import br.com.amw.baozistore.model.Produto;
import br.com.amw.baozistore.service.ProdutoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Recursodo Produto
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    // Injeção de dependência via construtor
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }
    // POST /produtos - Cadastra um novo produto
    @PostMapping
    public Produto salvar(@RequestBody Produto produto) {
        return produtoService.salvar(produto);
    }

    // GET /produtos - Lista todos os produtos
    @GetMapping
    public List<Produto> listarTodos() {
        return produtoService.listarTodos();
    }

    // GET /produtos/{id} - Busca produto por ID
    @GetMapping("/{id}")
    public Produto buscarPorId(@PathVariable Long id) {
        return produtoService.buscarPorId(id);
    }

    // PUT /produtos/{id} - Atualiza produto por ID
    @PutMapping("/{id}")
    public Produto atualizar(
            @PathVariable Long id,
            @RequestBody Produto produto) {
        return produtoService.atualizar(id, produto);
    }

    // DELETE /produtos/{id} - Exclui produto por ID
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        produtoService.excluir(id);
    }
}