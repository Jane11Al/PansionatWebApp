package ru.ssau.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ssau.dto.SubjectAreaDto;
import ru.ssau.entity.SubjectArea;
import ru.ssau.exception.EntityNotFoundException;
import ru.ssau.mapper.SubjectAreaMapper;
import ru.ssau.repository.SubjectAreaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectAreaService implements BaseService<SubjectAreaDto, Long> {

    private final SubjectAreaRepository repository;
    private final SubjectAreaMapper mapper;

    @Override
    public List<SubjectAreaDto> findAll() {
        return mapper.toDtoList(repository.findAll());
    }

    @Override
    public SubjectAreaDto findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Предметная область", id));
    }

    @Override
    @Transactional
    public SubjectAreaDto create(SubjectAreaDto dto) {
        SubjectArea entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    @Transactional
    public SubjectAreaDto update(Long id, SubjectAreaDto dto) {
        SubjectArea existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Предметная область", id));
        mapper.updateEntity(dto, existing);
        return mapper.toDto(repository.save(existing));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Предметная область", id);
        }
        repository.deleteById(id);
    }
}