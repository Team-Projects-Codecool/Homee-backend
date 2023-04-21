package com.codecool.homee_backend.service;

import com.codecool.homee_backend.controller.dto.space.NewSpaceDto;
import com.codecool.homee_backend.controller.dto.space.ShareSpaceDto;
import com.codecool.homee_backend.controller.dto.space.SpaceDto;
import com.codecool.homee_backend.controller.dto.space.UpdatedSpaceDto;
import com.codecool.homee_backend.entity.HomeeUser;
import com.codecool.homee_backend.entity.Space;
import com.codecool.homee_backend.entity.SpaceGroup;
import com.codecool.homee_backend.mapper.SpaceMapper;
import com.codecool.homee_backend.repository.DeviceRepository;
import com.codecool.homee_backend.repository.HomeeUserRepository;
import com.codecool.homee_backend.repository.SpaceGroupRepository;
import com.codecool.homee_backend.repository.SpaceRepository;
import com.codecool.homee_backend.service.exception.HomeeUserNotFoundException;
import com.codecool.homee_backend.service.exception.SpaceGroupNotFoundException;
import com.codecool.homee_backend.service.exception.SpaceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class SpaceService {
    private final SpaceRepository spaceRepository;
    private final HomeeUserRepository homeeUserRepository;

    private final SpaceGroupRepository spaceGroupRepository;

    private final DeviceRepository deviceRepository;
    private final SpaceMapper spaceMapper;

    public SpaceService(SpaceRepository spaceRepository, HomeeUserRepository homeeUserRepository, SpaceGroupRepository spaceGroupRepository, DeviceRepository deviceRepository, SpaceMapper spaceMapper) {this.spaceRepository = spaceRepository;
        this.homeeUserRepository = homeeUserRepository;
        this.spaceGroupRepository = spaceGroupRepository;
        this.deviceRepository = deviceRepository;
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
                .orElseThrow(() -> new SpaceNotFoundException(spaceId));
    }

    public SpaceDto addNewSpace(NewSpaceDto dto) {
        Space space = spaceMapper.mapSpaceDtoToEntity(dto);
        HomeeUser homeeUser = homeeUserRepository.findById(dto.userId())
                .orElseThrow(() -> new HomeeUserNotFoundException(dto.userId()));
        space.addHomeeUser(homeeUser);
        homeeUser.addSpace(space);
        Space spaceDb = spaceRepository.save(space);
        return spaceMapper.mapSpaceEntityToDto(spaceDb);
    }

    public void assignSpaceToUser(UUID spaceId, UUID userId) {
        Space space = spaceRepository.findById(spaceId)
                .orElseThrow(() -> new SpaceNotFoundException(spaceId));
        HomeeUser homeeUser = homeeUserRepository.findById(userId)
                .orElseThrow(() -> new HomeeUserNotFoundException(userId));

        space.addHomeeUser(homeeUser);
        homeeUser.addSpace(space);
        homeeUserRepository.save(homeeUser);
    }

    public void unassignedSpaceFromUser(UUID spaceId, UUID userId) {
        Space space = spaceRepository.findById(spaceId)
                .orElseThrow(() -> new SpaceNotFoundException(spaceId));
        HomeeUser homeeUser = homeeUserRepository.findById(userId)
                .orElseThrow(() -> new HomeeUserNotFoundException(userId));
        space.getHomeeUsers().removeIf(u -> u == homeeUser);
        homeeUser.getSpaces().removeIf(s -> s == space);
        spaceRepository.save(space);
    }

    public void shareSpace(ShareSpaceDto dto) {
        Space space = spaceRepository.findById(dto.spaceId())
                .orElseThrow(() -> new SpaceNotFoundException(dto.spaceId()));
        HomeeUser homeeUser = homeeUserRepository.findByEmail(dto.invitationEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        space.addHomeeUser(homeeUser);
        homeeUser.addSpace(space);
        homeeUserRepository.save(homeeUser);
    }

    public void assignSpaceToGroup(UUID spaceId, UUID groupId) {
        Space space = spaceRepository.findById(spaceId)
                .orElseThrow(() -> new SpaceNotFoundException(spaceId));
        SpaceGroup spaceGroup = spaceGroupRepository.findById(groupId)
                .orElseThrow(() -> new SpaceGroupNotFoundException(groupId));

        space.setSpaceGroup(spaceGroup);
        spaceGroup.addSpace(space);
        spaceGroupRepository.save(spaceGroup);
    }

    @Transactional
    public void deleteSpace(UUID spaceId) {
        Space space = spaceRepository.findById(spaceId)
                        .orElseThrow(() -> new SpaceNotFoundException(spaceId));
        space.getDevices().forEach(d -> d.setSpace(null));
        spaceRepository.deleteById(spaceId);
    }

    @Transactional
    public void deleteSpaceWithDevices(UUID spaceId) {
        Space space = spaceRepository.findById(spaceId)
                .orElseThrow(() -> new SpaceNotFoundException(spaceId));
        space.getHomeeUsers().forEach(u -> {
            u.getSpaces().remove(space);
            homeeUserRepository.save(u);
        });
        spaceRepository.save(space);
        deviceRepository.deleteAllBySpaceId(spaceId);
        spaceRepository.deleteById(spaceId);
    }

    public SpaceDto updateSpace(UpdatedSpaceDto dto) {
        Space space = spaceRepository.findById(dto.id())
                .orElseThrow(() -> new SpaceNotFoundException(dto.id()));
        space.setName(dto.name());
        space.setAbout(dto.about());
        spaceRepository.save(space);
        return spaceMapper.mapSpaceEntityToDto(space);
    }

    public Integer countSpacesForHomeeUserId(UUID userId) {
        return spaceRepository.findByHomeeUserId(userId).size();
    }

}
