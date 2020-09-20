package org.com.br.filipe.vendas.service;

import org.com.br.filipe.vendas.domain.Cliente;
import org.springframework.data.domain.Example;

import java.util.List;
import java.util.Optional;

public interface IClienteService {

    public Optional<Cliente> findById(Integer id);
    public List<Cliente> findAll();
    public void delete(Cliente cliente);
    public Cliente save(Cliente cliente);
    public List<Cliente> findAllWithExampleSearch(Example example);
}
