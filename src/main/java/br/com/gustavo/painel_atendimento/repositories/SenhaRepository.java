package br.com.gustavo.painel_atendimento.repositories;

import br.com.gustavo.painel_atendimento.entities.Senha;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SenhaRepository extends JpaRepository<Senha, Long> {
    List<Senha> findByChamadaFalseOrderByDataHoraAsc();

    List<Senha> findByChamadaTrueOrderByDataHoraAsc();
}
