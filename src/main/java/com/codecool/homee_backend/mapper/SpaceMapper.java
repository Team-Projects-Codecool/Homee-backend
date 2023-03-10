package com.codecool.homee_backend.mapper;

import com.codecool.homee_backend.controller.dto.space.NewSpaceDto;
import com.codecool.homee_backend.controller.dto.space.SpaceDto;
import com.codecool.homee_backend.entity.Space;
import org.springframework.stereotype.Component;

@Component
public class SpaceMapper {

    public SpaceDto mapSpaceEntityToDto(Space entity) {
        return new SpaceDto(
                entity.getId(),
                entity.getName(),
                entity.getAbout()
        );
    }

    public Space mapSpaceDtoToEntity(NewSpaceDto dto) {
        return new Space(
                dto.name(),
                dto.about()
        );
    }

}
