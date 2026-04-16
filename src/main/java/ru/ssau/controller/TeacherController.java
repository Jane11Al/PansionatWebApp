package ru.ssau.controller;

import ru.ssau.dto.*;
import ru.ssau.service.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController extends BaseController<TeacherDto, Long> {
    private final TeacherService service;
    public TeacherController(TeacherService service) { this.service = service; }
    @Override protected BaseService<TeacherDto, Long> getService() { return service; }
}