package com.codecool.homee_backend.controller;

import com.codecool.homee_backend.repository.DeviceRepository;
import com.codecool.homee_backend.repository.HomeeUserRepository;
import com.codecool.homee_backend.repository.SpaceRepository;
import com.codecool.homee_backend.service.HomeeUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@AllArgsConstructor
@Slf4j
public class ForTestingController {

    private final HomeeUserService homeeUserService;
    private final HomeeUserRepository homeeUserRepository;
    private final SpaceRepository spaceRepository;
    private final DeviceRepository deviceRepository;

    @GetMapping("/")
    public String testSomething() {
//        HomeeUser homeeUser = new HomeeUser();
//        homeeUser.setUsername("john_doe");
//        homeeUser.setPassword("password123");
//        homeeUser.setEmail("john_doe@example.com");
//        homeeUserRepository.save(homeeUser);
//
//        Space space = new Space();
//        space.setName("test");
//
//        space.getHomeeUsers().add(homeeUser);
//        spaceRepository.save(space);
//
//
//        HomeeUser homeeUser = homeeUserRepository.findHomeeUserById(1L);
//        log.info(homeeUser.toString());
//        Space space = spaceRepository.findSpaceById(1L);
//        log.info(space.toString());
//
//        homeeUser.addSpace(space);
//        homeeUserRepository.save(homeeUser);
//
//        log.info(spaceRepository.findByHomeeUserId(1L).toString());

//        Device device = new Device();
//        device.setName("test");
//        device.setModel("test");
//
//
//        Device device2 = new Device();
//        device2.setName("test");
//        device2.setModel("test");
//
//        Space space = spaceRepository.findSpaceById(1L);
//
//        device2.setSpace(space);
//
//        deviceRepository.save(device2);
//
//        Space space2 = spaceRepository.findSpaceById(1L);
//        log.info(space2.toString());

        return "index";
    }

}
