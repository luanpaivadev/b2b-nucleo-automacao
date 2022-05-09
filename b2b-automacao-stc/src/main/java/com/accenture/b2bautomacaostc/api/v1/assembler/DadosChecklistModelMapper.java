package com.accenture.b2bautomacaostc.api.v1.assembler;

import com.accenture.b2bautomacaostc.api.v1.dto.DadosChecklistDTO;
import com.accenture.b2bautomacaostc.domain.model.DadosChecklist;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DadosChecklistModelMapper {

    @Autowired
    private ModelMapper modelMapper;

    public DadosChecklistDTO toDTOObject(DadosChecklist dadosChecklist) {
        return modelMapper.map(dadosChecklist, DadosChecklistDTO.class);
    }

}
