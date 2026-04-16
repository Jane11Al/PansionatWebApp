package ru.ssau.controller;

import ru.ssau.dto.*;
import ru.ssau.service.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController extends BaseController<SubjectDto, Long> {
    private final SubjectService service;
    public SubjectController(SubjectService service) { this.service = service; }
    @Override protected BaseService<SubjectDto, Long> getService() { return service; }
}