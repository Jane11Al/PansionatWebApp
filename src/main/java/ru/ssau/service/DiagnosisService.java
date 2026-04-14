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
public class DiagnosisService {
    private final DiagnosisRepository repository;
    private final DiagnosisMapper mapper;

    public List<DiagnosisDto> findAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public DiagnosisDto findById(String code) {
        return repository.findById(code).map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Diagnosis not found with code: " + code));
    }

    @Transactional
    public DiagnosisDto create(DiagnosisDto dto) {
        Diagnosis entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    @Transactional
    public DiagnosisDto update(String code, DiagnosisDto dto) {
        if (!repository.existsById(code)) {
            throw new RuntimeException("Diagnosis not found with code: " + code);
        }
        Diagnosis entity = mapper.toEntity(dto);
        entity.setCode(code);
        return mapper.toDto(repository.save(entity));
    }

    @Transactional
    public void delete(String code) {
        repository.deleteById(code);
    }
}