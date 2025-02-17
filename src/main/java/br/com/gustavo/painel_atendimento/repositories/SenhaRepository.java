package br.com.gustavo.painel_atendimento.repositories;

import br.com.gustavo.painel_atendimento.entities.Senha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SenhaRepository extends JpaRepository<Senha, Long> {

    // 🔹 Lista as senhas pendentes (não chamadas) em ordem de chegada
    List<Senha> findByChamadaFalseOrderByDataHoraAsc();

    // 🔹 Lista as senhas atendidas (chamadas) em ordem de atendimento
    List<Senha> findByChamadaTrueOrderByDataHoraAsc();

    // 🔹 Busca a próxima senha na fila para ser chamada
    @Query("SELECT s FROM Senha s WHERE s.chamada = false ORDER BY s.dataHora ASC LIMIT 1")
    Optional<Senha> buscarProximaSenha();

    // 🔹 Atualiza o status da senha para chamada
    @Query("UPDATE Senha s SET s.chamada = true WHERE s.id = :id")
    void marcarSenhaComoChamada(@Param("id") Long id);
}
