package ru.ssau.controller;

import ru.ssau.dto.*;
import ru.ssau.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personal-result-types")
@RequiredArgsConstructor
public class PersonalResultTypeController {
    private final PersonalResultTypeService service;

    @GetMapping
    public List<PersonalResultTypeDto> getAll() {
        return service.findAll();
    }

    @GetMapping("/{educationCode}")
    public PersonalResultTypeDto getById(@PathVariable Integer educationCode) {
        return service.findById(educationCode);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonalResultTypeDto create(@RequestBody PersonalResultTypeDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{educationCode}")
    public PersonalResultTypeDto update(@PathVariable Integer educationCode,
                                        @RequestBody PersonalResultTypeDto dto) {
        return service.update(educationCode, dto);
    }

    @DeleteMapping("/{educationCode}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer educationCode) {
        service.delete(educationCode);
    }
}
