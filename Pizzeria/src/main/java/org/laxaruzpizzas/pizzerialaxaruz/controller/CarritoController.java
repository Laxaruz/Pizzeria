package org.laxaruzpizzas.pizzerialaxaruz.controller;
import org.laxaruzpizzas.pizzerialaxaruz.entity.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;




@Controller
@RequestMapping("/carrito")
public class CarritoController {

    @PostMapping("/agregar")
    public String agregarPizza(
            @RequestParam TipoPizza tipo,
            @RequestParam TamañoPizza tamaño,
            @RequestParam(defaultValue = "1") int cantidad,
            HttpSession session
    ) {
        // Crear la pizza según los enums recibidos
        Pizza pizza = new Pizza(System.currentTimeMillis(), tipo, tamaño);

        // Obtener o crear el carrito en sesión
        List<ItemCarrito> carrito = (List<ItemCarrito>) session.getAttribute("carrito");
        if (carrito == null) {
            carrito = new ArrayList<>();
            session.setAttribute("carrito", carrito);
        }

        // Buscar si ya existe el mismo tipo y tamaño
        boolean combinado = false;
        for (ItemCarrito item : carrito) {
            if (item.getPizza().getTipo() == tipo && item.getPizza().getTamaño() == tamaño) {
                item.setCantidad(item.getCantidad() + cantidad);
                combinado = true;
                break;
            }
        }

        // Si no existía, añadir nuevo
        if (!combinado) {
            carrito.add(new ItemCarrito(pizza, cantidad));
        }

        return "redirect:/carrito/ver";
    }

    @GetMapping("/ver")
    public String verCarrito(Model model, HttpSession session) {
        List<ItemCarrito> carrito = (List<ItemCarrito>) session.getAttribute("carrito");
        if (carrito == null) carrito = new ArrayList<>();
        int total = carrito.stream().mapToInt(ItemCarrito::getSubtotal).sum();

        model.addAttribute("carrito", carrito);
        model.addAttribute("total", total);
        return "carrito";
    }

    @PostMapping("/eliminar")
    public String eliminarPizza(@RequestParam int index, HttpSession session) {
        List<ItemCarrito> carrito = (List<ItemCarrito>) session.getAttribute("carrito");
        if (carrito != null && index >= 0 && index < carrito.size()) {
            carrito.remove(index);
        }
        return "redirect:/carrito/ver";
    }

    @PostMapping("/cancelar")
    public String cancelarPedido(HttpSession session) {
        session.removeAttribute("carrito");
        return "redirect:/catalogo";
    }
}
