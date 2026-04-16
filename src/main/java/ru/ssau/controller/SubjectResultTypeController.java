package ru.ssau.controller;

import ru.ssau.dto.*;
import ru.ssau.service.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subject-result-types")
public class SubjectResultTypeController extends BaseController<SubjectResultTypeDto, Long> {
    private final SubjectResultTypeService service;
    public SubjectResultTypeController(SubjectResultTypeService service) { this.service = service; }
    @Override protected BaseService<SubjectResultTypeDto, Long> getService() { return service; }
}
