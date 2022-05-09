package com.accenture.b2bautomacaoorquestrador.api.v1.assembler;

import com.accenture.b2bautomacaoorquestrador.api.v1.model.input.DadosChecklistInput;
import com.accenture.b2bautomacaoorquestrador.domain.model.DadosChecklist;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DadosChecklistModelMapper {

    @Autowired
    private ModelMapper modelMapper;

    public DadosChecklist toDomainObject(DadosChecklistInput dadosChecklistInput) {
        return modelMapper.map(dadosChecklistInput, DadosChecklist.class);
    }

    public List<DadosChecklist> toDomainListObject(List<DadosChecklistInput> dadosChecklistInput) {
        return dadosChecklistInput
                .stream()
                .map(item -> modelMapper.map(item, DadosChecklist.class))
                .collect(Collectors.toList());
    }

}
