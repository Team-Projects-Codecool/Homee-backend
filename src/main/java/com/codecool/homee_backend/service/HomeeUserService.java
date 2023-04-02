package com.codecool.homee_backend.service;

import com.codecool.homee_backend.controller.dto.homeeuser.HomeeUserDto;
import com.codecool.homee_backend.controller.dto.homeeuser.LoginUserDto;
import com.codecool.homee_backend.controller.dto.homeeuser.NewHomeeUserDto;
import com.codecool.homee_backend.entity.HomeeUser;
import com.codecool.homee_backend.mapper.HomeeUserMapper;
import com.codecool.homee_backend.repository.HomeeUserRepository;
import com.codecool.homee_backend.service.exception.HomeeUserNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
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
                .orElseThrow(() -> new HomeeUserNotFoundException(id));
    }

    public HomeeUserDto addNewHomeeUser(NewHomeeUserDto dto) {
        HomeeUser homeeUser = homeeUserMapper.mapHomeeUserDtoToEntity(dto);
        HomeeUser homeeUserDb = homeeUserRepository.save(homeeUser);
        return homeeUserMapper.mapHomeeUserEntityToDto(homeeUserDb);
    }

    public void softDelete(UUID id) {
        HomeeUser homeeUser = homeeUserRepository.findByUserId(id)
                .orElseThrow(() -> new HomeeUserNotFoundException(id));

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

    public HomeeUserDto loginUser(LoginUserDto dto) {
        log.info(dto.username());
        HomeeUser homeeUser = homeeUserRepository.findByEmailOrUsername(dto.username(), dto.username())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        if (Objects.equals(homeeUser.getPassword(), dto.password())) {
            return homeeUserMapper.mapHomeeUserEntityToDto(homeeUser);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    }
}
