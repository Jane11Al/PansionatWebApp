package ru.ssau.controller;

import ru.ssau.dto.*;
import ru.ssau.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subject-result-types")
@RequiredArgsConstructor
public class SubjectResultTypeController {
    private final SubjectResultTypeService service;

    @GetMapping
    public List<SubjectResultTypeDto> getAll() {
        return service.findAll();
    }

    @GetMapping("/{educationCode}")
    public SubjectResultTypeDto getById(@PathVariable Integer educationCode) {
        return service.findById(educationCode);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SubjectResultTypeDto create(@RequestBody SubjectResultTypeDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{educationCode}")
    public SubjectResultTypeDto update(@PathVariable Integer educationCode,
                                       @RequestBody SubjectResultTypeDto dto) {
        return service.update(educationCode, dto);
    }

    @DeleteMapping("/{educationCode}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer educationCode) {
        service.delete(educationCode);
    }
}