package ru.ssau.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ssau.dto.GuardianPupilDto;
import ru.ssau.entity.GuardianPupil;
import ru.ssau.exception.EntityNotFoundException;
import ru.ssau.mapper.GuardianPupilMapper;
import ru.ssau.repository.GuardianPupilRepository;
import ru.ssau.repository.GuardianRepository;
import ru.ssau.repository.PupilRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GuardianPupilService implements BaseService<GuardianPupilDto, Long> {

    private final GuardianPupilRepository repository;
    private final GuardianRepository guardianRepository;
    private final PupilRepository pupilRepository;
    private final GuardianPupilMapper mapper;

    @Override
    public List<GuardianPupilDto> findAll() {
        return mapper.toDtoList(repository.findAll());
    }

    @Override
    public GuardianPupilDto findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Связь опекун-воспитанник", id));
    }

    @Override
    @Transactional
    public GuardianPupilDto create(GuardianPupilDto dto) {
        GuardianPupil entity = mapper.toEntity(dto);
        if (dto.getGuardianId() != null) {
            entity.setGuardian(guardianRepository.getReferenceById(dto.getGuardianId()));
        }
        if (dto.getPupilId() != null) {
            entity.setPupil(pupilRepository.getReferenceById(dto.getPupilId()));
        }
        return mapper.toDto(repository.save(entity));
    }

    @Override
    @Transactional
    public GuardianPupilDto update(Long id, GuardianPupilDto dto) {
        GuardianPupil existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Связь опекун-воспитанник", id));
        mapper.updateEntity(dto, existing);
        if (dto.getGuardianId() != null) {
            existing.setGuardian(guardianRepository.getReferenceById(dto.getGuardianId()));
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
            throw new EntityNotFoundException("Связь опекун-воспитанник", id);
        }
        repository.deleteById(id);
    }
}