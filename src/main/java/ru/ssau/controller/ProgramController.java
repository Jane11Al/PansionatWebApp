package ru.ssau.controller;

import ru.ssau.dto.*;
import ru.ssau.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/programs")
@RequiredArgsConstructor
public class ProgramController {
    private final ProgramService service;

    @GetMapping
    public List<ProgramDto> getAll() {
        return service.findAll();
    }

    @GetMapping("/{code}")
    public ProgramDto getById(@PathVariable Integer code) {
        return service.findById(code);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProgramDto create(@RequestBody ProgramDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{code}")
    public ProgramDto update(@PathVariable Integer code, @RequestBody ProgramDto dto) {
        return service.update(code, dto);
    }

    @DeleteMapping("/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer code) {
        service.delete(code);
    }
}