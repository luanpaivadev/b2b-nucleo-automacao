package com.accenture.b2bautomacaosac.domain.repository;

import com.accenture.b2bautomacaosac.domain.model.DadosChecklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DadosChecklistRepository extends JpaRepository<DadosChecklist, Long> {
}
