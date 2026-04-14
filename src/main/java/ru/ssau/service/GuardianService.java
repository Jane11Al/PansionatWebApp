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
public class GuardianService {
    private final GuardianRepository repository;
    private final GuardianMapper mapper;

    public List<GuardianDto> findAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public GuardianDto findById(String fullName) {
        return repository.findById(fullName).map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Guardian not found: " + fullName));
    }

    @Transactional
    public GuardianDto create(GuardianDto dto) {
        Guardian entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    @Transactional
    public GuardianDto update(String fullName, GuardianDto dto) {
        if (!repository.existsById(fullName)) {
            throw new RuntimeException("Guardian not found: " + fullName);
        }
        Guardian entity = mapper.toEntity(dto);
        entity.setFullName(fullName);
        return mapper.toDto(repository.save(entity));
    }

    @Transactional
    public void delete(String fullName) {
        repository.deleteById(fullName);
    }
}
