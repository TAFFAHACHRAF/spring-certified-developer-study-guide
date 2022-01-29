package io.davidarchanjo.controller;

import io.davidarchanjo.model.dto.TodoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@RestController
@RequestMapping("api/todos")
@RequiredArgsConstructor
public class TodoController {

    @Value("${todo.url}")
    private String todoUrl;

    private final RestTemplate restTemplate;

    @GetMapping
    public Object find(@RequestParam(required = false) String task) {
        return Objects.nonNull(task)
            ? restTemplate.getForEntity(todoUrl + "?task={task}", TodoDTO.class, task)
            : restTemplate.getForObject(todoUrl, TodoDTO[].class);
    }

    @GetMapping("{id}")
    public Object find(@PathVariable Long id) {
        return restTemplate.getForEntity(todoUrl + "/{id}", TodoDTO.class, id);
    }

    @PostMapping
    public void create(@RequestBody TodoDTO dto, HttpServletResponse response) {
        HttpEntity<TodoDTO> request = new HttpEntity<>(dto);
        restTemplate.postForObject(todoUrl, request, Void.class);
        response.setStatus(HttpStatus.CREATED.value());
    }
}