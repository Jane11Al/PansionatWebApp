package ru.ssau.controller;

import ru.ssau.dto.*;
import ru.ssau.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pupils")
@RequiredArgsConstructor
public class PupilController {
    private final PupilService service;

    @GetMapping
    public List<PupilDto> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public PupilDto getById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PupilDto create(@RequestBody PupilDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public PupilDto update(@PathVariable Integer id, @RequestBody PupilDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
