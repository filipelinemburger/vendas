package org.com.br.filipe.vendas.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {

    private Integer clienteId;
    private BigDecimal total;
    private List<ItemPedidoDTO> listaItens;


}
