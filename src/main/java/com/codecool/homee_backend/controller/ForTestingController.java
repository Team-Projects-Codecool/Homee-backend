package com.codecool.homee_backend.controller;

import com.codecool.homee_backend.entity.HomeeUser;
import com.codecool.homee_backend.service.HomeeUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class ForTestingController {

    private final HomeeUserService homeeUserService;

    @GetMapping("/")
    public String testSomething() {
        HomeeUser homeeUser = new HomeeUser(5L, "test", "test", "test123", LocalDateTime.now(), LocalDateTime.now(), "testName", "testLastName", "testAbout");
        homeeUserService.addHomeeUser(homeeUser);
        return "index";
    }

}
