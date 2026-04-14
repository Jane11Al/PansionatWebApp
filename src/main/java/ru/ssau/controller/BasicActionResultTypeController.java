package ru.ssau.controller;

import ru.ssau.dto.*;
import ru.ssau.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/basic-action-result-types")
@RequiredArgsConstructor
public class BasicActionResultTypeController {
    private final BasicActionResultTypeService service;

    @GetMapping
    public List<BasicActionResultTypeDto> getAll() {
        return service.findAll();
    }

    @GetMapping("/{educationCode}")
    public BasicActionResultTypeDto getById(@PathVariable Integer educationCode) {
        return service.findById(educationCode);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BasicActionResultTypeDto create(@RequestBody BasicActionResultTypeDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{educationCode}")
    public BasicActionResultTypeDto update(@PathVariable Integer educationCode,
                                           @RequestBody BasicActionResultTypeDto dto) {
        return service.update(educationCode, dto);
    }

    @DeleteMapping("/{educationCode}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer educationCode) {
        service.delete(educationCode);
    }
}