package br.com.amw.baozistore.model;

import  jakarta.persistence.*;
import java.time.LocalDateTime;

 @Entity
 @Table(name = "pedidos")
 public class Pedido {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "cliente_id", nullable = false)
        private Cliente cliente;

        @ManyToOne
        @JoinColumn(name = "produto_id", nullable = false)
        private Produto produto;

        @Column(nullable = false)
        private Integer quantidade;

        @Column (nullable = false)
        private LocalDateTime dataPedido;

     public Long getId() {
         return id;
     }

     public void setId(Long id) {
         this.id = id;
     }

     public Pedido(Long id) {
         this.id = id;
     }
 }
