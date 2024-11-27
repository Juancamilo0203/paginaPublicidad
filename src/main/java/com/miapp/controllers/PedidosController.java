package com.miapp.controllers;

import com.miapp.models.PedidoDto;
import com.miapp.models.Pedidos;
import com.miapp.models.Usuarios;
import com.miapp.repositories.PedidosRepository;
import com.miapp.repositories.UsuarioRepository;
import com.miapp.services.PedidosService;
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
@RequestMapping("/pedido")
public class PedidosController {

    @Autowired
    private PedidosRepository pedidosRepository;

    @Autowired
    private UsuarioRepository usuariosRepository;

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private PedidosService pedidosService;

    @PostMapping("/registrarPedido")
    public String registrarPedido(
            @RequestParam String descripcion,
            @RequestParam String seccion,
            HttpSession session,
            Model model
    ) {
        // Obtener el nombre del usuario desde la sesión
        String nombreUsuario = (String) session.getAttribute("usuarioNombre");

        // Validar que el usuario haya iniciado sesión
        if (nombreUsuario == null) {
            model.addAttribute("error", "Debes iniciar sesión para registrar un pedido.");
            return "redirect:/login";
        }

        // Buscar el usuario en la base de datos usando el nombre
        Usuarios usuario = usuarioService.obtenerUsuarioPorNombre(nombreUsuario);

        if (usuario == null) {
            model.addAttribute("error", "Usuario no encontrado en la base de datos.");
            return "redirect:/home";
        }

        // Crear un nuevo pedido
        Pedidos nuevoPedido = new Pedidos();
        nuevoPedido.setUsuario(usuario);
        nuevoPedido.setDescripcion(descripcion);
        nuevoPedido.setFechaPedido(LocalDate.now().toString()); // Fecha actual
        nuevoPedido.setSeccion(seccion); // Asignar la sección

        // Guardar el pedido en la base de datos
        pedidosRepository.save(nuevoPedido);

        // Mensaje de éxito
        model.addAttribute("success", "¡Pedido registrado exitosamente!");
        return "redirect:/" + seccion; // Redirigir a la página actual
    }

    //Admin features
    @GetMapping
    public String listarPedidos(Model model) {
        model.addAttribute("pedidos", pedidosRepository.findAll());
        return "admin";
    }

    @PostMapping("/crear")
    public String crearPedido(@RequestParam String descripcion, @RequestParam String seccion) {
        Pedidos nuevoPedido = new Pedidos();
        nuevoPedido.setDescripcion(descripcion);
        nuevoPedido.setSeccion(seccion);
        nuevoPedido.setFechaPedido(LocalDate.now().toString());
        pedidosRepository.save(nuevoPedido);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable Long id) {
        pedidosService.eliminarPedido(id);  // Usar el repositorio de pedidos para eliminar el pedido
        return ResponseEntity.noContent().build();
    }



}
