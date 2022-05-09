package com.accenture.b2bautomacaostc.api.v1.assembler;

import com.accenture.b2bautomacaostc.api.v1.dto.ConsultaDTO;
import com.accenture.b2bautomacaostc.domain.model.Consulta;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaModelMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ConsultaDTO toDTOObject(Consulta consulta) {
        return modelMapper.map(consulta, ConsultaDTO.class);
    }

}
