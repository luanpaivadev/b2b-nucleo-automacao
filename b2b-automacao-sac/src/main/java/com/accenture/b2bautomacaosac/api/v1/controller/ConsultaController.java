package com.accenture.b2bautomacaosac.api.v1.controller;

import com.accenture.b2bautomacaosac.domain.model.Consulta;
import com.accenture.b2bautomacaosac.domain.model.StatusProcessamento;
import com.accenture.b2bautomacaosac.domain.model.Usuario;
import com.accenture.b2bautomacaosac.domain.repository.ConsultasRepository;
import com.accenture.b2bautomacaosac.domain.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/v1/checklist/sac/consultas")
public class ConsultaController {

    @Autowired
    private ConsultasRepository consultasRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<List<Consulta>> findBySistema() {
        var consultas = consultasRepository.findBySistema("SAC");
        return ResponseEntity.ok(consultas);
    }

    @GetMapping("/status-processamento")
    public ResponseEntity<?> findBySistemaAndStatusProcessamento(StatusProcessamento statusProcessamento) {
        if (statusProcessamento == null) {
            return ResponseEntity.badRequest().body("INSERIR PARÂMETRO: [statusProcessamento=PENDENTE/PROCESSADO]");
        }
        var consultas = consultasRepository.findBySistemaAndStatusProcessamento("SAC", statusProcessamento);
        return ResponseEntity.ok(consultas);
    }

    @GetMapping("/usuarios-disponiveis")
    public ResponseEntity<?> findAll(@RequestParam String sistema, @RequestParam String uf,
                                     @RequestParam Boolean emUso, @RequestParam Boolean disponivel) {
        Optional<Usuario> result = usuarioRepository
                .findTop1BySistemaAndUfAndEmUsoAndDisponivel(sistema, uf, emUso, disponivel);

        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.notFound().build();
    }
}
