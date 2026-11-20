package org.arones.service.impl;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.arones.contract.ms.users.model.UsuarioRequestDto;
import org.arones.mapper.UsuariosMapper;
import org.arones.messaging.output.NewUserProducer;
import org.arones.model.UsuarioNtt;
import org.arones.repository.UsuariosRepository;
import org.arones.service.UsuariosService;
import org.arones.contract.ms.users.model.UsuarioResponseDto;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class UsuariosServiceImpl implements UsuariosService {

    private final UsuariosRepository usuariosRepository;
    private final UsuariosMapper usuariosMapper;
    private final NewUserProducer newUserProducer;

    @Override
    @RolesAllowed("default-roles-myrealm")
    public List<UsuarioResponseDto> listUsuarios() {
        log.info("iniciando... listUsuarios");
        return usuariosRepository.listAll().stream()
                .map(usuariosMapper::fromNttToDto)
                .toList();
    }

    @Override
    @Transactional
    public Optional<UsuarioResponseDto> createUsuario(UsuarioRequestDto usuarioRequestDto) {
        log.info("iniciando... createUsuario");

        // Se convierte DtoRequest -> Ntt para persistir
        log.info("Se persiste en BD nuevo usuario");
        UsuarioNtt usuarioNtt = usuariosMapper.fromRequestToNtt(usuarioRequestDto);
        usuariosRepository.persist(usuarioNtt);

        //Se envia kafka
        newUserProducer.sendEvent(usuarioNtt);
        // Se retorna datos a Controller
        UsuarioResponseDto usuarioResponseDto = usuariosMapper.fromNttToDto(usuarioNtt);

        return Optional.of(usuarioResponseDto);
    }

    @Override
    @Transactional
    public Optional<UsuarioResponseDto> createUsuarioOrq(UsuarioRequestDto usuarioRequestDto) {
        log.info("iniciando... createUsuarioOrq");
        log.info("input usuarioRequestDto: {}", usuarioRequestDto);

        // Se convierte DtoRequest -> Ntt para persistir
        log.info("Se persiste en BD nuevo usuario");
        UsuarioNtt usuarioNtt = usuariosMapper.fromRequestToNtt(usuarioRequestDto);
        usuariosRepository.persist(usuarioNtt);

        //No se envia kafka

        // Se retorna datos a Controller
        UsuarioResponseDto usuarioResponseDto = usuariosMapper.fromNttToDto(usuarioNtt);

        return Optional.of(usuarioResponseDto);
    }

    @Override
    public Optional<UsuarioResponseDto> actualizarUsuario() {
        return Optional.empty();
    }

    @Override
    public void eliminarUsuario() {

    }

    @Override
    public void actualizarUsuarioNotificado(Long id) {
        log.info("Iniciando actualizarUsuarioNotificado() id: {}",id);
        usuariosRepository.findByIdOptional(id)
            .ifPresentOrElse(
                    usuarioNtt -> {
                        usuarioNtt.setNotificado(Boolean.TRUE);
                        usuariosRepository.persist(usuarioNtt);
                    },
                    () -> {
                        throw new RuntimeException("No se pudo actualzar estado de notificado de nuevo usuario");
                    });
    }
}
