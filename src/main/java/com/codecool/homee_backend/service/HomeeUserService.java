package com.codecool.homee_backend.service;

import com.codecool.homee_backend.entity.HomeeUser;
import com.codecool.homee_backend.repository.HomeeUserRepository;
import org.springframework.stereotype.Service;

@Service
public class HomeeUserService {
    private final HomeeUserRepository homeeUserRepository;

    public HomeeUserService(HomeeUserRepository homeeUserRepository) {this.homeeUserRepository = homeeUserRepository;}

    public void addHomeeUser(HomeeUser homeeUser) {
        homeeUserRepository.save(homeeUser);
    }

    public HomeeUser getHomeeUser(Long id) { return homeeUserRepository.findHomeeUserById(id); }
}
