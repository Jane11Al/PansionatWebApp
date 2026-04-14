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
public class PupilEducationService {
    private final PupilEducationRepository repository;
    private final PupilEducationMapper mapper;
    private final IndividualProgramRepository individualProgramRepository;
    private final TeacherRepository teacherRepository;
    private final PupilRepository pupilRepository;

    public List<PupilEducationDto> findAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public PupilEducationDto findById(Integer code) {
        return repository.findById(code).map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("PupilEducation not found: " + code));
    }

    @Transactional
    public PupilEducationDto create(PupilEducationDto dto) {
        PupilEducation entity = mapper.toEntity(dto);
        IndividualProgramId ipId = new IndividualProgramId(dto.getYear(), dto.getProgramCode());
        entity.setIndividualProgram(individualProgramRepository.getReferenceById(ipId));
        entity.setTeacher(teacherRepository.getReferenceById(dto.getTeacherFullName()));
        entity.setPupil(pupilRepository.getReferenceById(dto.getPersonalFileNumber()));
        return mapper.toDto(repository.save(entity));
    }

    @Transactional
    public PupilEducationDto update(Integer code, PupilEducationDto dto) {
        if (!repository.existsById(code)) {
            throw new RuntimeException("PupilEducation not found: " + code);
        }
        PupilEducation entity = mapper.toEntity(dto);
        entity.setCode(code);
        IndividualProgramId ipId = new IndividualProgramId(dto.getYear(), dto.getProgramCode());
        entity.setIndividualProgram(individualProgramRepository.getReferenceById(ipId));
        entity.setTeacher(teacherRepository.getReferenceById(dto.getTeacherFullName()));
        entity.setPupil(pupilRepository.getReferenceById(dto.getPersonalFileNumber()));
        return mapper.toDto(repository.save(entity));
    }

    @Transactional
    public void delete(Integer code) {
        repository.deleteById(code);
    }
}