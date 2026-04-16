package ru.ssau.controller;

import ru.ssau.dto.*;
import ru.ssau.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/diagnoses")
public class DiagnosisController extends BaseController<DiagnosisDto, Long> {
    private final DiagnosisService service;
    public DiagnosisController(DiagnosisService service) { this.service = service; }
    @Override protected BaseService<DiagnosisDto, Long> getService() { return service; }
}