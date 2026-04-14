package ru.ssau.controller;

import ru.ssau.dto.*;
import ru.ssau.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService service;

    @GetMapping
    public List<TeacherDto> getAll() {
        return service.findAll();
    }

    @GetMapping("/{fullName}")
    public TeacherDto getById(@PathVariable String fullName) {
        return service.findById(fullName);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeacherDto create(@RequestBody TeacherDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{fullName}")
    public TeacherDto update(@PathVariable String fullName, @RequestBody TeacherDto dto) {
        return service.update(fullName, dto);
    }

    @DeleteMapping("/{fullName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String fullName) {
        service.delete(fullName);
    }
}
