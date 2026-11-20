package org.arones.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Schema(description = "Representacion de datos requerios del usuario necesarios para su creacion")
public class UsuarioRequestDto {

    @Schema(description = "nombre completo del cliente", examples = {"Jhonatan"})
    private String nombre;

    @Schema(description = "contraseña segura del cliente", examples = {"JJ@#&dsk@"})
    private String contrasena;

    @Schema(description = "el usuario identficado del usuario", examples = {"jhae"})
    private String usuario;

    @Schema(description = "el correo del usuario", examples = {"jhonatan98181@gmaill.com"})
    private String mail;
}
