package com.codecool.homee_backend.mapper;

import com.codecool.homee_backend.controller.dto.homeeuser.HomeeUserDto;
import com.codecool.homee_backend.controller.dto.homeeuser.NewHomeeUserDto;
import com.codecool.homee_backend.entity.HomeeUser;
import org.springframework.stereotype.Component;

@Component
public class HomeeUserMapper {

    private final SpaceMapper spaceMapper;

    public HomeeUserMapper(SpaceMapper spaceMapper) {
        this.spaceMapper = spaceMapper;
    }


    public HomeeUserDto mapHomeeUserEntityToDto(HomeeUser entity) {
        return new HomeeUserDto(
                entity.getId(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getRegisteredTime(),
                entity.getLastLoggedIn(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getAbout(),
                entity.getSpaces().stream()
                        .map(spaceMapper::mapSpaceEntityToDto)
                        .toList()
        );
    }

    public HomeeUser mapHomeeUserDtoToEntity(NewHomeeUserDto dto) {
        return new HomeeUser(
                dto.username(),
                dto.email(),
                dto.password(),
                dto.registeredTime(),
                dto.lastLoggedIn(),
                dto.firstName(),
                dto.lastName(),
                dto.about()
        );
    }
}
