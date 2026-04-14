package ru.ssau.controller;

import ru.ssau.dto.*;
import ru.ssau.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guardian-pupils")
@RequiredArgsConstructor
public class GuardianPupilController {
    private final GuardianPupilService service;

    @GetMapping
    public List<GuardianPupilDto> getAll() {
        return service.findAll();
    }

    @GetMapping("/{personalFileNumber}")
    public GuardianPupilDto getById(@PathVariable Integer personalFileNumber) {
        return service.findById(personalFileNumber);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GuardianPupilDto create(@RequestBody GuardianPupilDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{personalFileNumber}")
    public GuardianPupilDto update(@PathVariable Integer personalFileNumber,
                                   @RequestBody GuardianPupilDto dto) {
        return service.update(personalFileNumber, dto);
    }

    @DeleteMapping("/{personalFileNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer personalFileNumber) {
        service.delete(personalFileNumber);
    }
}