package com.accenture.b2bautomacaoorquestrador.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
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
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataHoraInicio;
    @UpdateTimestamp
    @JsonSerialize(using = LocalDateTimeSerializer.class)
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
    private DadosChecklist dadosChecklist;
}
