package ru.ssau.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ssau.dto.SubjectDto;
import ru.ssau.entity.Subject;
import ru.ssau.exception.EntityNotFoundException;
import ru.ssau.mapper.SubjectMapper;
import ru.ssau.repository.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService implements BaseService<SubjectDto, Long> {

    private final SubjectRepository repository;
    private final SubjectAreaRepository subjectAreaRepository;
    private final IndividualProgramRepository individualProgramRepository;
    private final SubjectMapper mapper;

    @Override
    public List<SubjectDto> findAll() {
        return mapper.toDtoList(repository.findAll());
    }

    @Override
    public SubjectDto findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Учебный предмет", id));
    }

    @Override
    @Transactional
    public SubjectDto create(SubjectDto dto) {
        Subject entity = mapper.toEntity(dto);
        if (dto.getSubjectAreaId() != null) {
            entity.setSubjectArea(subjectAreaRepository.getReferenceById(dto.getSubjectAreaId()));
        }
        if (dto.getIndividualProgramId() != null) {
            entity.setIndividualProgram(individualProgramRepository.getReferenceById(dto.getIndividualProgramId()));
        }
        return mapper.toDto(repository.save(entity));
    }

    @Override
    @Transactional
    public SubjectDto update(Long id, SubjectDto dto) {
        Subject existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Учебный предмет", id));
        mapper.updateEntity(dto, existing);
        if (dto.getSubjectAreaId() != null) {
            existing.setSubjectArea(subjectAreaRepository.getReferenceById(dto.getSubjectAreaId()));
        } else {
            existing.setSubjectArea(null);
        }
        if (dto.getIndividualProgramId() != null) {
            existing.setIndividualProgram(individualProgramRepository.getReferenceById(dto.getIndividualProgramId()));
        } else {
            existing.setIndividualProgram(null);
        }
        return mapper.toDto(repository.save(existing));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Учебный предмет", id);
        }
        repository.deleteById(id);
    }
}