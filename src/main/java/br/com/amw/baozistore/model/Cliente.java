package br.com.amw.baozistore.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;

// Entidade JPA que mapeia a tabela de clientes no banco de dados
@Entity
@Table(name = "clientes")
public class Cliente {

    // Chave primária com auto-incremento
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    // Formata a data no padrão brasileiro na serialização JSON
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate clienteDesde;

    // Construtor padrão
    public Cliente() {
    }

    // Construtor com ID
    public void setId(Long id) {
        this.id = id;
    }

    // Getters e Setters
    public Cliente(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getClienteDesde() {
        return clienteDesde;
    }

    public void setClienteDesde(LocalDate clienteDesde) {
        this.clienteDesde = clienteDesde;
    }
}