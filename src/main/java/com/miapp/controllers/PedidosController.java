package com.miapp.controllers;

import com.miapp.models.PedidoDto;
import com.miapp.models.Pedidos;
import com.miapp.models.Usuarios;
import com.miapp.repositories.PedidosRepository;
import com.miapp.repositories.UsuarioRepository;
import com.miapp.services.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/pedidos")
public class PedidosController {

    @Autowired
    private PedidosRepository pedidosRepository;

    @Autowired
    private UsuarioRepository usuariosRepository;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registrarPedido")
    public String registrarPedido(@RequestParam String descripcion, HttpSession session, Model model) {
        // Obtener el nombre del usuario desde la sesión
        String nombreUsuario = (String) session.getAttribute("usuarioNombre");

        // Validar que el usuario haya iniciado sesión
        if (nombreUsuario == null) {
            model.addAttribute("error", "Debes iniciar sesión para registrar un pedido.");
            return "redirect:/login"; // Redirigir a la página de inicio de sesión si no hay usuario
        }

        // Buscar el usuario en la base de datos usando el nombre
        Usuarios usuario = usuarioService.obtenerUsuarioPorNombre(nombreUsuario);

        // Validar que el usuario exista en la base de datos
        if (usuario == null) {
            model.addAttribute("error", "Usuario no encontrado en la base de datos.");
            return "redirect:/home";
        }

        // Crear un nuevo pedido con los datos proporcionados
        Pedidos nuevoPedido = new Pedidos();
        nuevoPedido.setUsuario(usuario);
        nuevoPedido.setDescripcion(descripcion);
        nuevoPedido.setFechaPedido(LocalDate.now().toString()); // Fecha actual en formato ISO

        // Guardar el pedido en la base de datos
        pedidosRepository.save(nuevoPedido);

        // Redirigir al usuario a la página de marketing o de confirmación
        model.addAttribute("success", "¡Pedido registrado exitosamente!");
        return "redirect:/marketing";
    }



}
