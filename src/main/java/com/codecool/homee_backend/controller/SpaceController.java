package com.codecool.homee_backend.controller;


import com.codecool.homee_backend.controller.dto.space.NewSpaceDto;
import com.codecool.homee_backend.controller.dto.space.ShareSpaceDto;
import com.codecool.homee_backend.controller.dto.space.SpaceDto;
import com.codecool.homee_backend.controller.dto.space.UpdatedSpaceDto;
import com.codecool.homee_backend.service.SpaceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.UUID;

import static com.codecool.homee_backend.config.auth.SpringSecurityConfig.USER;

@RolesAllowed(USER)
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

    @PutMapping("/{id}")
    public SpaceDto updateSpace(@RequestBody UpdatedSpaceDto dto) {
        return spaceService.updateSpace(dto);
    }

    @GetMapping(params = {"userId"})
    public List<SpaceDto> getSpacesForUserId(@RequestParam UUID userId) {
        return spaceService.getAllSpacesForHomeeUserId(userId);
    }

    @GetMapping(params = {"userId", "count"})
    public Integer countSpacesForUserId(@RequestParam UUID userId) {
        return spaceService.countSpacesForHomeeUserId(userId);
    }

    @GetMapping(params = {"groupId"})
    public List<SpaceDto> getSpacesForGroupId(@RequestParam UUID groupId) {
        return spaceService.getAllSpacesForGroup(groupId);
    }

    @PostMapping
    public SpaceDto addNewSpace(@RequestBody NewSpaceDto newSpace) {
        return spaceService.addNewSpace(newSpace);
    }

    @PutMapping(params = "share")
    public HttpStatus shareSpace(@RequestBody ShareSpaceDto dto) {
        spaceService.shareSpace(dto);
        return HttpStatus.OK;
    }

    @PutMapping(params = {"spaceId", "userId"})
    public void assignSpaceToUser(@RequestParam UUID spaceId, @RequestParam UUID userId) {
        spaceService.assignSpaceToUser(spaceId, userId);
    }

    @DeleteMapping(params = {"spaceId", "userId"})
    public void unassignedSpaceFromUser(@RequestParam UUID spaceId, @RequestParam UUID userId) {
        spaceService.unassignedSpaceFromUser(spaceId, userId);
    }

    @PutMapping(params = {"spaceId", "groupId"})
    public void assignSpaceToGroup(@RequestParam UUID spaceId, @RequestParam UUID groupId) {
        spaceService.assignSpaceToGroup(spaceId, groupId);
    }


    @DeleteMapping(value = "/{spaceId}", params = "cascade")
    public void deleteSpace(@PathVariable UUID spaceId, @RequestParam Boolean cascade) {
        if (cascade) {
            spaceService.deleteSpaceWithDevices(spaceId);
        } else {
            spaceService.deleteSpace(spaceId);
        }
    }

}
