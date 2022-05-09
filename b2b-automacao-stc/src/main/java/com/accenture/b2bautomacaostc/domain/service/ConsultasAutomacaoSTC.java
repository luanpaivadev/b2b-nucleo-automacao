package com.accenture.b2bautomacaostc.domain.service;

import com.accenture.b2bautomacaostc.api.v1.dto.ConsultaDTO;
import com.accenture.b2bautomacaostc.domain.model.Consulta;
import org.springframework.stereotype.Component;

@Component
public interface ConsultasAutomacaoSTC {

    ConsultaDTO consultaDDR(Consulta consulta);
    ConsultaDTO consultaReparo(Consulta consulta);
    ConsultaDTO consultaBloqueio(Consulta consulta);
    ConsultaDTO consultaOs(Consulta consulta);
    ConsultaDTO consultaOsDespachada(Consulta consulta);

}
