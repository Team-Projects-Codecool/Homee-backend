package com.codecool.homee_backend.service;

import com.codecool.homee_backend.controller.dto.group.NewSpaceGroupDto;
import com.codecool.homee_backend.controller.dto.group.SpaceGroupDto;
import com.codecool.homee_backend.entity.HomeeUser;
import com.codecool.homee_backend.entity.SpaceGroup;
import com.codecool.homee_backend.mapper.SpaceGroupMapper;
import com.codecool.homee_backend.repository.HomeeUserRepository;
import com.codecool.homee_backend.repository.SpaceGroupRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class SpaceGroupService {
    private final SpaceGroupRepository spaceGroupRepository;

    private final HomeeUserRepository homeeUserRepository;
    private final SpaceGroupMapper spaceGroupMapper;

    public SpaceGroupService(SpaceGroupRepository groupRepository, HomeeUserRepository homeeUserRepository, SpaceGroupMapper groupMapper) {
        this.spaceGroupRepository = groupRepository;
        this.homeeUserRepository = homeeUserRepository;
        this.spaceGroupMapper = groupMapper;
    }

    public List<SpaceGroupDto> getGroupsByHomeeUserId(UUID id) {
        return spaceGroupRepository.findSpaceGroupsByHomeeUserId(id).stream()
                .map(spaceGroupMapper::mapGroupEntityToDto)
                .toList();
    }

    public SpaceGroupDto getSpaceGroup(UUID id) {
        return spaceGroupRepository.findById(id)
                .map(spaceGroupMapper::mapGroupEntityToDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public SpaceGroupDto addNewSpaceGroup(NewSpaceGroupDto dto) {
        SpaceGroup spaceGroup = spaceGroupMapper.mapGroupDtoToEntity(dto);
        SpaceGroup spaceGroupDb = spaceGroupRepository.save(spaceGroup);
        return spaceGroupMapper.mapGroupEntityToDto(spaceGroupDb);
    }

    public void assignGroupToUser(UUID groupId, UUID userId) {
        SpaceGroup spaceGroup = spaceGroupRepository.findById(groupId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        HomeeUser homeeUser = homeeUserRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        spaceGroup.setHomeeUser(homeeUser);
        homeeUser.addGroup(spaceGroup);

        homeeUserRepository.save(homeeUser);
    }
}
