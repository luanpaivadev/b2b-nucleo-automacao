package com.accenture.b2bautomacaostc.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Consulta implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private String sistema;
    @CreationTimestamp
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataHoraInicio;
    @UpdateTimestamp
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataHoraFim;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String mensagemStatus;
    private String dadosSaida;
    @Enumerated(EnumType.STRING)
    private StatusProcessamento statusProcessamento = StatusProcessamento.PENDENTE;
    private String usuario;
    private String portaAutomacao;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "dados_checklist_id")
    private DadosChecklist dadosChecklist;
}
