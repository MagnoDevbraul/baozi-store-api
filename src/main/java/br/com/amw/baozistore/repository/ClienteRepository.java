package br.com.amw.baozistore.repository;

import br.com.amw.baozistore.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import  org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {


}
