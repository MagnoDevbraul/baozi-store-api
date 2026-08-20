package br.com.amw.baozistore.repository;

import br.com.amw.baozistore.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import  org.springframework.data.jpa.repository.JpaRepository;

// Repositório JPA para operações de CRUD no banco de dados para a entidade Cliente
public interface ClienteRepository extends JpaRepository<Cliente, Long> {


}
