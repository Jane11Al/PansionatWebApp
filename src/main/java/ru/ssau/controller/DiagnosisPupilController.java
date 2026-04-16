package ru.ssau.controller;

import ru.ssau.dto.*;
import ru.ssau.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/diagnosis-pupils")
public class DiagnosisPupilController extends BaseController<DiagnosisPupilDto, Long> {
    private final DiagnosisPupilService service;
    public DiagnosisPupilController(DiagnosisPupilService service) { this.service = service; }
    @Override protected BaseService<DiagnosisPupilDto, Long> getService() { return service; }
}