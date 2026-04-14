package ru.ssau.controller;

import ru.ssau.dto.*;
import ru.ssau.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guardians")
@RequiredArgsConstructor
public class GuardianController {
    private final GuardianService service;

    @GetMapping
    public List<GuardianDto> getAll() {
        return service.findAll();
    }

    @GetMapping("/{fullName}")
    public GuardianDto getById(@PathVariable String fullName) {
        return service.findById(fullName);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GuardianDto create(@RequestBody GuardianDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{fullName}")
    public GuardianDto update(@PathVariable String fullName, @RequestBody GuardianDto dto) {
        return service.update(fullName, dto);
    }

    @DeleteMapping("/{fullName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String fullName) {
        service.delete(fullName);
    }
}