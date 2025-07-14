package com.gestion.api.controller;

import com.gestion.api.exception.DatabaseLogicalException;
import com.gestion.api.model.ApiResponse;
import com.gestion.api.model.Usuario;
import com.gestion.api.model.UsuarioResponse;
import com.gestion.api.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioControllerTest {

    @Mock
    private UsuarioService usuarioService;

    private UsuarioController usuarioController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        usuarioController = new UsuarioController("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d@$!%*?&]{8,}$");
        usuarioController.setUsuarioService(usuarioService);
    }

    @Test
    void testObtenerUsuarios() {
        Usuario usuario1 = new Usuario();
        Usuario usuario2 = new Usuario();
        when(usuarioService.obtenerTodos()).thenReturn(Arrays.asList(usuario1, usuario2));
        List<Usuario> usuarios = usuarioController.obtenerUsuarios();
        assertEquals(2, usuarios.size());
    }

    @Test
    void testCrearUsuario_EmailExiste() {
        Usuario usuario = new Usuario();
        usuario.setEmail("test@test.com");
        usuario.setPassword("Password1");
        when(usuarioService.emailExiste(usuario.getEmail())).thenReturn(true);
        DatabaseLogicalException exception = assertThrows(DatabaseLogicalException.class, () -> {
            usuarioController.crearUsuario(usuario);
        });
        assertEquals("500 - El correo ya registrado", exception.getMessage());
    }

    @Test
    void testCrearUsuario_PasswordInvalida() {
        Usuario usuario = new Usuario();
        usuario.setEmail("test@test.com");
        usuario.setPassword("short");
        when(usuarioService.emailExiste(usuario.getEmail())).thenReturn(false);
        DatabaseLogicalException exception = assertThrows(DatabaseLogicalException.class, () -> {
            usuarioController.crearUsuario(usuario);
        });
        assertEquals("500 - Clave con formato inválido", exception.getMessage());
    }

    @Test
    void testCrearUsuario_Exito() {
        Usuario usuario = new Usuario();
        usuario.setEmail("test@test.com");
        usuario.setPassword("Password1");
        when(usuarioService.emailExiste(usuario.getEmail())).thenReturn(false);
        when(usuarioService.guardar(usuario)).thenReturn(usuario);
        usuario.setId(1L);
        usuario.setCreated(LocalDateTime.now());
        usuario.setModified(LocalDateTime.now());
        usuario.setLastlogin(LocalDateTime.now());
        usuario.setIsActived(true);
        usuario.setToken(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        ResponseEntity<ApiResponse<UsuarioResponse>> response = usuarioController.crearUsuario(usuario);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("0", response.getBody().getStatus());
        assertEquals("123e4567-e89b-12d3-a456-426614174000", response.getBody().getData().getToken());
    }

    @Test
    void testEsValida() {
        assertTrue(usuarioController.esValida("Password1"));
        assertFalse(usuarioController.esValida("pass"));
    }
}
