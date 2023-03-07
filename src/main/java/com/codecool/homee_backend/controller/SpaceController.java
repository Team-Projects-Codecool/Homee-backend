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

    @GetMapping(params = {"userId"})
    public List<SpaceDto> getSpacesForUserId(@RequestParam UUID userId) {
        return spaceService.getAllSpacesForHomeeUserId(userId);
    }

    @GetMapping(params = {"groupId"})
    public List<SpaceDto> getSpacesForGroupId(@RequestParam UUID groupId) {
        return spaceService.getAllSpacesForGroup(groupId);
    }

    @PostMapping
    public SpaceDto addNewSpace(@RequestBody NewSpaceDto newSpace) {
        return spaceService.addNewSpace(newSpace);
    }

    @PutMapping(params = {"spaceId", "userId"})
    public void assignSpaceToUser(@RequestParam UUID spaceId, @RequestParam UUID userId) {
        spaceService.assignSpaceToUser(spaceId, userId);
    }

    @PutMapping(params = {"spaceId", "groupId"})
    public void assignSpaceToGroup(@RequestParam UUID spaceId, @RequestParam UUID groupId) {
        spaceService.assignSpaceToGroup(spaceId, groupId);
    }
}
