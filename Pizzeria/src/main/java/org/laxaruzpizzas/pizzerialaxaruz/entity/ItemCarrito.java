package org.laxaruzpizzas.pizzerialaxaruz.entity;

public class ItemCarrito {

    private Pizza pizza;
    private int cantidad;

    public ItemCarrito() {}

    public ItemCarrito(Pizza pizza, int cantidad) {
        this.pizza = pizza;
        this.cantidad = cantidad;
    }

    // Getters y Setters
    public Pizza getPizza() {
        return pizza;
    }
    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    // Subtotal calculado
    public int getSubtotal() {
        return pizza.getPrecio() * cantidad;
    }

    @Override
    public String toString() {
        return cantidad + "x " + pizza.toString() + " = $" + getSubtotal();
    }
}
