package org.arones.service;

import org.arones.contract.ms.users.model.UsuarioRequestDto;
import org.arones.contract.ms.users.model.UsuarioResponseDto;



import java.util.List;
import java.util.Optional;

public interface UsuariosService {

    List<UsuarioResponseDto> listUsuarios();
    Optional<UsuarioResponseDto> createUsuario(UsuarioRequestDto usuarioRequestDto);
    Optional<UsuarioResponseDto> createUsuarioOrq(UsuarioRequestDto usuarioRequestDto);
    Optional<UsuarioResponseDto> actualizarUsuario();
    void eliminarUsuario();

    void actualizarUsuarioNotificado(Long id);
}
