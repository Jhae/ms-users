package org.arones.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.arones.model.UsuarioNtt;

@ApplicationScoped
public class UsuariosRepository implements PanacheRepository<UsuarioNtt> {
}
