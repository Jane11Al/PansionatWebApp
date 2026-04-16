package ru.ssau.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ssau.dto.ProgramDto;
import ru.ssau.entity.Program;
import ru.ssau.exception.EntityNotFoundException;
import ru.ssau.mapper.ProgramMapper;
import ru.ssau.repository.ProgramRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgramService implements BaseService<ProgramDto, Long> {

    private final ProgramRepository repository;
    private final ProgramMapper mapper;

    @Override
    public List<ProgramDto> findAll() {
        return mapper.toDtoList(repository.findAll());
    }

    @Override
    public ProgramDto findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Программа", id));
    }

    @Override
    @Transactional
    public ProgramDto create(ProgramDto dto) {
        Program entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    @Transactional
    public ProgramDto update(Long id, ProgramDto dto) {
        Program existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Программа", id));
        mapper.updateEntity(dto, existing);
        return mapper.toDto(repository.save(existing));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Программа", id);
        }
        repository.deleteById(id);
    }
}