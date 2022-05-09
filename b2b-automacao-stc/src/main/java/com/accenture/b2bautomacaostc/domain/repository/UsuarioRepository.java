package com.accenture.b2bautomacaostc.domain.repository;

import com.accenture.b2bautomacaostc.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findTop1BySistemaAndUfAndEmUsoAndDisponivel(String sistema, String uf, Boolean emUso, Boolean disponivel);

}
