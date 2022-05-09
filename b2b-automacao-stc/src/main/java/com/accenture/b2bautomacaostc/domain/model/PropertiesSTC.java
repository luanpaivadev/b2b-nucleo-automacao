package com.accenture.b2bautomacaostc.domain.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("lista.uf")
public class PropertiesSTC {

    private String ugRJ;
    private String ugMG;
    private String ugBA;
    private String ugPE;

}
