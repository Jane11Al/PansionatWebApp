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
public class GuardianPupilService {
    private final GuardianPupilRepository repository;
    private final GuardianPupilMapper mapper;
    private final GuardianRepository guardianRepository;
    private final PupilRepository pupilRepository;

    public List<GuardianPupilDto> findAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    // Поскольку PK только personalFileNumber, используем его
    public GuardianPupilDto findById(Integer personalFileNumber) {
        return repository.findById(personalFileNumber).map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("GuardianPupil not found for pupil: " + personalFileNumber));
    }

    @Transactional
    public GuardianPupilDto create(GuardianPupilDto dto) {
        GuardianPupil entity = new GuardianPupil();
        entity.setPersonalFileNumber(dto.getPersonalFileNumber());
        entity.setGuardian(guardianRepository.getReferenceById(dto.getGuardianFullName()));
        entity.setPupil(pupilRepository.getReferenceById(dto.getPersonalFileNumber()));
        return mapper.toDto(repository.save(entity));
    }

    @Transactional
    public GuardianPupilDto update(Integer personalFileNumber, GuardianPupilDto dto) {
        if (!repository.existsById(personalFileNumber)) {
            throw new RuntimeException("GuardianPupil not found for pupil: " + personalFileNumber);
        }
        GuardianPupil entity = new GuardianPupil();
        entity.setPersonalFileNumber(personalFileNumber);
        entity.setGuardian(guardianRepository.getReferenceById(dto.getGuardianFullName()));
        entity.setPupil(pupilRepository.getReferenceById(personalFileNumber));
        return mapper.toDto(repository.save(entity));
    }

    @Transactional
    public void delete(Integer personalFileNumber) {
        repository.deleteById(personalFileNumber);
    }
}