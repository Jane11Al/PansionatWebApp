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
public class SubjectResultTypeService {
    private final SubjectResultTypeRepository repository;
    private final SubjectResultTypeMapper mapper;
    private final PupilEducationRepository educationRepository;

    public List<SubjectResultTypeDto> findAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public SubjectResultTypeDto findById(Integer educationCode) {
        return repository.findById(educationCode).map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("SubjectResultType not found for education: " + educationCode));
    }

    @Transactional
    public SubjectResultTypeDto create(SubjectResultTypeDto dto) {
        SubjectResultType entity = mapper.toEntity(dto);
        entity.setEducation(educationRepository.getReferenceById(dto.getEducationCode()));
        return mapper.toDto(repository.save(entity));
    }

    @Transactional
    public SubjectResultTypeDto update(Integer educationCode, SubjectResultTypeDto dto) {
        if (!repository.existsById(educationCode)) {
            throw new RuntimeException("SubjectResultType not found for education: " + educationCode);
        }
        SubjectResultType entity = mapper.toEntity(dto);
        entity.setEducationCode(educationCode);
        entity.setEducation(educationRepository.getReferenceById(educationCode));
        return mapper.toDto(repository.save(entity));
    }

    @Transactional
    public void delete(Integer educationCode) {
        repository.deleteById(educationCode);
    }
}