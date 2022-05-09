package com.accenture.b2bautomacaostc.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String usuario;
    private String sistema;
    private String uf;
    private Boolean emUso;
    private Boolean disponivel;
    @UpdateTimestamp
    private LocalDateTime ultimoUso;
}
