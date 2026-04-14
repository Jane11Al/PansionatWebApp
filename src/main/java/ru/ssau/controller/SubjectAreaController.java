package ru.ssau.controller;

import ru.ssau.dto.*;
import ru.ssau.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subject-areas")
@RequiredArgsConstructor
public class SubjectAreaController {
    private final SubjectAreaService service;

    @GetMapping
    public List<SubjectAreaDto> getAll() {
        return service.findAll();
    }

    @GetMapping("/{code}")
    public SubjectAreaDto getById(@PathVariable Integer code) {
        return service.findById(code);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SubjectAreaDto create(@RequestBody SubjectAreaDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{code}")
    public SubjectAreaDto update(@PathVariable Integer code, @RequestBody SubjectAreaDto dto) {
        return service.update(code, dto);
    }

    @DeleteMapping("/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer code) {
        service.delete(code);
    }
}