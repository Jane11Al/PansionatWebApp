package ru.ssau.controller;

import ru.ssau.dto.*;
import ru.ssau.service.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/medical-equipments")
public class MedicalEquipmentController extends BaseController<MedicalEquipmentDto, Long> {
    private final MedicalEquipmentService service;
    public MedicalEquipmentController(MedicalEquipmentService service) { this.service = service; }
    @Override protected BaseService<MedicalEquipmentDto, Long> getService() { return service; }
}