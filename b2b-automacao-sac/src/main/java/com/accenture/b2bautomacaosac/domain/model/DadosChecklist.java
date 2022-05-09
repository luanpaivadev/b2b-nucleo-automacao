package com.accenture.b2bautomacaosac.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "dadosChecklist", cascade = CascadeType.PERSIST)
    private List<Consulta> consultas = new ArrayList<>();
}
