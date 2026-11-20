package org.arones.controller;


import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.arones.contract.ms.users.model.UsuarioRequestDto;
import org.arones.contract.ms.users.model.UsuarioResponseDto;
import org.arones.service.UsuariosService;
import org.jboss.resteasy.reactive.RestResponse;
import org.arones.contract.ms.users.resources.UsuariosResource;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor(onConstructor_ = @Inject)
@Slf4j
public class UsuariosResourceImpl implements UsuariosResource {

    private final UsuariosService usuariosService;
    @RolesAllowed("administrador")
    @Override
    public RestResponse<List<UsuarioResponseDto>> getAllUsuarios() {
        log.info("Inicia metodo de controlador GET");
        return RestResponse.ok(usuariosService.listUsuarios());
    }

    @RolesAllowed("administrador")
    @Override
    public RestResponse<UsuarioResponseDto> createUsusario(UsuarioRequestDto usuarioRequestDto) {
        log.info("Inicia metodo de controlador POST");
        return usuariosService.createUsuario(usuarioRequestDto)
                .map(usuarioResponseDto -> {
                    return RestResponse.ResponseBuilder
                            .create(RestResponse.Status.CREATED, usuarioResponseDto)
                            .location(URI.create("/usuarios/"+usuarioResponseDto.getId()))
                            .build();
                }).orElse(RestResponse.status(RestResponse.Status.BAD_REQUEST));
    }

    @RolesAllowed("administrador")
    @Override
    public RestResponse<UsuarioResponseDto> createUsusarioOrq(UsuarioRequestDto usuarioRequestDto) {
        log.info("Inicia metodo de controlador POST");
        return usuariosService.createUsuarioOrq(usuarioRequestDto)
                .map(usuarioResponseDto -> {
                    return RestResponse.ResponseBuilder
                            .create(RestResponse.Status.CREATED, usuarioResponseDto)
                            .location(URI.create("/usuarios/"+usuarioResponseDto.getId()))
                            .build();
                }).orElse(RestResponse.status(RestResponse.Status.BAD_REQUEST));
    }

}
