package org.arones.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.OffsetDateTime;

@Entity
@Table(name = "usuario")
@NoArgsConstructor @AllArgsConstructor @Builder
@Data
public class UsuarioNtt {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String usuario;
    private String contrasena;
    private String mail;
    @CreationTimestamp @Column(name = "created_at")
    private OffsetDateTime createdAt;
    @UpdateTimestamp @Column(name = "updated_at")
    private OffsetDateTime  updatedAt;
    private Boolean notificado;
}
