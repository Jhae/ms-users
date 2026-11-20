package org.arones.mapper;

import com.ms.users.events.CreatedNewUserEvent;
import org.arones.contract.ms.users.model.UsuarioRequestDto;
import org.arones.model.UsuarioNtt;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.arones.contract.ms.users.model.UsuarioResponseDto;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA)
public interface UsuariosMapper {

    UsuarioResponseDto fromNttToDto(UsuarioNtt usuarioNtt);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "notificado", ignore = true)
    UsuarioNtt fromRequestToNtt(UsuarioRequestDto usuarioRequestDto);
    CreatedNewUserEvent fromNttToCreatedNewUserEvent(UsuarioNtt usuarioNtt);
}
