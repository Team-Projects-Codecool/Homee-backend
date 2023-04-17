package com.codecool.homee_backend.service;

import com.codecool.homee_backend.controller.dto.homeeuser.*;
import com.codecool.homee_backend.entity.HomeeUser;
import com.codecool.homee_backend.mapper.HomeeUserMapper;
import com.codecool.homee_backend.repository.HomeeUserRepository;
import com.codecool.homee_backend.service.auth.JwtTokenService;
import com.codecool.homee_backend.service.exception.HomeeUserNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static com.codecool.homee_backend.config.auth.SpringSecurityConfig.USER;

@Service
@AllArgsConstructor
@Slf4j
public class HomeeUserService {
    private final HomeeUserRepository homeeUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;
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

    public HomeeUserDto registerUser(NewHomeeUserDto dto) {
        HomeeUser homeeUser = homeeUserMapper.mapHomeeUserDtoToEntity(dto);
        homeeUser.setPassword(passwordEncoder.encode(homeeUser.getPassword()));
        homeeUser.setRole(USER);
        HomeeUser homeeUserDb = homeeUserRepository.save(homeeUser);
        return homeeUserMapper.mapHomeeUserEntityToDto(homeeUserDb);
    }

    public void softDelete(UUID id) {
        HomeeUser homeeUser = homeeUserRepository.findByUserId(id)
                .orElseThrow(() -> new HomeeUserNotFoundException(id));

        obfuscateSensitiveData(homeeUser);
        homeeUserRepository.save(homeeUser);
    }

    public AuthenticatedUserDto loginUser(LoginUserDto dto) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                dto.username(), dto.password()
        );
        authenticationManager.authenticate(authentication);
        HomeeUser homeeUser = homeeUserRepository.findByEmail(dto.username())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        return new AuthenticatedUserDto(homeeUser.getId(), jwtTokenService.generateToken(dto.username()));
    }

    public HomeeUserDto updateUser(UpdatedHomeeUserDto dto) {
        HomeeUser homeeUser = homeeUserRepository.findById(dto.id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        homeeUser.setUsername(dto.username());
        homeeUser.setEmail(dto.email());
        homeeUser.setFirstName(dto.firstName());
        homeeUser.setLastName(dto.lastName());
        homeeUser.setAbout(dto.about());
        homeeUserRepository.save(homeeUser);
        return homeeUserMapper.mapHomeeUserEntityToDto(homeeUser);
    }

    public HomeeUserDto changeUserPassword(ChangeHomeeUserPasswordDto dto) {
        HomeeUser homeeUser = homeeUserRepository.findById(dto.id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        if (!Objects.equals(homeeUser.getPassword(), dto.oldPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        homeeUser.setPassword(dto.newPassword());
        homeeUserRepository.save(homeeUser);
        return homeeUserMapper.mapHomeeUserEntityToDto(homeeUser);
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
