package org.com.br.filipe.vendas.repository;

import org.com.br.filipe.vendas.domain.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {
}
