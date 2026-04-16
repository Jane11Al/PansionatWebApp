package ru.ssau.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ssau.dto.GuardianDto;
import ru.ssau.entity.Guardian;
import ru.ssau.exception.EntityNotFoundException;
import ru.ssau.mapper.GuardianMapper;
import ru.ssau.repository.GuardianRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GuardianService implements BaseService<GuardianDto, Long> {

    private final GuardianRepository repository;
    private final GuardianMapper mapper;

    @Override
    public List<GuardianDto> findAll() {
        return mapper.toDtoList(repository.findAll());
    }

    @Override
    public GuardianDto findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Опекун", id));
    }

    @Override
    @Transactional
    public GuardianDto create(GuardianDto dto) {
        Guardian entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    @Transactional
    public GuardianDto update(Long id, GuardianDto dto) {
        Guardian existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Опекун", id));
        mapper.updateEntity(dto, existing);
        return mapper.toDto(repository.save(existing));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Опекун", id);
        }
        repository.deleteById(id);
    }
}