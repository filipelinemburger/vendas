package org.com.br.filipe.vendas.rest.controller;

import org.com.br.filipe.vendas.domain.Cliente;
import org.com.br.filipe.vendas.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.StringMatcher.CONTAINING;
import static org.springframework.http.HttpStatus.*;

@Validated
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {


    @Autowired
    private IClienteService iClienteService;

    @GetMapping(value = "/{id}")
    public Cliente getClienteById(@PathVariable Integer id) {
        return iClienteService.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Cliente não encontrado"));
    }

    @GetMapping(value = "/list")
    public List<Cliente> getClientsList() {
        List<Cliente> clientes = iClienteService.findAll();
        if (!clientes.isEmpty())
            return clientes;
        throw new ResponseStatusException(NOT_FOUND, "Nenhum cliente encontrado");
    }

    @PostMapping
    @ResponseStatus(code = CREATED)
    public Cliente saveCliente(@RequestBody Cliente cliente) {
        return iClienteService.save(cliente);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(code = NO_CONTENT)
    public void deleteById(@PathVariable Integer id) {
        iClienteService.findById(id).map(cliente -> {
            iClienteService.delete(cliente);
            return cliente;
        }).orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, "Ocorreu um erro ao excluir o cliente"));
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, @RequestBody Cliente cliente) {
        iClienteService.findById(id).map(clienteExistente -> {
            cliente.setId(clienteExistente.getId());
            iClienteService.save(cliente);
            return ResponseEntity.noContent().build();
        }).orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, "Cliente não encontrado"));
    }

    @GetMapping
    public List<Cliente> find(Cliente filtro) {
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(CONTAINING);
        Example example = Example.of(filtro, matcher);
        return iClienteService.findAllWithExampleSearch(example);
    }


}
