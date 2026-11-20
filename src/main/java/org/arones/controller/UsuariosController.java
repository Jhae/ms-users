package org.arones.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.arones.dto.UsuarioRequestDto;
import org.arones.dto.UsuarioResponseDto;
import org.arones.service.UsuariosService;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.List;

//@Path("/usuarios")
@Tag(name = "Recurso Usuarios", description = "API para consultas y reaalizar operaciones relacionadas a usuarios.")
@RequiredArgsConstructor(onConstructor_ = @Inject)
@Slf4j
public class UsuariosController {

    private final UsuariosService usuariosService;

    @GET
    @Operation( operationId = "getUsuarios", summary = "Obtener Usuarios",
            description = "Obtiene todos los usuarios encontrados en el sistema")
    @APIResponses( value = {
            @APIResponse(
                    responseCode = "200",
                    description = "Lsita completa de los usuarios",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = UsuarioRequestDto.class, type = SchemaType.ARRAY))),
    })
    public RestResponse<List<UsuarioResponseDto>> getUsuarios(){
        log.info("inciando metodo GET de usuarios");
//        return RestResponse.ok(usuariosService.listUsuarios());
        return null;
    }

    @POST
    @Operation( operationId = "postUsuarios", summary = "Crea nuevo usuario",
            description = "Permite la creacion de un nuevo usuario, se le debe pasar JSON con datos de usuario")
    @APIResponses( value = {
            @APIResponse(
                    responseCode = "200",
                    description = "Retorna los datos del usuarios creado en sistema",
                    content = @Content),
            @APIResponse(
                    responseCode = "400",
                    description = "Si no se encontro si no se pudo crear por algun problema con algun campo",
                    content = @Content)
    })
    public RestResponse<UsuarioResponseDto> postUsuarios(UsuarioRequestDto usuarioRequestDto){
        log.info("inciando metodo POST de usuarios");
//        return usuariosService.createUsuario(usuarioRequestDto)
//                .map(RestResponse::ok)
//                .orElse(RestResponse.status(RestResponse.Status.BAD_REQUEST));
        return null;
    }
}
