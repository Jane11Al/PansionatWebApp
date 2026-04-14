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
public class PersonalResultTypeService {
    private final PersonalResultTypeRepository repository;
    private final PersonalResultTypeMapper mapper;
    private final PupilEducationRepository educationRepository;

    public List<PersonalResultTypeDto> findAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public PersonalResultTypeDto findById(Integer educationCode) {
        return repository.findById(educationCode).map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("PersonalResultType not found for education: " + educationCode));
    }

    @Transactional
    public PersonalResultTypeDto create(PersonalResultTypeDto dto) {
        PersonalResultType entity = mapper.toEntity(dto);
        entity.setEducation(educationRepository.getReferenceById(dto.getEducationCode()));
        return mapper.toDto(repository.save(entity));
    }

    @Transactional
    public PersonalResultTypeDto update(Integer educationCode, PersonalResultTypeDto dto) {
        if (!repository.existsById(educationCode)) {
            throw new RuntimeException("PersonalResultType not found for education: " + educationCode);
        }
        PersonalResultType entity = mapper.toEntity(dto);
        entity.setEducationCode(educationCode);
        entity.setEducation(educationRepository.getReferenceById(educationCode));
        return mapper.toDto(repository.save(entity));
    }

    @Transactional
    public void delete(Integer educationCode) {
        repository.deleteById(educationCode);
    }
}
