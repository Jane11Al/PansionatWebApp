package ru.ssau.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ssau.dto.PupilDto;
import ru.ssau.entity.Pupil;
import ru.ssau.exception.EntityNotFoundException;
import ru.ssau.mapper.PupilMapper;
import ru.ssau.repository.PupilRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PupilService implements BaseService<PupilDto, Long> {

    private final PupilRepository repository;
    private final PupilMapper mapper;

    @Override
    public List<PupilDto> findAll() {
        return mapper.toDtoList(repository.findAll());
    }

    @Override
    public PupilDto findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Воспитанник", id));
    }

    @Override
    @Transactional
    public PupilDto create(PupilDto dto) {
        Pupil entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    @Transactional
    public PupilDto update(Long id, PupilDto dto) {
        Pupil existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Воспитанник", id));
        mapper.updateEntity(dto, existing);
        return mapper.toDto(repository.save(existing));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Воспитанник", id);
        }
        repository.deleteById(id);
    }
}