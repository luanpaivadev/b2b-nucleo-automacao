package com.accenture.b2bautomacaostc.domain.repository;

import com.accenture.b2bautomacaostc.domain.model.Consulta;
import com.accenture.b2bautomacaostc.domain.model.StatusProcessamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultasRepository extends JpaRepository<Consulta, Long> {

    List<Consulta> findBySistema(String sistema);
    List<Consulta> findBySistemaAndStatusProcessamento(String sistema, StatusProcessamento statusProcessamento);
}
