package ru.ssau.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ssau.dto.SubjectResultTypeDto;
import ru.ssau.entity.SubjectResultType;
import ru.ssau.exception.EntityNotFoundException;
import ru.ssau.mapper.SubjectResultTypeMapper;
import ru.ssau.repository.SubjectResultTypeRepository;
import ru.ssau.repository.PupilEducationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectResultTypeService implements BaseService<SubjectResultTypeDto, Long> {

    private final SubjectResultTypeRepository repository;
    private final PupilEducationRepository educationRepository;
    private final SubjectResultTypeMapper mapper;

    @Override
    public List<SubjectResultTypeDto> findAll() {
        return mapper.toDtoList(repository.findAll());
    }

    @Override
    public SubjectResultTypeDto findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Предметные результаты", id));
    }

    @Override
    @Transactional
    public SubjectResultTypeDto create(SubjectResultTypeDto dto) {
        SubjectResultType entity = mapper.toEntity(dto);
        if (dto.getEducationId() != null) {
            entity.setEducation(educationRepository.getReferenceById(dto.getEducationId()));
        }
        return mapper.toDto(repository.save(entity));
    }

    @Override
    @Transactional
    public SubjectResultTypeDto update(Long id, SubjectResultTypeDto dto) {
        SubjectResultType existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Предметные результаты", id));
        mapper.updateEntity(dto, existing);
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
            throw new EntityNotFoundException("Предметные результаты", id);
        }
        repository.deleteById(id);
    }
}