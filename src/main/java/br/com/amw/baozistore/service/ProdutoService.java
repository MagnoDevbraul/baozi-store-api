package br.com.amw.baozistore.service;

import br.com.amw.baozistore.model.Produto;
import br.com.amw.baozistore.repository.ProdutoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

// Classe de serviço responsável pelas regras de negócio do recurso Produto
@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    // Injeção de dependência via construtor
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    // Salva um novo produto no banco de dados
    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    // Retorna a lista completa de produtos
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    // Busca produto por ID ou lança exceção NOT_FOUND (404)
    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Produto não encontrado."
                        )
                );
    }

    // Atualiza os dados de um produto existente
    public Produto atualizar(Long id, Produto produto) {
        Produto produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Produto não encontrado."
                        )
                );

        produtoExistente.setNome(produto.getNome());
        produtoExistente.setPreco(produto.getPreco());
        produtoExistente.setEstoque(produto.getEstoque());

        return produtoRepository.save(produtoExistente);
    }

    // Exclui um produto pelo ID após a verificação de sua existência
    public void excluir(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Produto não encontrado."
            );
        }

        produtoRepository.deleteById(id);
    }
}