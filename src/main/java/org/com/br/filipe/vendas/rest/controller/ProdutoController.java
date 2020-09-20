package org.com.br.filipe.vendas.rest.controller;

import org.com.br.filipe.vendas.domain.Produto;
import org.com.br.filipe.vendas.service.IProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.StringMatcher.CONTAINING;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private IProdutoService iProdutoService;

    @GetMapping(value = "/{id}")
    public Produto getProdutoById(@PathVariable Integer id) {
        return iProdutoService.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Produto não encontrado"));
    }

    @GetMapping(value = "/list")
    public List<Produto> getProdutosList() {
        List<Produto> produtos = iProdutoService.findAll();
        if (!produtos.isEmpty())
            return produtos;
        throw new ResponseStatusException(NOT_FOUND, "Nenhum produto encontrado");
    }

    @PostMapping("/list")
    @ResponseStatus(code = CREATED)
    public List<Produto> saveProdutoList(@RequestBody List<Produto> produto) {
        return iProdutoService.saveAll(produto);
    }

    @PostMapping
    @ResponseStatus(code = CREATED)
    public Produto saveProduto(@RequestBody Produto produto) {
        return iProdutoService.save(produto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(code = NO_CONTENT)
    public void deleteById(@PathVariable Integer id) {
        iProdutoService.findById(id).map(produto -> {
            iProdutoService.delete(produto);
            return produto;
        }).orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, "Ocorreu um erro ao excluir o produto"));
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, @RequestBody Produto produto) {
        iProdutoService.findById(id).map(produtoExistente -> {
            produto.setId(produtoExistente.getId());
            iProdutoService.save(produto);
            return ResponseEntity.noContent().build();
        }).orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, "Produto não encontrado"));
    }

    @GetMapping
    public List<Produto> find(Produto filtro) {
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(CONTAINING);
        Example example = Example.of(filtro, matcher);
        return iProdutoService.findAllWithExampleSearch(example);
    }

}
