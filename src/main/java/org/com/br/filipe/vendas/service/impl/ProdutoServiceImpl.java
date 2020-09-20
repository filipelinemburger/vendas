package org.com.br.filipe.vendas.service.impl;

import org.com.br.filipe.vendas.domain.Produto;
import org.com.br.filipe.vendas.repository.IProdutoRepository;
import org.com.br.filipe.vendas.service.IProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServiceImpl implements IProdutoService {

    @Autowired
    private IProdutoRepository iProdutoRepository;

    public Optional<Produto> findById(Integer id) {
        return iProdutoRepository.findById(id);
    }

    public List<Produto> findAll() {
        return iProdutoRepository.findAll();
    }

    public void delete(Produto produto) {
        iProdutoRepository.delete(produto);
    }

    public Produto save(Produto produto) {
        return iProdutoRepository.save(produto);
    }

    @Override
    public List<Produto> saveAll(List<Produto> produto) {
        return iProdutoRepository.saveAll(produto);
    }

    public List<Produto> findAllWithExampleSearch(Example example) {
        return iProdutoRepository.findAll(example);
    }
}
