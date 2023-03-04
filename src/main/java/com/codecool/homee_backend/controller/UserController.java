package com.codecool.homee_backend.controller;


import com.codecool.homee_backend.controller.dto.homeeuser.HomeeUserDto;
import com.codecool.homee_backend.controller.dto.homeeuser.NewHomeeUserDto;
import com.codecool.homee_backend.service.HomeeUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final HomeeUserService homeeUserService;


    public UserController(HomeeUserService homeeUserService) {
        this.homeeUserService = homeeUserService;
    }

    @GetMapping
    public List<HomeeUserDto> getAllUsers() { return homeeUserService.getAllHomeeUsers(); }

    @GetMapping("/{id}")
    public HomeeUserDto getUser(@PathVariable UUID id) { return homeeUserService.getHomeeUser(id); }

    @PostMapping
    public HomeeUserDto addNewUser(@RequestBody NewHomeeUserDto newHomeeUser) {
        return homeeUserService.addNewHomeeUser(newHomeeUser);
    }
}
