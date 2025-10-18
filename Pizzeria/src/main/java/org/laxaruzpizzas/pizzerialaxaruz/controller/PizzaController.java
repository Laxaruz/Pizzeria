package org.laxaruzpizzas.pizzerialaxaruz.controller;

import org.laxaruzpizzas.pizzerialaxaruz.entity.Pizza;
import org.laxaruzpizzas.pizzerialaxaruz.services.PizzaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PizzaController {

    private final PizzaService pizzaService;

    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping("/")
    public String root() {
        return "redirect:/catalogo";
    }

    @GetMapping("/catalogo")
    public String verCatalogo(Model model, HttpSession session) {
        // Si el usuario no está logueado, redirigir al login
        Object usuario = session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return "redirect:/usuario/login";
        }

        // Cargar las pizzas disponibles
        List<Pizza> pizzas = pizzaService.findAll();
        model.addAttribute("pizzas", pizzas);

        return "catalogo";
    }
}
