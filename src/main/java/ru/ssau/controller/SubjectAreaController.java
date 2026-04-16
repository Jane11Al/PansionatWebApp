package ru.ssau.controller;

import ru.ssau.dto.*;
import ru.ssau.service.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subject-areas")
public class SubjectAreaController extends BaseController<SubjectAreaDto, Long> {
    private final SubjectAreaService service;
    public SubjectAreaController(SubjectAreaService service) { this.service = service; }
    @Override protected BaseService<SubjectAreaDto, Long> getService() { return service; }
}