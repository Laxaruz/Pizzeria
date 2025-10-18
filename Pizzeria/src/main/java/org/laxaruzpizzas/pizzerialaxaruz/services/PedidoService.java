package org.laxaruzpizzas.pizzerialaxaruz.services;

import org.laxaruzpizzas.pizzerialaxaruz.entity.Pedido;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PedidoService {

    private final List<Pedido> pedidos = new ArrayList<>();
    private final AtomicLong sequence = new AtomicLong(1);

    public Pedido save(Pedido pedido) {
        pedido.setId(sequence.getAndIncrement());
        pedidos.add(pedido);
        return pedido;
    }

    public List<Pedido> findAll() {
        return pedidos;
    }
}
