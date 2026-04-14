package ru.ssau.controller;

import ru.ssau.dto.*;
import ru.ssau.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diagnosis-pupils")
@RequiredArgsConstructor
public class DiagnosisPupilController {
    private final DiagnosisPupilService service;

    @GetMapping
    public List<DiagnosisPupilDto> getAll() {
        return service.findAll();
    }

    @GetMapping("/{diagnosisCode}/{personalFileNumber}")
    public DiagnosisPupilDto getById(@PathVariable String diagnosisCode,
                                     @PathVariable Integer personalFileNumber) {
        return service.findById(diagnosisCode, personalFileNumber);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DiagnosisPupilDto create(@RequestBody DiagnosisPupilDto dto) {
        return service.create(dto);
    }

    @DeleteMapping("/{diagnosisCode}/{personalFileNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String diagnosisCode,
                       @PathVariable Integer personalFileNumber) {
        service.delete(diagnosisCode, personalFileNumber);
    }
}