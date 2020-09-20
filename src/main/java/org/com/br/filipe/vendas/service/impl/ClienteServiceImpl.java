package org.com.br.filipe.vendas.service.impl;

import org.com.br.filipe.vendas.domain.Cliente;
import org.com.br.filipe.vendas.repository.IClienteRepository;
import org.com.br.filipe.vendas.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements IClienteService {

    @Autowired
    private IClienteRepository iClienteRepository;

    public Optional<Cliente> findById(Integer id) {
        return iClienteRepository.findById(id);
    }

    public List<Cliente> findAll() {
        return iClienteRepository.findAll();
    }

    public void delete(Cliente cliente) {
        iClienteRepository.delete(cliente);
    }

    public Cliente save(Cliente cliente) {
        return iClienteRepository.save(cliente);
    }

    public List<Cliente> findAllWithExampleSearch(Example example) {
        return iClienteRepository.findAll(example);
    }


}
