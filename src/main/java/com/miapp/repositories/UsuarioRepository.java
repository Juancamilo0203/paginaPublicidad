package com.miapp.repositories;

import com.miapp.models.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuarios, Long> {
    Usuarios findByNombreAndPass(String nombre, String pass);
}
