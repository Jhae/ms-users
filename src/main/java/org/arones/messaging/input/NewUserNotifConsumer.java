package org.arones.messaging.input;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.arones.service.UsuariosService;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
@RequiredArgsConstructor(onConstructor_ = @Inject)
@Slf4j
public class NewUserNotifConsumer {

    private final UsuariosService usuariosService;
    private final ObjectMapper objectMapper;


    @Incoming("new-user-notif-topic-in")
    @Transactional
    public void handleEnvent(String message) throws JsonProcessingException {
        log.info("Iniciando consumo new-user-notif: message: {}",message);

        if(message!= null && !message.contains("OK")){
            log.warn("message: {}, no tiene estado OK, omitido", message);
            return;
        }

        JsonNode json = objectMapper.readTree(message);
        Long idUsuario = json.has("idUsuario")? json.get("idUsuario").asLong(): null;
        if(idUsuario == null){
            log.error("Error no se tiene idUsuario");
            throw new RuntimeException("No se puede procesar evento, ya que no posee idUsuario");
        }

        usuariosService.actualizarUsuarioNotificado(idUsuario);
        log.info("Fin consumo new-user-notif");


    }

}
