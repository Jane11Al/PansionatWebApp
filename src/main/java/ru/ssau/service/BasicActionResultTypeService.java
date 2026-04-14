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
public class BasicActionResultTypeService {
    private final BasicActionResultTypeRepository repository;
    private final BasicActionResultTypeMapper mapper;
    private final PupilEducationRepository educationRepository;

    public List<BasicActionResultTypeDto> findAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public BasicActionResultTypeDto findById(Integer educationCode) {
        return repository.findById(educationCode).map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("BasicActionResultType not found for education: " + educationCode));
    }

    @Transactional
    public BasicActionResultTypeDto create(BasicActionResultTypeDto dto) {
        BasicActionResultType entity = mapper.toEntity(dto);
        entity.setEducation(educationRepository.getReferenceById(dto.getEducationCode()));
        return mapper.toDto(repository.save(entity));
    }

    @Transactional
    public BasicActionResultTypeDto update(Integer educationCode, BasicActionResultTypeDto dto) {
        if (!repository.existsById(educationCode)) {
            throw new RuntimeException("BasicActionResultType not found for education: " + educationCode);
        }
        BasicActionResultType entity = mapper.toEntity(dto);
        entity.setEducationCode(educationCode);
        entity.setEducation(educationRepository.getReferenceById(educationCode));
        return mapper.toDto(repository.save(entity));
    }

    @Transactional
    public void delete(Integer educationCode) {
        repository.deleteById(educationCode);
    }
}
