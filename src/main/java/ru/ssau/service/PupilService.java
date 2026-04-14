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
public class PupilService {
    private final PupilRepository repository;
    private final PupilMapper mapper;

    public List<PupilDto> findAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public PupilDto findById(Integer id) {
        return repository.findById(id).map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Pupil not found with id: " + id));
    }

    @Transactional
    public PupilDto create(PupilDto dto) {
        Pupil entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    @Transactional
    public PupilDto update(Integer id, PupilDto dto) {
        Pupil existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pupil not found with id: " + id));
        // MapStruct не обновляет существующую сущность, поэтому либо используем @MappingTarget,
        // либо вручную обновляем поля. Для простоты создадим новую сущность с тем же ID.
        Pupil updated = mapper.toEntity(dto);
        updated.setPersonalFileNumber(id);
        return mapper.toDto(repository.save(updated));
    }

    @Transactional
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
