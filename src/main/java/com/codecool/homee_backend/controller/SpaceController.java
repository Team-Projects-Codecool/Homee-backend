package com.codecool.homee_backend.controller;


import com.codecool.homee_backend.controller.dto.space.NewSpaceDto;
import com.codecool.homee_backend.controller.dto.space.SpaceDto;
import com.codecool.homee_backend.service.SpaceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/spaces")
public class SpaceController {
    private final SpaceService spaceService;


    public SpaceController(SpaceService spaceService) {
        this.spaceService = spaceService;
    }

    @GetMapping
    public List<SpaceDto> getAllSpaces() { return spaceService.getAllSpaces(); }

    @GetMapping("/{id}")
    public SpaceDto getSpace(@PathVariable UUID id) { return spaceService.getSpace(id); }

    @PostMapping
    public SpaceDto addNewSpace(@RequestBody NewSpaceDto newSpace) {
        return spaceService.addNewSpace(newSpace);
    }

    @PutMapping("/{spaceId}/users/{userId}")
    public void assignSpaceToUser(@PathVariable UUID spaceId, @PathVariable UUID userId) {
        spaceService.assignSpaceToUser(spaceId, userId);
    }
}
