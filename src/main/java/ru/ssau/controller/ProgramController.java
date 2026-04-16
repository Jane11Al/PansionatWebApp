package ru.ssau.controller;

import ru.ssau.dto.*;
import ru.ssau.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/programs")
public class ProgramController extends BaseController<ProgramDto, Long> {
    private final ProgramService service;
    public ProgramController(ProgramService service) { this.service = service; }
    @Override protected BaseService<ProgramDto, Long> getService() { return service; }
}