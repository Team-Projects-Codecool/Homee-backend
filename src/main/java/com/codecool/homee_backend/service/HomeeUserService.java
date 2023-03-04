package com.codecool.homee_backend.service;

import com.codecool.homee_backend.entity.HomeeUser;
import com.codecool.homee_backend.repository.HomeeUserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class HomeeUserService {
    private final HomeeUserRepository homeeUserRepository;

    public HomeeUserService(HomeeUserRepository homeeUserRepository) {this.homeeUserRepository = homeeUserRepository;}

    public void addHomeeUser(HomeeUser homeeUser) {
        homeeUserRepository.save(homeeUser);
    }

    public HomeeUser getHomeeUser(UUID id) { return homeeUserRepository.findHomeeUserById(id); }
}
