package ru.ssau.controller;

import ru.ssau.dto.*;
import ru.ssau.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/individual-programs")
@RequiredArgsConstructor
public class IndividualProgramController {
    private final IndividualProgramService service;

    @GetMapping
    public List<IndividualProgramDto> getAll() {
        return service.findAll();
    }

    @GetMapping("/{year}/{programCode}")
    public IndividualProgramDto getById(@PathVariable Short year,
                                        @PathVariable Integer programCode) {
        return service.findById(year, programCode);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IndividualProgramDto create(@RequestBody IndividualProgramDto dto) {
        return service.create(dto);
    }

    @DeleteMapping("/{year}/{programCode}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Short year,
                       @PathVariable Integer programCode) {
        service.delete(year, programCode);
    }
}