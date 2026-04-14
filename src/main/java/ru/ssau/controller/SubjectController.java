package ru.ssau.controller;

import ru.ssau.dto.*;
import ru.ssau.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectService service;

    @GetMapping
    public List<SubjectDto> getAll() {
        return service.findAll();
    }

    @GetMapping("/{name}/{subjectAreaCode}")
    public SubjectDto getById(@PathVariable String name,
                              @PathVariable Integer subjectAreaCode) {
        return service.findById(name, subjectAreaCode);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SubjectDto create(@RequestBody SubjectDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{name}/{subjectAreaCode}")
    public SubjectDto update(@PathVariable String name,
                             @PathVariable Integer subjectAreaCode,
                             @RequestBody SubjectDto dto) {
        return service.update(name, subjectAreaCode, dto);
    }

    @DeleteMapping("/{name}/{subjectAreaCode}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String name,
                       @PathVariable Integer subjectAreaCode) {
        service.delete(name, subjectAreaCode);
    }
}