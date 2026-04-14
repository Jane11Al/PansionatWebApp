package ru.ssau.controller;

import ru.ssau.dto.*;
import ru.ssau.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medical-equipments")
@RequiredArgsConstructor
public class MedicalEquipmentController {
    private final MedicalEquipmentService service;

    @GetMapping
    public List<MedicalEquipmentDto> getAll() {
        return service.findAll();
    }

    @GetMapping("/{inventoryNumber}")
    public MedicalEquipmentDto getById(@PathVariable Integer inventoryNumber) {
        return service.findById(inventoryNumber);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MedicalEquipmentDto create(@RequestBody MedicalEquipmentDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{inventoryNumber}")
    public MedicalEquipmentDto update(@PathVariable Integer inventoryNumber,
                                      @RequestBody MedicalEquipmentDto dto) {
        return service.update(inventoryNumber, dto);
    }

    @DeleteMapping("/{inventoryNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer inventoryNumber) {
        service.delete(inventoryNumber);
    }
}