package ru.ssau.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ssau.dto.DiagnosisDto;
import ru.ssau.entity.Diagnosis;
import ru.ssau.exception.EntityNotFoundException;
import ru.ssau.mapper.DiagnosisMapper;
import ru.ssau.repository.DiagnosisRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiagnosisService implements BaseService<DiagnosisDto, Long> {

    private final DiagnosisRepository repository;
    private final DiagnosisMapper mapper;

    @Override
    public List<DiagnosisDto> findAll() {
        return mapper.toDtoList(repository.findAll());
    }

    @Override
    public DiagnosisDto findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Диагноз", id));
    }

    @Override
    @Transactional
    public DiagnosisDto create(DiagnosisDto dto) {
        Diagnosis entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    @Transactional
    public DiagnosisDto update(Long id, DiagnosisDto dto) {
        Diagnosis existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Диагноз", id));
        mapper.updateEntity(dto, existing);
        return mapper.toDto(repository.save(existing));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Диагноз", id);
        }
        repository.deleteById(id);
    }
}