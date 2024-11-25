package com.miapp.controllers;

import com.miapp.models.Usuarios;
import com.miapp.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Redirigir a "/home" cuando se acceda a la raíz "/"
    @GetMapping("/")
    public String redirectHome() {
        return "redirect:/home";  // Redirige a /home
    }

    // Mostrar el formulario de inicio de sesión
    @GetMapping("/home")
    public String mostrarLogin(Model model) {
        List<Usuarios> usuarios = usuarioService.obtenerUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "index";
    }

    @GetMapping("/registro")
    public String registrarUsuario(Model model) {
        List<Usuarios> usuarios = usuarioService.obtenerUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "registrar";
    }

    // Página de bienvenida después de iniciar sesión correctamente
    @GetMapping("/bienvenida")
    public String mostrarBienvenida(Model model) {
        List<Usuarios> usuarios = usuarioService.obtenerUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "bienvenida";
    }

    // Procesar el inicio de sesión con el nombre y contraseña
    @PostMapping("/home")
    public String iniciarSesion(@RequestParam String nombre, @RequestParam String pass, Model model) {
        // Buscar el usuario con el nombre y contraseña proporcionados
        Usuarios usuario = usuarioService.obtenerUsuarioPorNombreYPass(nombre, pass);

        // Validar si el usuario existe
        if (usuario != null) {
            return "redirect:/bienvenida";
        } else {
            model.addAttribute("error", "Nombre o contraseña incorrectos");
            return "index";
        }
    }

    @PostMapping("/registro")
    public String registrarUsuario(@RequestParam String nombre, @RequestParam String correo, @RequestParam String pass,  @RequestParam String numerotel, Model model) {
        // Lógica para guardar el nuevo usuario en la base de datos
        Usuarios nuevoUsuario = new Usuarios();
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setCorreo(correo);
        nuevoUsuario.setPass(pass);
        nuevoUsuario.setNumeroTelefono(numerotel);
        nuevoUsuario.setRol("usuario");

        usuarioService.guardarUsuario(nuevoUsuario); // Método en el servicio para guardar el usuario

        // Redirigir al inicio de sesión o a otra página de bienvenida
        return "redirect:/home";
    }
}
