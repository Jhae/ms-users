package org.arones.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Path("/env")
@Tag(name = "Controller Helm", description = "Controller para pruebas de Chart")
public class EnvController {

    @Inject @ConfigProperty(name = "app.nombre")
    private String appNombre;
    @Inject @ConfigProperty(name = "appAlias")
    private String appAlias;
    @Inject @ConfigProperty(name = "customAlias")
    private String customAlias;

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    @Operation( summary = "Consultar nombre de App", description = "Devuelve el nombre de la APP configurada en cada ambiente")
    @APIResponses(value = {
            @APIResponse(responseCode = "200",
            description = "El nombre de la APP",
            content = @Content(mediaType = MediaType.APPLICATION_JSON))})
    public Response consutlarNombreApp(){

        Map<String,String> res = new HashMap<>();
        res.put("nombreApp",appNombre);
        res.put("Alias",appAlias);
        res.put("AliasPersonlizado",customAlias);

        return Response.status(Response.Status.OK)
                .entity(res)
                .build();
    }
}
