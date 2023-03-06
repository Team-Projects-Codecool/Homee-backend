package com.codecool.homee_backend.controller;

import com.codecool.homee_backend.controller.dto.group.NewSpaceGroupDto;
import com.codecool.homee_backend.controller.dto.group.SpaceGroupDto;
import com.codecool.homee_backend.service.SpaceGroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/groups")
public class GroupController {
    private final SpaceGroupService spaceGroupService;

    public GroupController(SpaceGroupService spaceGroupService) {
        this.spaceGroupService = spaceGroupService;
    }

    @GetMapping("/users/{id}")
    public List<SpaceGroupDto> getSpaceGroupsForHomeeUser(@PathVariable UUID id) {
        return spaceGroupService.getGroupsByHomeeUserId(id);
    }

    @GetMapping("/{id}")
    public SpaceGroupDto getGroup(@PathVariable UUID id) {
        return spaceGroupService.getSpaceGroup(id);
    }

    @PostMapping
    public SpaceGroupDto addNewGroup(@RequestBody NewSpaceGroupDto newSpaceGroup) {
        return spaceGroupService.addNewSpaceGroup(newSpaceGroup);
    }

    @PutMapping("/{groupId}/users/{userId}")
    public void assignGroupToUser(@PathVariable UUID groupId, @PathVariable UUID userId) {
        spaceGroupService.assignGroupToUser(groupId, userId);
    }
}
