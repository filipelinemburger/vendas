package org.com.br.filipe.vendas.rest.controller;

import org.com.br.filipe.vendas.domain.ItemPedido;
import org.com.br.filipe.vendas.domain.enums.StatusPedido;
import org.com.br.filipe.vendas.rest.dto.*;
import org.com.br.filipe.vendas.domain.Pedido;
import org.com.br.filipe.vendas.service.IPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.com.br.filipe.vendas.domain.enums.StatusPedido.CANCELADO;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private IPedidoService iPedidoService;

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save(@RequestBody PedidoDTO pedidoDTO) {
        Pedido pedido = iPedidoService.salvar(pedidoDTO);
        return pedido.getId();
    }

    @GetMapping(value = "/{id}")
    public Pedido getPedidoById(@PathVariable Integer id) {
        return iPedidoService.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Pedido não encontrado"));
    }

    @PatchMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateStatus(@PathVariable Integer id ,
                             @RequestBody AtualizacaoStatusPedidoDTO dto){
        String novoStatus = dto.getNovoStatus();
        iPedidoService.atualizaStatus(id, StatusPedido.valueOf(novoStatus));
    }


    @GetMapping(value = "/detalhes/{id}")
    public DadosPedidoDTO getPedidoDetalhesById(@PathVariable Integer id) {
        return iPedidoService.obterPedidoCompleto(id)
                .map(p -> converterPedidoParaDTO(p))
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Pedido não encontrado"));
    }

    private DadosPedidoDTO converterPedidoParaDTO(Pedido pedido){
        return DadosPedidoDTO
                .builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .status(pedido.getStatus().name())
                .itens(converterItensParaDTO(pedido.getItens()))
                .build();
    }

    private List<InformacaoItemPedidoDTO> converterItensParaDTO(List<ItemPedido> itens){
        if(CollectionUtils.isEmpty(itens)){
            return Collections.emptyList();
        }
        return itens.stream().map(
                item -> InformacaoItemPedidoDTO
                        .builder().descricaoProduto(item.getProduto().getDescricao())
                        .precoUnitario(item.getProduto().getPreco())
                        .quantidade(item.getQuantidade())
                        .build()
        ).collect(Collectors.toList());
    }


    @GetMapping(value = "/list")
    public List<Pedido> getPedidosList() {
        List<Pedido> Pedidos = iPedidoService.findAll();
        if (!Pedidos.isEmpty())
            return Pedidos;
        throw new ResponseStatusException(NOT_FOUND, "Nenhum Pedido encontrado");
    }


}
