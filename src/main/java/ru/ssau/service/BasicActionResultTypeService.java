package ru.ssau.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ssau.dto.BasicActionResultTypeDto;
import ru.ssau.entity.BasicActionResultType;
import ru.ssau.exception.EntityNotFoundException;
import ru.ssau.mapper.BasicActionResultTypeMapper;
import ru.ssau.repository.BasicActionResultTypeRepository;
import ru.ssau.repository.PupilEducationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BasicActionResultTypeService implements BaseService<BasicActionResultTypeDto, Long> {

    private final BasicActionResultTypeRepository repository;
    private final PupilEducationRepository educationRepository;
    private final BasicActionResultTypeMapper mapper;

    @Override
    public List<BasicActionResultTypeDto> findAll() {
        return mapper.toDtoList(repository.findAll());
    }

    @Override
    public BasicActionResultTypeDto findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Результаты базовых учебных действий", id));
    }

    @Override
    @Transactional
    public BasicActionResultTypeDto create(BasicActionResultTypeDto dto) {
        BasicActionResultType entity = mapper.toEntity(dto);
        if (dto.getEducationId() != null) {
            entity.setEducation(educationRepository.getReferenceById(dto.getEducationId()));
        }
        return mapper.toDto(repository.save(entity));
    }

    @Override
    @Transactional
    public BasicActionResultTypeDto update(Long id, BasicActionResultTypeDto dto) {
        BasicActionResultType existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Результаты базовых учебных действий", id));
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
            throw new EntityNotFoundException("Результаты базовых учебных действий", id);
        }
        repository.deleteById(id);
    }
}