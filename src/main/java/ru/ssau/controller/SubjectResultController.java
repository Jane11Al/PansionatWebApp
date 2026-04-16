package ru.ssau.controller;

import ru.ssau.dto.*;
import ru.ssau.service.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subject-results")
public class SubjectResultController extends BaseController<SubjectResultDto, Long> {
    private final SubjectResultService service;
    public SubjectResultController(SubjectResultService service) { this.service = service; }
    @Override protected BaseService<SubjectResultDto, Long> getService() { return service; }
}