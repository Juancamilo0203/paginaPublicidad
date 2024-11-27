package com.miapp.services;

import com.miapp.models.Pedidos;
import com.miapp.repositories.PedidosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidosService {
    @Autowired
    private PedidosRepository pedidosRepository;


    public List<Pedidos> obtenerPedidos() {
        // Devuelve todos los pedidos de la base de datos
        return pedidosRepository.findAll();
    }


    public Pedidos obtenerPedidoPorId(Long id) {
        // Busca un pedido por su ID
        Optional<Pedidos> pedido = pedidosRepository.findById(id);
        return pedido.orElse(null); // Retorna null si no lo encuentra
    }


    public void guardarPedido(Pedidos pedido) {
        // Guarda o actualiza un pedido
        pedidosRepository.save(pedido);
    }

    public void eliminarPedido(Long id) {
        // Verificar si el usuario existe antes de eliminarlo (opcional)
        if (pedidosRepository.existsById(id)) {
            pedidosRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("El pedido con ID " + id + " no existe.");
        }
    }
}
