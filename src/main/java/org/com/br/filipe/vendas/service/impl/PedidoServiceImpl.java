package org.com.br.filipe.vendas.service.impl;

import org.com.br.filipe.vendas.rest.dto.ItemPedidoDTO;
import org.com.br.filipe.vendas.rest.dto.PedidoDTO;
import org.com.br.filipe.vendas.domain.Cliente;
import org.com.br.filipe.vendas.domain.ItemPedido;
import org.com.br.filipe.vendas.domain.Pedido;
import org.com.br.filipe.vendas.domain.Produto;
import org.com.br.filipe.vendas.exception.RegraNegocioException;
import org.com.br.filipe.vendas.repository.IClienteRepository;
import org.com.br.filipe.vendas.repository.IItemPedidoRepository;
import org.com.br.filipe.vendas.repository.IPedidoRepository;
import org.com.br.filipe.vendas.repository.IProdutoRepository;
import org.com.br.filipe.vendas.service.IPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements IPedidoService {

    @Autowired
    private IPedidoRepository iPedidoRepository;

    @Autowired
    private IClienteRepository iClienteRepository;

    @Autowired
    private IItemPedidoRepository iItemPedidoRepository;

    @Autowired
    private IProdutoRepository iProdutoRepository;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO pedidoDTO) {
        Cliente cliente = iClienteRepository.findById(pedidoDTO.getClienteId()).orElseThrow(() -> new RegraNegocioException("Código de cliente inválido"));
        Pedido pedido = new Pedido();
        pedido.setTotal(pedidoDTO.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        List<ItemPedidoDTO> itensPedidoDTO = pedidoDTO.getListaItens();
        List<ItemPedido> itemPedidoList = mapItensPedido(pedido, itensPedidoDTO);
        iPedidoRepository.save(pedido);
        iItemPedidoRepository.saveAll(itemPedidoList);
        pedido.setItens(itemPedidoList);
        return pedido;
    }

    private List<ItemPedido> mapItensPedido(Pedido pedido, List<ItemPedidoDTO> itensPedidoDTO) {
        if (itensPedidoDTO.isEmpty()) {
            throw new RegraNegocioException("Não é possível realizadade pedido sem ítem");
        }
        return itensPedidoDTO.stream().map(dto -> {
            Produto produto = iProdutoRepository.findById(dto.getProdutoId())
                    .orElseThrow(() -> new RegraNegocioException("Código do produto inválido: " + dto.getProdutoId()));
            ItemPedido item = new ItemPedido();
            item.setQuantidade(dto.getQuantidade());
            item.setPedido(pedido);
            item.setProduto(produto);
            return item;
        }).collect(Collectors.toList());

    }

    public Optional<Pedido> findById(Integer id) {
        return iPedidoRepository.findById(id);
    }

    public List<Pedido> findAll() {
        return iPedidoRepository.findAll();
    }

    public void delete(Pedido pedido) {
        iPedidoRepository.delete(pedido);
    }

    public Produto save(Produto produto) {
        return iProdutoRepository.save(produto);
    }

    public List<Pedido> findAllWithExampleSearch(Example example) {
        return iPedidoRepository.findAll(example);
    }
}
