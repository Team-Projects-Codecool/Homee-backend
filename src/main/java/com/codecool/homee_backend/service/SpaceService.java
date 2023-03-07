package com.codecool.homee_backend.service;

import com.codecool.homee_backend.controller.dto.space.NewSpaceDto;
import com.codecool.homee_backend.controller.dto.space.SpaceDto;
import com.codecool.homee_backend.entity.HomeeUser;
import com.codecool.homee_backend.entity.Space;
import com.codecool.homee_backend.entity.SpaceGroup;
import com.codecool.homee_backend.mapper.SpaceMapper;
import com.codecool.homee_backend.repository.HomeeUserRepository;
import com.codecool.homee_backend.repository.SpaceGroupRepository;
import com.codecool.homee_backend.repository.SpaceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class SpaceService {
    private final SpaceRepository spaceRepository;
    private final HomeeUserRepository homeeUserRepository;

    private final SpaceGroupRepository spaceGroupRepository;
    private final SpaceMapper spaceMapper;

    public SpaceService(SpaceRepository spaceRepository, HomeeUserRepository homeeUserRepository, SpaceGroupRepository spaceGroupRepository, SpaceMapper spaceMapper) {this.spaceRepository = spaceRepository;
        this.homeeUserRepository = homeeUserRepository;
        this.spaceGroupRepository = spaceGroupRepository;
        this.spaceMapper = spaceMapper;
    }

    public List<SpaceDto> getAllSpaces() {
        return spaceRepository.findAllBy().stream()
                .map(spaceMapper::mapSpaceEntityToDto)
                .toList();
    }

    public List<SpaceDto> getAllSpacesForHomeeUserId(UUID homeeUserId) {
        return spaceRepository.findByHomeeUserId(homeeUserId).stream()
                .map(spaceMapper::mapSpaceEntityToDto)
                .toList();
    }

    public List<SpaceDto> getAllSpacesForGroup(UUID groupId) {
        return spaceRepository.findByGroupId(groupId).stream()
                .map(spaceMapper::mapSpaceEntityToDto)
                .toList();
    }

    public SpaceDto getSpace(UUID spaceId) {
        return spaceRepository.findById(spaceId)
                .map(spaceMapper::mapSpaceEntityToDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public SpaceDto addNewSpace(NewSpaceDto dto) {
        Space space = spaceMapper.mapSpaceDtoToEntity(dto);
        Space spaceDb = spaceRepository.save(space);
        return spaceMapper.mapSpaceEntityToDto(spaceDb);
    }

    public void assignSpaceToUser(UUID spaceId, UUID userId) {
        Space space = spaceRepository.findById(spaceId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        HomeeUser homeeUser = homeeUserRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        space.addHomeeUser(homeeUser);
        homeeUser.addSpace(space);

        homeeUserRepository.save(homeeUser);
    }

    public void assignSpaceToGroup(UUID spaceId, UUID groupId) {
        Space space = spaceRepository.findById(spaceId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        SpaceGroup spaceGroup = spaceGroupRepository.findById(groupId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        space.setSpaceGroup(spaceGroup);
        spaceGroup.addSpace(space);
        spaceGroupRepository.save(spaceGroup);
    }
}
