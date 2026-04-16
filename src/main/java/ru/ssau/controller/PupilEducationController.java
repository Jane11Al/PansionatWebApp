package ru.ssau.controller;

import ru.ssau.dto.*;
import ru.ssau.service.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pupil-educations")
public class PupilEducationController extends BaseController<PupilEducationDto, Long> {
    private final PupilEducationService service;
    public PupilEducationController(PupilEducationService service) { this.service = service; }
    @Override protected BaseService<PupilEducationDto, Long> getService() { return service; }
}