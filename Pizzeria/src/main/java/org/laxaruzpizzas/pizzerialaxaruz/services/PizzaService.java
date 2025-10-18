package org.laxaruzpizzas.pizzerialaxaruz.services;

import org.laxaruzpizzas.pizzerialaxaruz.entity.Pizza;
import org.laxaruzpizzas.pizzerialaxaruz.entity.TipoPizza;
import org.laxaruzpizzas.pizzerialaxaruz.entity.TamañoPizza;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PizzaService {

    private final List<Pizza> pizzas = new ArrayList<>();

    public PizzaService() {
        long id = 1;
        for (TipoPizza tipo : TipoPizza.values()) {
            for (TamañoPizza tam : TamañoPizza.values()) {
                pizzas.add(new Pizza(id++, tipo, tam));
            }
        }
    }

    public List<Pizza> findAll() {
        return pizzas;
    }
}
