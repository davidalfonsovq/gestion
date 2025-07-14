package com.gestion.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.api.model.Usuario;
import com.gestion.api.repository.UsuarioRepository;


/**
 * The Class UsuarioService.
 */
@Service
public class UsuarioService {
	
	/** The usuario repository. */
	@Autowired
	private UsuarioRepository usuarioRepository;
	

    /**
     * Obtener todos.
     *
     * @return the list
     */
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    /**
     * Guardar.
     *
     * @param usuario the usuario
     * @return the usuario
     */
    public Usuario guardar(Usuario usuario) {
        usuarioRepository.save(usuario);
        return usuario;
    }
    
    /**
     * Valida existencia.
     *
     * @param email
     * @return boolean
     */
    public boolean emailExiste(String email) {
    	return usuarioRepository.existsByEmail(email);
    }

}
