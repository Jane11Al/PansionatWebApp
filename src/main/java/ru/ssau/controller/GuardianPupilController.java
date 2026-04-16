package ru.ssau.controller;

import ru.ssau.dto.*;
import ru.ssau.service.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/guardian-pupils")
public class GuardianPupilController extends BaseController<GuardianPupilDto, Long> {
    private final GuardianPupilService service;
    public GuardianPupilController(GuardianPupilService service) { this.service = service; }
    @Override protected BaseService<GuardianPupilDto, Long> getService() { return service; }
}