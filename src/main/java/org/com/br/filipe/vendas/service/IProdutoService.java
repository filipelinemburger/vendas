package org.com.br.filipe.vendas.service;

import org.com.br.filipe.vendas.domain.Produto;
import org.springframework.data.domain.Example;

import java.util.List;
import java.util.Optional;

public interface IProdutoService {

    public Optional<Produto> findById(Integer id);
    public List<Produto> findAll();
    public void delete(Produto produto);
    public Produto save(Produto produto);
    public List<Produto> saveAll(List<Produto> produto);
    public List<Produto> findAllWithExampleSearch(Example example);
}
