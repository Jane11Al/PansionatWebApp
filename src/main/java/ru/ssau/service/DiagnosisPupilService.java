package ru.ssau.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ssau.dto.DiagnosisPupilDto;
import ru.ssau.entity.DiagnosisPupil;
import ru.ssau.exception.EntityNotFoundException;
import ru.ssau.mapper.DiagnosisPupilMapper;
import ru.ssau.repository.DiagnosisPupilRepository;
import ru.ssau.repository.DiagnosisRepository;
import ru.ssau.repository.PupilRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiagnosisPupilService implements BaseService<DiagnosisPupilDto, Long> {

    private final DiagnosisPupilRepository repository;
    private final DiagnosisRepository diagnosisRepository;
    private final PupilRepository pupilRepository;
    private final DiagnosisPupilMapper mapper;

    @Override
    public List<DiagnosisPupilDto> findAll() {
        return mapper.toDtoList(repository.findAll());
    }

    @Override
    public DiagnosisPupilDto findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Связь диагноз-воспитанник", id));
    }

    @Override
    @Transactional
    public DiagnosisPupilDto create(DiagnosisPupilDto dto) {
        DiagnosisPupil entity = mapper.toEntity(dto);
        if (dto.getDiagnosisId() != null) {
            entity.setDiagnosis(diagnosisRepository.getReferenceById(dto.getDiagnosisId()));
        }
        if (dto.getPupilId() != null) {
            entity.setPupil(pupilRepository.getReferenceById(dto.getPupilId()));
        }
        return mapper.toDto(repository.save(entity));
    }

    @Override
    @Transactional
    public DiagnosisPupilDto update(Long id, DiagnosisPupilDto dto) {
        DiagnosisPupil existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Связь диагноз-воспитанник", id));
        mapper.updateEntity(dto, existing);
        if (dto.getDiagnosisId() != null) {
            existing.setDiagnosis(diagnosisRepository.getReferenceById(dto.getDiagnosisId()));
        }
        if (dto.getPupilId() != null) {
            existing.setPupil(pupilRepository.getReferenceById(dto.getPupilId()));
        }
        return mapper.toDto(repository.save(existing));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Связь диагноз-воспитанник", id);
        }
        repository.deleteById(id);
    }
}