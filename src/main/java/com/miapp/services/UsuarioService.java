package com.miapp.services;

import com.miapp.models.Usuarios;
import com.miapp.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuarios> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }
    public Usuarios obtenerUsuarioPorNombreYPass(String nombre, String pass) {
        return usuarioRepository.findByNombreAndPass(nombre, pass);
    }
    public Usuarios obtenerUsuarioPorNombre(String nombre) {
        return usuarioRepository.findByNombre(nombre);
    }
    // Método para guardar el nuevo usuario en la base de datos
    public void guardarUsuario(Usuarios nuevoUsuario) {
        usuarioRepository.save(nuevoUsuario);
    }

    public void eliminarUsuario(Long id) {
        // Verificar si el usuario existe antes de eliminarlo (opcional)
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("El usuario con ID " + id + " no existe.");
        }
    }
}
