package br.com.amw.baozistore.repository;

import br.com.amw.baozistore.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

// Repositório JPA para operações de CRUD no banco de dados para a entidade Pedido
public interface PedidoRepository extends JpaRepository<Pedido, Long> {


}
