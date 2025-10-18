package org.laxaruzpizzas.pizzerialaxaruz.controller;

import org.laxaruzpizzas.pizzerialaxaruz.entity.*;
import org.laxaruzpizzas.pizzerialaxaruz.services.PedidoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping("/pedido/confirmar")
    public String confirmarPedido(HttpSession session, Model model) {
        // Obtener el usuario logueado
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return "redirect:/login";
        }

        // Obtener el carrito actual
        List<ItemCarrito> carrito = (List<ItemCarrito>) session.getAttribute("carrito");
        if (carrito == null || carrito.isEmpty()) {
            return "redirect:/catalogo";
        }

        // Calcular total
        int total = carrito.stream().mapToInt(ItemCarrito::getSubtotal).sum();

        // Crear pedido
        Pedido pedido = new Pedido();
        pedido.setCliente(usuario);
        pedido.setItems(carrito);
        pedido.setTotal(total);
        pedido.setEstado("Confirmado");

        // Guardar en memoria
        pedidoService.save(pedido);

        // Pasar datos a la vista
        model.addAttribute("pedido", pedido);

        // Limpiar carrito
        session.removeAttribute("carrito");

        return "confirmacion";
    }
}
