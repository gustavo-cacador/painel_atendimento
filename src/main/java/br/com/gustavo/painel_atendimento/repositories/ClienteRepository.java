package br.com.gustavo.painel_atendimento.repositories;

import br.com.gustavo.painel_atendimento.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // @Query("SELECT s FROM Senha s LEFT JOIN FETCH s.guiche WHERE s.chamada = false ORDER BY s.dataHora ASC")
    List<Cliente> findByChamadaFalseOrderByDataHoraAsc();

    //@Query("SELECT s FROM Senha s LEFT JOIN FETCH s.guiche WHERE s.chamada = true")
    List<Cliente> findByChamadaTrue();
}
