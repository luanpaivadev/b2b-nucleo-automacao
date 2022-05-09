package com.accenture.b2bautomacaostc.domain.service;

import com.accenture.b2bautomacaostc.domain.model.DadosChecklist;
import com.accenture.b2bautomacaostc.domain.repository.DadosChecklistRepository;
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
