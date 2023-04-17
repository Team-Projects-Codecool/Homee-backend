package com.codecool.homee_backend.controller;


import com.codecool.homee_backend.controller.dto.homeeuser.*;
import com.codecool.homee_backend.service.HomeeUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.UUID;

import static com.codecool.homee_backend.config.auth.SpringSecurityConfig.USER;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final HomeeUserService homeeUserService;


    public UserController(HomeeUserService homeeUserService) {
        this.homeeUserService = homeeUserService;
    }

    @RolesAllowed(USER)
    @GetMapping
    public List<HomeeUserDto> getAllUsers() { return homeeUserService.getAllHomeeUsers(); }

    @RolesAllowed(USER)
    @GetMapping("/{id}")
    public HomeeUserDto getUser(@PathVariable UUID id) { return homeeUserService.getHomeeUser(id); }

    @PostMapping("/login")
    public AuthenticatedUserDto loginUser(@RequestBody LoginUserDto dto) { return homeeUserService.loginUser(dto); }

    @RolesAllowed(USER)
    @PutMapping
    public HomeeUserDto updateUser(@RequestBody UpdatedHomeeUserDto dto) { return homeeUserService.updateUser(dto); }

    @RolesAllowed(USER)
    @PutMapping(params = "password")
    public HomeeUserDto changeUserPassword(@RequestBody ChangeHomeeUserPasswordDto dto) { return homeeUserService.changeUserPassword(dto); }

    @RolesAllowed(USER)
    @DeleteMapping("/{id}")
    public void softDeleteUser(@PathVariable UUID id) { homeeUserService.softDelete(id); }

    @PostMapping("/register")
    public HomeeUserDto addNewUser(@RequestBody NewHomeeUserDto newHomeeUser) {
        return homeeUserService.registerUser(newHomeeUser);
    }
}
