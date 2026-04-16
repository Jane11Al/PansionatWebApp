package ru.ssau.controller;

import ru.ssau.dto.*;
import ru.ssau.service.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/basic-action-result-types")
public class BasicActionResultTypeController extends BaseController<BasicActionResultTypeDto, Long> {
    private final BasicActionResultTypeService service;
    public BasicActionResultTypeController(BasicActionResultTypeService service) { this.service = service; }
    @Override protected BaseService<BasicActionResultTypeDto, Long> getService() { return service; }
}