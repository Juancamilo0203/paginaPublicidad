package com.miapp.controllers;

import com.miapp.models.Usuarios;
import com.miapp.services.UsuarioService;
import jakarta.servlet.http.HttpSession;
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

    @GetMapping("/campañas")
    public String mostrarcampanas(Model model) {
        List<Usuarios> usuarios = usuarioService.obtenerUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "campañas";
    }

    @GetMapping("/marketing")
    public String mostrarMarketin(Model model) {
        List<Usuarios> usuarios = usuarioService.obtenerUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "marketing";
    }

    @GetMapping("/prodaud")
    public String mostrarProdaud(Model model) {
        List<Usuarios> usuarios = usuarioService.obtenerUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "prodaud";
    }

    @GetMapping("/webapp")
    public String mostrarWebapp(Model model) {
        List<Usuarios> usuarios = usuarioService.obtenerUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "webapp";
    }

    @PostMapping("/home")
    public String iniciarSesion(@RequestParam String nombre, @RequestParam String pass, HttpSession session, Model model) {
        // Buscar el usuario con el nombre y la contraseña proporcionados
        Usuarios usuario = usuarioService.obtenerUsuarioPorNombreYPass(nombre, pass);

        if (usuario != null) {
            // Guardar el nombre del usuario en la sesión
            session.setAttribute("usuarioNombre", usuario.getNombre());

            if ("usuario".equals(usuario.getRol())) {
                return "redirect:/bienvenida"; // Redirigir a la página de bienvenida
            } else if ("admin".equals(usuario.getRol())) {
                return "redirect:/admin"; // Redirigir al panel de administración
            } else {
                model.addAttribute("error", "Rol no reconocido");
                return "index";
            }
        } else {
            model.addAttribute("error", "Nombre o contraseña incorrectos");
            return "index"; // Si las credenciales no son correctas, volver al formulario
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
