package ru.ssau.controller;

import ru.ssau.dto.*;
import ru.ssau.service.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/individual-programs")
public class IndividualProgramController extends BaseController<IndividualProgramDto, Long> {
    private final IndividualProgramService service;
    public IndividualProgramController(IndividualProgramService service) { this.service = service; }
    @Override protected BaseService<IndividualProgramDto, Long> getService() { return service; }
}