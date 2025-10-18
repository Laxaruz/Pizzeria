package org.laxaruzpizzas.pizzerialaxaruz.entity;

import java.util.List;

public class Pedido {

    private Long id;
    private Usuario cliente;
    private List<ItemCarrito> items;
    private int total;
    private String estado;

    public Pedido() {}

    public Pedido(Long id, Usuario cliente, List<ItemCarrito> items, int total, String estado) {
        this.id = id;
        this.cliente = cliente;
        this.items = items;
        this.total = total;
        this.estado = estado;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Usuario getCliente() { return cliente; }
    public void setCliente(Usuario cliente) { this.cliente = cliente; }

    public List<ItemCarrito> getItems() { return items; }
    public void setItems(List<ItemCarrito> items) { this.items = items; }

    public int getTotal() { return total; }
    public void setTotal(int total) { this.total = total; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", cliente=" + (cliente != null ? cliente.toString() : "null") +
                ", items=" + items +
                ", total=" + total +
                ", estado='" + estado + '\'' +
                '}';
    }
}
