package com.accenture.b2bautomacaostc.domain.service;

import com.accenture.b2bautomacaostc.domain.exceptions.UsuarioException;
import com.accenture.b2bautomacaostc.domain.model.Usuario;
import com.accenture.b2bautomacaostc.domain.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario alocarUsuarioDisponivel(final String UF) throws UsuarioException {
        Optional<Usuario> result = usuarioRepository.findTop1BySistemaAndUfAndEmUsoAndDisponivel("STC", UF, false, true);
        if (result.isPresent()) {
            Usuario usuario = result.get();
            usuario.setEmUso(true);
            return usuarioRepository.save(usuario);
        }
        throw new UsuarioException("Não há usuários disponíveis no momento");
    }
}
