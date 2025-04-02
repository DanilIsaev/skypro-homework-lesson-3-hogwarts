package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("port")
public class InfoController {

    @Autowired
    Environment environment;

    @GetMapping
    public String info() {
        return environment.getProperty("local.server.port");
    }
}
