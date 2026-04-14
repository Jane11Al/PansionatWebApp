package ru.ssau.service;

import ru.ssau.dto.*;
import ru.ssau.entity.*;
import ru.ssau.mapstruct.*;
import ru.ssau.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgramService {
    private final ProgramRepository repository;
    private final ProgramMapper mapper;

    public List<ProgramDto> findAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public ProgramDto findById(Integer code) {
        return repository.findById(code).map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Program not found: " + code));
    }

    @Transactional
    public ProgramDto create(ProgramDto dto) {
        Program entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    @Transactional
    public ProgramDto update(Integer code, ProgramDto dto) {
        if (!repository.existsById(code)) {
            throw new RuntimeException("Program not found: " + code);
        }
        Program entity = mapper.toEntity(dto);
        entity.setCode(code);
        return mapper.toDto(repository.save(entity));
    }

    @Transactional
    public void delete(Integer code) {
        repository.deleteById(code);
    }
}
