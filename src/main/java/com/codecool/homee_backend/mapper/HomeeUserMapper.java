package com.codecool.homee_backend.mapper;

import com.codecool.homee_backend.controller.dto.homeeuser.AuthenticatedUserDto;
import com.codecool.homee_backend.controller.dto.homeeuser.HomeeUserDto;
import com.codecool.homee_backend.controller.dto.homeeuser.NewHomeeUserDto;
import com.codecool.homee_backend.entity.HomeeUser;
import org.springframework.stereotype.Component;

@Component
public class HomeeUserMapper {

    public HomeeUserDto mapHomeeUserEntityToDto(HomeeUser entity) {
        return new HomeeUserDto(
                entity.getId(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getRegisteredTime(),
                entity.getLastLoggedIn(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getAbout()
        );
    }

    public AuthenticatedUserDto mapHomeeUserEntityToAuthenticatedUserDto(HomeeUser entity, String token) {
        return new AuthenticatedUserDto(
                entity.getId(),
                token
        );
    }

    public HomeeUser mapHomeeUserDtoToEntity(NewHomeeUserDto dto) {
        return new HomeeUser(
                dto.username(),
                dto.email(),
                dto.password(),
                dto.firstName(),
                dto.lastName(),
                dto.about()
        );
    }
}
