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
public class MedicalEquipmentService {
    private final MedicalEquipmentRepository repository;
    private final MedicalEquipmentMapper mapper;
    private final PupilRepository pupilRepository;

    public List<MedicalEquipmentDto> findAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public MedicalEquipmentDto findById(Integer inventoryNumber) {
        return repository.findById(inventoryNumber).map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("MedicalEquipment not found: " + inventoryNumber));
    }

    @Transactional
    public MedicalEquipmentDto create(MedicalEquipmentDto dto) {
        MedicalEquipment entity = mapper.toEntity(dto);
        if (dto.getPersonalFileNumber() != null) {
            entity.setPupil(pupilRepository.getReferenceById(dto.getPersonalFileNumber()));
        }
        return mapper.toDto(repository.save(entity));
    }

    @Transactional
    public MedicalEquipmentDto update(Integer inventoryNumber, MedicalEquipmentDto dto) {
        if (!repository.existsById(inventoryNumber)) {
            throw new RuntimeException("MedicalEquipment not found: " + inventoryNumber);
        }
        MedicalEquipment entity = mapper.toEntity(dto);
        entity.setInventoryNumber(inventoryNumber);
        if (dto.getPersonalFileNumber() != null) {
            entity.setPupil(pupilRepository.getReferenceById(dto.getPersonalFileNumber()));
        }
        return mapper.toDto(repository.save(entity));
    }

    @Transactional
    public void delete(Integer inventoryNumber) {
        repository.deleteById(inventoryNumber);
    }
}
