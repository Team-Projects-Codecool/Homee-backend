package com.codecool.homee_backend.service;

import com.codecool.homee_backend.controller.dto.homeeuser.HomeeUserDto;
import com.codecool.homee_backend.controller.dto.homeeuser.NewHomeeUserDto;
import com.codecool.homee_backend.entity.HomeeUser;
import com.codecool.homee_backend.mapper.HomeeUserMapper;
import com.codecool.homee_backend.repository.HomeeUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class HomeeUserService {
    private final HomeeUserRepository homeeUserRepository;
    private final HomeeUserMapper homeeUserMapper;

    public List<HomeeUserDto> getAllHomeeUsers() {
        return homeeUserRepository.findAllBy().stream()
                .map(homeeUserMapper::mapHomeeUserEntityToDto)
                .toList();
    }

    public HomeeUserDto getHomeeUser(UUID id) {
        return homeeUserRepository.findByUserId(id)
                .map(homeeUserMapper::mapHomeeUserEntityToDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public HomeeUserDto addNewHomeeUser(NewHomeeUserDto dto) {
        HomeeUser homeeUser = homeeUserMapper.mapHomeeUserDtoToEntity(dto);
        HomeeUser homeeUserDb = homeeUserRepository.save(homeeUser);
        return homeeUserMapper.mapHomeeUserEntityToDto(homeeUserDb);
    }

    public void softDelete(UUID id) {
        HomeeUser homeeUser = homeeUserRepository.findByUserId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        obfuscateSensitiveData(homeeUser);
        homeeUserRepository.save(homeeUser);
    }

    private void obfuscateSensitiveData(HomeeUser homeeUser) {
        int firstNameLength = homeeUser.getFirstName().length();
        homeeUser.setFirstName("*".repeat(firstNameLength));

        int lastNameLength = homeeUser.getLastName().length();
        homeeUser.setLastName("*".repeat(lastNameLength));

        int usernameLength = homeeUser.getUsername().length();
        homeeUser.setUsername("*".repeat(usernameLength));

        int atCharPos = homeeUser.getEmail().indexOf('@');
        String randomPrefix = "deleted-" + UUID.randomUUID();

        homeeUser.setEmail(randomPrefix + homeeUser.getEmail().substring(atCharPos));
        homeeUser.clearAllUserGroups();
        homeeUser.clearAllUserSpaces();
    }
}
