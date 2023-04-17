package com.codecool.homee_backend.controller.dto.homeeuser;

public record NewHomeeUserDto(
        String username,
        String email,
        String password,
        String firstName,
        String lastName,
        String about
) {
}
