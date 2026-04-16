package ru.ssau.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ssau.dto.PersonalResultTypeDto;
import ru.ssau.entity.PersonalResultType;
import ru.ssau.exception.EntityNotFoundException;
import ru.ssau.mapper.PersonalResultTypeMapper;
import ru.ssau.repository.PersonalResultTypeRepository;
import ru.ssau.repository.PupilEducationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonalResultTypeService implements BaseService<PersonalResultTypeDto, Long> {

    private final PersonalResultTypeRepository repository;
    private final PupilEducationRepository educationRepository;
    private final PersonalResultTypeMapper mapper;

    @Override
    public List<PersonalResultTypeDto> findAll() {
        return mapper.toDtoList(repository.findAll());
    }

    @Override
    public PersonalResultTypeDto findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Личностные результаты", id));
    }

    @Override
    @Transactional
    public PersonalResultTypeDto create(PersonalResultTypeDto dto) {
        PersonalResultType entity = mapper.toEntity(dto);
        if (dto.getEducationId() != null) {
            entity.setEducation(educationRepository.getReferenceById(dto.getEducationId()));
        }
        return mapper.toDto(repository.save(entity));
    }

    @Override
    @Transactional
    public PersonalResultTypeDto update(Long id, PersonalResultTypeDto dto) {
        PersonalResultType existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Личностные результаты", id));
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
            throw new EntityNotFoundException("Личностные результаты", id);
        }
        repository.deleteById(id);
    }
}