package org.com.br.filipe.vendas.repository;

import org.com.br.filipe.vendas.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPedidoRepository extends JpaRepository<Pedido, Integer> {
}
