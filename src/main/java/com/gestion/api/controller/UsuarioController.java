package com.gestion.api.controller;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.api.exception.DatabaseLogicalException;
import com.gestion.api.model.ApiResponse;
import com.gestion.api.model.Usuario;
import com.gestion.api.model.UsuarioResponse;
import com.gestion.api.service.UsuarioService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

/**
 * The Class UsuarioController.
 */
@RestController
@RequestMapping("/api/")
@SecurityRequirement(name = "bearerAuth")
public class UsuarioController {
    
    private Pattern pattern = null;

    
    public UsuarioController(@Value("${regex.password}") String regex) {
        this.pattern = Pattern.compile(regex);
    }

    public boolean esValida(String password) {
        return pattern.matcher(password).matches();
    }

    /** The usuario service. */
    @Autowired
    private UsuarioService usuarioService;

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Obtener usuarios.
     *
     * @return the list
     */
    @GetMapping("/getUsers")
    @ResponseStatus(HttpStatus.OK)
    public List<Usuario> obtenerUsuarios() {
        return usuarioService.obtenerTodos();
    }

    /**
     * Crear usuario.
     *
     * @param usuario the usuario
     * @return the usuario
     */
    @PostMapping(value = "/createUser", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiResponse<UsuarioResponse>> crearUsuario(@Valid @RequestBody Usuario usuario) {
        if (usuarioService.emailExiste(usuario.getEmail())){
            throw new DatabaseLogicalException("500", "El correo ya registrado");
        }
        
        if (!this.esValida(usuario.getPassword())){
            throw new DatabaseLogicalException("500", "Clave con formato inválido");
        }
            
        Usuario usuario1 = usuarioService.guardar(usuario);
        UsuarioResponse usuarioResponse = new UsuarioResponse();
        usuarioResponse.setId(usuario1.getId());
        usuarioResponse.setCreated(usuario1.getCreated());
        usuarioResponse.setModified(usuario1.getModified());
        usuarioResponse.setLastlogin(usuario1.getLastlogin());
        usuarioResponse.setIsactived(usuario1.getIsActived());
        usuarioResponse.setToken(usuario1.getToken().toString());
        
        return ResponseEntity.ok(new ApiResponse<UsuarioResponse>("0", "", usuarioResponse));
    }
}
