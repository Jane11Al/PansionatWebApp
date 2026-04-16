package ru.ssau.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ssau.dto.MedicalEquipmentDto;
import ru.ssau.entity.MedicalEquipment;
import ru.ssau.exception.EntityNotFoundException;
import ru.ssau.mapper.MedicalEquipmentMapper;
import ru.ssau.repository.MedicalEquipmentRepository;
import ru.ssau.repository.PupilRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicalEquipmentService implements BaseService<MedicalEquipmentDto, Long> {

    private final MedicalEquipmentRepository repository;
    private final PupilRepository pupilRepository;
    private final MedicalEquipmentMapper mapper;

    @Override
    public List<MedicalEquipmentDto> findAll() {
        return mapper.toDtoList(repository.findAll());
    }

    @Override
    public MedicalEquipmentDto findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Медоборудование", id));
    }

    @Override
    @Transactional
    public MedicalEquipmentDto create(MedicalEquipmentDto dto) {
        MedicalEquipment entity = mapper.toEntity(dto);
        if (dto.getPupilId() != null) {
            entity.setPupil(pupilRepository.getReferenceById(dto.getPupilId()));
        }
        return mapper.toDto(repository.save(entity));
    }

    @Override
    @Transactional
    public MedicalEquipmentDto update(Long id, MedicalEquipmentDto dto) {
        MedicalEquipment existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Медоборудование", id));
        mapper.updateEntity(dto, existing);
        if (dto.getPupilId() != null) {
            existing.setPupil(pupilRepository.getReferenceById(dto.getPupilId()));
        } else {
            existing.setPupil(null);
        }
        return mapper.toDto(repository.save(existing));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Медоборудование", id);
        }
        repository.deleteById(id);
    }
}