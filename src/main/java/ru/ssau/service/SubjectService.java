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
public class SubjectService {
    private final SubjectRepository repository;
    private final SubjectMapper mapper;
    private final SubjectAreaRepository subjectAreaRepository;
    private final IndividualProgramRepository individualProgramRepository;

    public List<SubjectDto> findAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public SubjectDto findById(String name, Integer subjectAreaCode) {
        SubjectId id = new SubjectId(name, subjectAreaCode);
        return repository.findById(id).map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Subject not found"));
    }

    @Transactional
    public SubjectDto create(SubjectDto dto) {
        Subject entity = mapper.toEntity(dto);
        entity.setSubjectArea(subjectAreaRepository.getReferenceById(dto.getSubjectAreaCode()));
        if (dto.getProgramCode() != null && dto.getYear() != null) {
            IndividualProgramId ipId = new IndividualProgramId(dto.getYear(), dto.getProgramCode());
            entity.setIndividualProgram(individualProgramRepository.getReferenceById(ipId));
        }
        return mapper.toDto(repository.save(entity));
    }

    @Transactional
    public SubjectDto update(String name, Integer subjectAreaCode, SubjectDto dto) {
        SubjectId id = new SubjectId(name, subjectAreaCode);
        if (!repository.existsById(id)) {
            throw new RuntimeException("Subject not found");
        }
        Subject entity = mapper.toEntity(dto);
        entity.getId().setName(name);
        entity.getId().setSubjectAreaCode(subjectAreaCode);
        entity.setSubjectArea(subjectAreaRepository.getReferenceById(subjectAreaCode));
        if (dto.getProgramCode() != null && dto.getYear() != null) {
            IndividualProgramId ipId = new IndividualProgramId(dto.getYear(), dto.getProgramCode());
            entity.setIndividualProgram(individualProgramRepository.getReferenceById(ipId));
        }
        return mapper.toDto(repository.save(entity));
    }

    @Transactional
    public void delete(String name, Integer subjectAreaCode) {
        SubjectId id = new SubjectId(name, subjectAreaCode);
        repository.deleteById(id);
    }
}