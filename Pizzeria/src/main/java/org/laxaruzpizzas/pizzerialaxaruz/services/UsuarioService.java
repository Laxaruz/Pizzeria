package org.laxaruzpizzas.pizzerialaxaruz.services;

import org.laxaruzpizzas.pizzerialaxaruz.entity.Usuario;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UsuarioService {

    private final List<Usuario> usuarios = new ArrayList<>();
    private final AtomicLong sequence = new AtomicLong(1);

    public void registrar(Usuario usuario) {
        usuario.setId(sequence.getAndIncrement());
        usuarios.add(usuario);
    }

    public boolean existeCorreo(String correo) {
        return usuarios.stream()
                .anyMatch(u -> u.getCorreo().equalsIgnoreCase(correo));
    }

    public Usuario iniciarSesion(String correo, String password) {
        return usuarios.stream()
                .filter(u -> u.getCorreo().equalsIgnoreCase(correo)
                        && u.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }
}
