package org.com.br.filipe.vendas.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column
    private Integer id;

    @Column(name = "nome", length = 100)
    private String nome;

    @Column(name = "cpf", length = 11)
    private String cpf;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente", fetch = LAZY)
    private Set<Pedido> pedidos;

    public Cliente(String nome) {
        this.nome = nome;
    }

    public Cliente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

}
