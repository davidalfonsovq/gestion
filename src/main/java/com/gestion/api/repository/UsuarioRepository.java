package com.gestion.api.repository;

import com.gestion.api.model.Usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The Interface UsuarioRepository.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Optional<Usuario> findByEmail(String email);
	boolean existsByEmail(String email);
}
