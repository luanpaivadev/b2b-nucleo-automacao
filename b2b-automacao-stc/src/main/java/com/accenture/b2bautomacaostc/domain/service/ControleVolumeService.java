package com.accenture.b2bautomacaostc.domain.service;

import com.accenture.b2bautomacaostc.domain.repository.ConsultasRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ControleVolumeService {

    @Autowired
    private ConsultasRepository consultasRepository;

}
