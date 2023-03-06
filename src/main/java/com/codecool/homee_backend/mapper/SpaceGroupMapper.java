package com.codecool.homee_backend.mapper;

import com.codecool.homee_backend.controller.dto.group.SpaceGroupDto;
import com.codecool.homee_backend.controller.dto.group.NewSpaceGroupDto;
import com.codecool.homee_backend.entity.SpaceGroup;
import org.springframework.stereotype.Component;

@Component
public class SpaceGroupMapper {

    public SpaceGroupDto mapGroupEntityToDto(SpaceGroup entity) {
        return new SpaceGroupDto(
                entity.getId(),
                entity.getName(),
                entity.getAbout()
        );
    }

    public SpaceGroup mapGroupDtoToEntity(NewSpaceGroupDto dto) {
        return new SpaceGroup(
                dto.name(),
                dto.about()
        );
    }

}
