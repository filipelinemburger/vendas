package org.com.br.filipe.vendas.rest.controller;

import org.com.br.filipe.vendas.rest.dto.PedidoDTO;
import org.com.br.filipe.vendas.domain.Pedido;
import org.com.br.filipe.vendas.service.IPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;

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

    @GetMapping(value = "/list")
    public List<Pedido> getPedidosList() {
        List<Pedido> Pedidos = iPedidoService.findAll();
        if (!Pedidos.isEmpty())
            return Pedidos;
        throw new ResponseStatusException(NOT_FOUND, "Nenhum Pedido encontrado");
    }

}
