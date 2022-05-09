package com.accenture.b2bautomacaostc.Utils;

import com.accenture.b2bautomacaostc.domain.model.PropertiesSTC;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class Util {

    @Autowired
    private PropertiesSTC propertiesSTC;

    @Getter
    @Setter
    private String UG;

    public String getUgSTC(String uf) {

        Map<String[], String> map = new HashMap<>();
        map.put(propertiesSTC.getUgRJ().split(","), "RJ");
        map.put(propertiesSTC.getUgMG().split(","), "MG");
        map.put(propertiesSTC.getUgBA().split(","), "BA");
        map.put(propertiesSTC.getUgPE().split(","), "PE");

        map.forEach((listUF, UG) -> {
            Arrays.stream(listUF).forEach(UF -> {
                if (UF.trim().equalsIgnoreCase(uf)) {
                    setUG(UG);
                }
            });
        });
        return getUG();
    }
}
