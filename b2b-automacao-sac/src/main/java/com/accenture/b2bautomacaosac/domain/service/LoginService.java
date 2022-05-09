package com.accenture.b2bautomacaosac.domain.service;

import com.accenture.b2bautomacaosac.domain.exceptions.UsuarioException;
import com.accenture.b2bautomacaosac.domain.model.Usuario;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoginService {

    @Autowired
    private UsuarioService usuarioService;

    public void liberarUsuarioAlocado(Usuario usuario) throws InterruptedException {
        if (usuario != null) {
            log.info("Liberando usuário...");
            usuario.setEmUso(false);
            usuarioService.save(usuario);
            log.info("Usuário liberado com sucesso!");
        }
        Thread.sleep(2000);
    }

    public Usuario alocarUsuarioDisponivel(final String UF) throws UsuarioException, InterruptedException {
        log.info("Iniciando processo de login...");
        log.info("Alocando usuário...");
        Usuario usuario = usuarioService.alocarUsuarioDisponivel(UF);
        log.info("Usuário alocado com sucesso: {}", usuario.getUsuario());
        Thread.sleep(2000);
        return usuario;
    }
}
