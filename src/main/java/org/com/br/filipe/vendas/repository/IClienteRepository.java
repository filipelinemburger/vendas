package org.com.br.filipe.vendas.repository;

import org.com.br.filipe.vendas.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface IClienteRepository extends JpaRepository<Cliente, Integer> {
    List<Cliente> findByNomeLike(String nome);

    boolean existsByNome(String nome);

    @Query(value = "select c from Cliente c where c.nome = :nome ")
    List<Cliente> buscarClientePorNome(String nome);

    @Modifying
    void deleteByNome(String nome);

    @Query("select c from Cliente c left join fetch c.pedidos p where c.id = :id")
    Cliente findClienteFetchPedidos(@Param("id") Integer id);

}
