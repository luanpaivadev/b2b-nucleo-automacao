package com.accenture.b2bautomacaosac.domain.service;

import com.accenture.b2bautomacaosac.api.v1.dto.ConsultaDTO;
import com.accenture.b2bautomacaosac.domain.model.Consulta;
import org.springframework.stereotype.Component;

@Component
public interface ConsultasAutomacaoSAC {

    ConsultaDTO consultaDDR(Consulta consulta);
    ConsultaDTO consultaBloqueio(Consulta consulta);
}
