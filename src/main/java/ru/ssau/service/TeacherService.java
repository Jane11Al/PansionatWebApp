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
public class TeacherService {
    private final TeacherRepository repository;
    private final TeacherMapper mapper;

    public List<TeacherDto> findAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public TeacherDto findById(String fullName) {
        return repository.findById(fullName).map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Teacher not found: " + fullName));
    }

    @Transactional
    public TeacherDto create(TeacherDto dto) {
        Teacher entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    @Transactional
    public TeacherDto update(String fullName, TeacherDto dto) {
        if (!repository.existsById(fullName)) {
            throw new RuntimeException("Teacher not found: " + fullName);
        }
        Teacher entity = mapper.toEntity(dto);
        entity.setFullName(fullName);
        return mapper.toDto(repository.save(entity));
    }

    @Transactional
    public void delete(String fullName) {
        repository.deleteById(fullName);
    }
}