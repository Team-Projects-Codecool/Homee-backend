package com.codecool.homee_backend.service;

import com.codecool.homee_backend.entity.Space;
import com.codecool.homee_backend.repository.SpaceRepository;
import org.springframework.stereotype.Service;

@Service
public class SpaceService {
    private final SpaceRepository spaceRepository;

    public SpaceService(SpaceRepository spaceRepository) {this.spaceRepository = spaceRepository;}

    public void addSpace(Space space) {spaceRepository.save(space);}
}
