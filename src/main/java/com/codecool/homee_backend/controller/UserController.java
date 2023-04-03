package com.codecool.homee_backend.controller;


import com.codecool.homee_backend.controller.dto.homeeuser.*;
import com.codecool.homee_backend.service.HomeeUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final HomeeUserService homeeUserService;


    public UserController(HomeeUserService homeeUserService) {
        this.homeeUserService = homeeUserService;
    }

    @GetMapping
    public List<HomeeUserDto> getAllUsers() { return homeeUserService.getAllHomeeUsers(); }

    @GetMapping("/{id}")
    public HomeeUserDto getUser(@PathVariable UUID id) { return homeeUserService.getHomeeUser(id); }

    @PostMapping("/login")
    public HomeeUserDto loginUser(@RequestBody LoginUserDto dto) { return homeeUserService.loginUser(dto); }

    @PutMapping
    public HomeeUserDto updateUser(@RequestBody UpdatedHomeeUserDto dto) { return homeeUserService.updateUser(dto); }

    @PutMapping(params = "password")
    public HomeeUserDto changeUserPassword(@RequestBody ChangeHomeeUserPasswordDto dto) { return homeeUserService.changeUserPassword(dto); }

    @DeleteMapping("/{id}")
    public void softDeleteUser(@PathVariable UUID id) { homeeUserService.softDelete(id); }

    @PostMapping
    public HomeeUserDto addNewUser(@RequestBody NewHomeeUserDto newHomeeUser) {
        return homeeUserService.addNewHomeeUser(newHomeeUser);
    }
}
