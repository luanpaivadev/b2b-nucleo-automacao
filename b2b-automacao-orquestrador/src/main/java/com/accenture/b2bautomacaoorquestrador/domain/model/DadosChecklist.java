package com.accenture.b2bautomacaoorquestrador.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DadosChecklist implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uf;
    private String ddd;
    private String terminal;
    private String circuito;
    private String localidade;
    private String cliente;
    private String urlCallback;
    private boolean statusCallback;
    private String requestId;
    private String loteId;

    @JoinColumn(name = "dados_checklist_id", referencedColumnName = "id")
    @OneToMany(targetEntity = Consulta.class, cascade = CascadeType.ALL)
    private List<Consulta> consultas = new ArrayList<>();
}
