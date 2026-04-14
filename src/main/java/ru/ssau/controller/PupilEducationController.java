package ru.ssau.controller;

import ru.ssau.dto.*;
import ru.ssau.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pupil-educations")
@RequiredArgsConstructor
public class PupilEducationController {
    private final PupilEducationService service;

    @GetMapping
    public List<PupilEducationDto> getAll() {
        return service.findAll();
    }

    @GetMapping("/{code}")
    public PupilEducationDto getById(@PathVariable Integer code) {
        return service.findById(code);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PupilEducationDto create(@RequestBody PupilEducationDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{code}")
    public PupilEducationDto update(@PathVariable Integer code,
                                    @RequestBody PupilEducationDto dto) {
        return service.update(code, dto);
    }

    @DeleteMapping("/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer code) {
        service.delete(code);
    }
}