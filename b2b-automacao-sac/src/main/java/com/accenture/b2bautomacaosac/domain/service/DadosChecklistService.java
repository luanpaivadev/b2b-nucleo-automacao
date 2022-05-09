package com.accenture.b2bautomacaosac.domain.service;

import com.accenture.b2bautomacaosac.domain.model.DadosChecklist;
import com.accenture.b2bautomacaosac.domain.repository.DadosChecklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DadosChecklistService {

    @Autowired
    private DadosChecklistRepository dadosChecklistRepository;

    @Transactional
    public DadosChecklist save(DadosChecklist dadosChecklist) {
        return dadosChecklistRepository.save(dadosChecklist);
    }
}
