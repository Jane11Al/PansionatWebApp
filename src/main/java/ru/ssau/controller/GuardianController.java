package ru.ssau.controller;

import ru.ssau.dto.*;
import ru.ssau.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/guardians")
public class GuardianController extends BaseController<GuardianDto, Long> {
    private final GuardianService service;
    public GuardianController(GuardianService service) { this.service = service; }
    @Override protected BaseService<GuardianDto, Long> getService() { return service; }
}