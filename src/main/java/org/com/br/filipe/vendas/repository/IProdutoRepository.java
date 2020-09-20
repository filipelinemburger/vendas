package org.com.br.filipe.vendas.repository;

import org.com.br.filipe.vendas.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProdutoRepository extends JpaRepository<Produto, Integer> {
}
