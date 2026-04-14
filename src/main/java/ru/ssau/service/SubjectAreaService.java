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
public class SubjectAreaService {
    private final SubjectAreaRepository repository;
    private final SubjectAreaMapper mapper;

    public List<SubjectAreaDto> findAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public SubjectAreaDto findById(Integer code) {
        return repository.findById(code).map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("SubjectArea not found: " + code));
    }

    @Transactional
    public SubjectAreaDto create(SubjectAreaDto dto) {
        SubjectArea entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    @Transactional
    public SubjectAreaDto update(Integer code, SubjectAreaDto dto) {
        if (!repository.existsById(code)) {
            throw new RuntimeException("SubjectArea not found: " + code);
        }
        SubjectArea entity = mapper.toEntity(dto);
        entity.setCode(code);
        return mapper.toDto(repository.save(entity));
    }

    @Transactional
    public void delete(Integer code) {
        repository.deleteById(code);
    }
}