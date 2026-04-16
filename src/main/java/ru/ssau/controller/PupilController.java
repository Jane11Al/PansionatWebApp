package ru.ssau.controller;

import org.springframework.web.bind.annotation.*;
import ru.ssau.dto.*;
import ru.ssau.service.*;

@RestController
@RequestMapping("/api/pupils")
public class PupilController extends BaseController<PupilDto, Long> {
    private final PupilService service;
    public PupilController(PupilService service) { this.service = service; }
    @Override protected BaseService<PupilDto, Long> getService() { return service; }
}
