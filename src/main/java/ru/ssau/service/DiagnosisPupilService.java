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
public class DiagnosisPupilService {
    private final DiagnosisPupilRepository repository;
    private final DiagnosisPupilMapper mapper;
    private final DiagnosisRepository diagnosisRepository;
    private final PupilRepository pupilRepository;

    public List<DiagnosisPupilDto> findAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public DiagnosisPupilDto findById(String diagnosisCode, Integer personalFileNumber) {
        DiagnosisPupilId id = new DiagnosisPupilId(diagnosisCode, personalFileNumber);
        return repository.findById(id).map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("DiagnosisPupil not found"));
    }

    @Transactional
    public DiagnosisPupilDto create(DiagnosisPupilDto dto) {
        DiagnosisPupil entity = new DiagnosisPupil();
        DiagnosisPupilId id = new DiagnosisPupilId(dto.getDiagnosisCode(), dto.getPersonalFileNumber());
        entity.setId(id);
        entity.setDiagnosis(diagnosisRepository.getReferenceById(dto.getDiagnosisCode()));
        entity.setPupil(pupilRepository.getReferenceById(dto.getPersonalFileNumber()));
        return mapper.toDto(repository.save(entity));
    }

    @Transactional
    public void delete(String diagnosisCode, Integer personalFileNumber) {
        DiagnosisPupilId id = new DiagnosisPupilId(diagnosisCode, personalFileNumber);
        repository.deleteById(id);
    }
}