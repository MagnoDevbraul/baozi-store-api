package br.com.amw.baozistore.repository;

import br.com.amw.baozistore.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

// Repositório JPA para operações de CRUD no banco de dados para a entidade Produto
public interface ProdutoRepository extends JpaRepository<Produto, Long> {


}
