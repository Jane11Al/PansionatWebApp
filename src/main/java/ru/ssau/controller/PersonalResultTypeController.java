package ru.ssau.controller;

import ru.ssau.dto.*;
import ru.ssau.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/personal-result-types")
public class PersonalResultTypeController extends BaseController<PersonalResultTypeDto, Long> {
    private final PersonalResultTypeService service;
    public PersonalResultTypeController(PersonalResultTypeService service) { this.service = service; }
    @Override protected BaseService<PersonalResultTypeDto, Long> getService() { return service; }
}