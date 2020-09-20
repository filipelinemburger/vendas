package org.com.br.filipe.vendas.service;

import org.com.br.filipe.vendas.rest.dto.PedidoDTO;
import org.com.br.filipe.vendas.domain.Pedido;
import org.springframework.data.domain.Example;

import java.util.List;
import java.util.Optional;

public interface IPedidoService {
    Optional<Pedido> findById(Integer id);

    List<Pedido> findAll();

    void delete(Pedido pedido);

    Pedido salvar(PedidoDTO pedidoDTO);

    public List<Pedido> findAllWithExampleSearch(Example example);
}
