package org.com.br.filipe.vendas.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

import static javax.persistence.GenerationType.AUTO;


@Entity
@Table(name = "produto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Integer id;

    @Column
    private String descricao;

    @Column(name = "preco_unitario")
    private BigDecimal preco;

}
