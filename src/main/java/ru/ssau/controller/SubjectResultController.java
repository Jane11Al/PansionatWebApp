package ru.ssau.controller;

import ru.ssau.dto.*;
import ru.ssau.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subject-results")
@RequiredArgsConstructor
public class SubjectResultController {
    private final SubjectResultService service;

    @GetMapping
    public List<SubjectResultDto> getAll() {
        return service.findAll();
    }

    @GetMapping("/{subjectName}/{subjectAreaCode}/{educationCode}")
    public SubjectResultDto getById(@PathVariable String subjectName,
                                    @PathVariable Integer subjectAreaCode,
                                    @PathVariable Integer educationCode) {
        return service.findById(subjectName, subjectAreaCode, educationCode);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SubjectResultDto create(@RequestBody SubjectResultDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{subjectName}/{subjectAreaCode}/{educationCode}")
    public SubjectResultDto update(@PathVariable String subjectName,
                                   @PathVariable Integer subjectAreaCode,
                                   @PathVariable Integer educationCode,
                                   @RequestBody SubjectResultDto dto) {
        return service.update(subjectName, subjectAreaCode, educationCode, dto);
    }

    @DeleteMapping("/{subjectName}/{subjectAreaCode}/{educationCode}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String subjectName,
                       @PathVariable Integer subjectAreaCode,
                       @PathVariable Integer educationCode) {
        service.delete(subjectName, subjectAreaCode, educationCode);
    }
}