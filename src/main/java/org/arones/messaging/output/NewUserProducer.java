package org.arones.messaging.output;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.arones.dto.UsuarioResponseDto;
import org.arones.mapper.UsuariosMapper;
import org.arones.model.UsuarioNtt;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import com.ms.users.events.CreatedNewUserEvent;

@ApplicationScoped
@RequiredArgsConstructor(onConstructor_ = @Inject)
@Slf4j
public class NewUserProducer {

    @Inject
    @Channel("new-user-topic-out")
    private Emitter<CreatedNewUserEvent> newUserEmiter;

    private final UsuariosMapper usuariosMapper;

    public void sendEvent(UsuarioNtt usuarioNtt){
        // Envio a kafka evento de "Nuevo Usuario"

//        Antes
//        String message = objectMapper.writeValueAsString(usuarioResponseDto);
//        newUserEmiter.send(message);

        CreatedNewUserEvent createdNewUserEvent = usuariosMapper.fromNttToCreatedNewUserEvent(usuarioNtt);

        log.info("se envia a kafka evento de CreatedNewUserEvent: {}", createdNewUserEvent);
        newUserEmiter.send(createdNewUserEvent);

        log.info("mensaje enviado");
    }

}
