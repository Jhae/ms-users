package org.arones.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Schema(description = "Representacion de datos del usuario")
public class UsuarioResponseDto {
    @Schema(description = "id del usuario", examples = {"1"})
    private Long id;

    @Schema(description = "nombre completo del cliente", examples = {"Jhonatan"})
    private String nombre;

    @Schema(description = "el usuario identficado del usuario", examples = {"jhae"})
    private String usuario;

    @Schema(description = "el correo del usuario", examples = {"jhonatan98181@gmaill.com"})
    private String mail;

    @Schema(description = "fecha de creacion del usuario")
    private LocalDateTime createdAt;

    @Schema(description = "fecha de creacion del usuario")
    private LocalDateTime updatedAt;

    @Schema(description = "indicador que informa si usuario ya fue notificado al momento de su creacion", examples = {"false"})
    private Boolean notificado;
}
