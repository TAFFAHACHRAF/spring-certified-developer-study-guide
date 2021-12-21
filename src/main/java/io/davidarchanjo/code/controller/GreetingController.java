package io.davidarchanjo.code.controller;

import io.davidarchanjo.code.service.GreetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class GreetingController {

    private final GreetingService service;

    @GetMapping
    public String greeting() {
        return service.greet();
    }
}
