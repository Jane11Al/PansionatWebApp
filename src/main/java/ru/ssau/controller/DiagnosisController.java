package ru.ssau.controller;

import ru.ssau.dto.*;
import ru.ssau.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diagnoses")
@RequiredArgsConstructor
public class DiagnosisController {
    private final DiagnosisService service;

    @GetMapping
    public List<DiagnosisDto> getAll() {
        return service.findAll();
    }

    @GetMapping("/{code}")
    public DiagnosisDto getById(@PathVariable String code) {
        return service.findById(code);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DiagnosisDto create(@RequestBody DiagnosisDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{code}")
    public DiagnosisDto update(@PathVariable String code, @RequestBody DiagnosisDto dto) {
        return service.update(code, dto);
    }

    @DeleteMapping("/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String code) {
        service.delete(code);
    }
}