package ru.ssau.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ssau.dto.SubjectResultDto;
import ru.ssau.entity.SubjectResult;
import ru.ssau.exception.EntityNotFoundException;
import ru.ssau.mapper.SubjectResultMapper;
import ru.ssau.repository.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectResultService implements BaseService<SubjectResultDto, Long> {

    private final SubjectResultRepository repository;
    private final SubjectRepository subjectRepository;
    private final PupilEducationRepository educationRepository;
    private final SubjectResultMapper mapper;

    @Override
    public List<SubjectResultDto> findAll() {
        return mapper.toDtoList(repository.findAll());
    }

    @Override
    public SubjectResultDto findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Результат изучения предмета", id));
    }

    @Override
    @Transactional
    public SubjectResultDto create(SubjectResultDto dto) {
        SubjectResult entity = mapper.toEntity(dto);
        if (dto.getSubjectId() != null) {
            entity.setSubject(subjectRepository.getReferenceById(dto.getSubjectId()));
        }
        if (dto.getEducationId() != null) {
            entity.setEducation(educationRepository.getReferenceById(dto.getEducationId()));
        }
        return mapper.toDto(repository.save(entity));
    }

    @Override
    @Transactional
    public SubjectResultDto update(Long id, SubjectResultDto dto) {
        SubjectResult existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Результат изучения предмета", id));
        mapper.updateEntity(dto, existing);
        if (dto.getSubjectId() != null) {
            existing.setSubject(subjectRepository.getReferenceById(dto.getSubjectId()));
        } else {
            existing.setSubject(null);
        }
        if (dto.getEducationId() != null) {
            existing.setEducation(educationRepository.getReferenceById(dto.getEducationId()));
        } else {
            existing.setEducation(null);
        }
        return mapper.toDto(repository.save(existing));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Результат изучения предмета", id);
        }
        repository.deleteById(id);
    }
}