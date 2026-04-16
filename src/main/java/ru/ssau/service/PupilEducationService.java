package ru.ssau.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ssau.dto.PupilEducationDto;
import ru.ssau.entity.PupilEducation;
import ru.ssau.exception.EntityNotFoundException;
import ru.ssau.mapper.PupilEducationMapper;
import ru.ssau.repository.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PupilEducationService implements BaseService<PupilEducationDto, Long> {

    private final PupilEducationRepository repository;
    private final IndividualProgramRepository individualProgramRepository;
    private final TeacherRepository teacherRepository;
    private final PupilRepository pupilRepository;
    private final PupilEducationMapper mapper;

    @Override
    public List<PupilEducationDto> findAll() {
        return mapper.toDtoList(repository.findAll());
    }

    @Override
    public PupilEducationDto findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Обучение воспитанника", id));
    }

    @Override
    @Transactional
    public PupilEducationDto create(PupilEducationDto dto) {
        PupilEducation entity = mapper.toEntity(dto);
        if (dto.getIndividualProgramId() != null) {
            entity.setIndividualProgram(individualProgramRepository.getReferenceById(dto.getIndividualProgramId()));
        }
        if (dto.getTeacherId() != null) {
            entity.setTeacher(teacherRepository.getReferenceById(dto.getTeacherId()));
        }
        if (dto.getPupilId() != null) {
            entity.setPupil(pupilRepository.getReferenceById(dto.getPupilId()));
        }
        return mapper.toDto(repository.save(entity));
    }

    @Override
    @Transactional
    public PupilEducationDto update(Long id, PupilEducationDto dto) {
        PupilEducation existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Обучение воспитанника", id));
        mapper.updateEntity(dto, existing);
        if (dto.getIndividualProgramId() != null) {
            existing.setIndividualProgram(individualProgramRepository.getReferenceById(dto.getIndividualProgramId()));
        } else {
            existing.setIndividualProgram(null);
        }
        if (dto.getTeacherId() != null) {
            existing.setTeacher(teacherRepository.getReferenceById(dto.getTeacherId()));
        } else {
            existing.setTeacher(null);
        }
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
            throw new EntityNotFoundException("Обучение воспитанника", id);
        }
        repository.deleteById(id);
    }
}