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
public class SubjectResultService {
    private final SubjectResultRepository repository;
    private final SubjectResultMapper mapper;
    private final SubjectRepository subjectRepository;
    private final PupilEducationRepository educationRepository;

    public List<SubjectResultDto> findAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public SubjectResultDto findById(String subjectName, Integer subjectAreaCode, Integer educationCode) {
        SubjectResultId id = new SubjectResultId(subjectName, subjectAreaCode, educationCode);
        return repository.findById(id).map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("SubjectResult not found"));
    }

    @Transactional
    public SubjectResultDto create(SubjectResultDto dto) {
        SubjectResult entity = mapper.toEntity(dto);
        SubjectId subjectId = new SubjectId(dto.getSubjectName(), dto.getSubjectAreaCode());
        entity.setSubject(subjectRepository.getReferenceById(subjectId));
        entity.setEducation(educationRepository.getReferenceById(dto.getEducationCode()));
        return mapper.toDto(repository.save(entity));
    }

    @Transactional
    public SubjectResultDto update(String subjectName, Integer subjectAreaCode, Integer educationCode, SubjectResultDto dto) {
        SubjectResultId id = new SubjectResultId(subjectName, subjectAreaCode, educationCode);
        if (!repository.existsById(id)) {
            throw new RuntimeException("SubjectResult not found");
        }
        SubjectResult entity = mapper.toEntity(dto);
        entity.getId().setSubjectName(subjectName);
        entity.getId().setSubjectAreaCode(subjectAreaCode);
        entity.getId().setEducationCode(educationCode);
        SubjectId subjectId = new SubjectId(subjectName, subjectAreaCode);
        entity.setSubject(subjectRepository.getReferenceById(subjectId));
        entity.setEducation(educationRepository.getReferenceById(educationCode));
        return mapper.toDto(repository.save(entity));
    }

    @Transactional
    public void delete(String subjectName, Integer subjectAreaCode, Integer educationCode) {
        SubjectResultId id = new SubjectResultId(subjectName, subjectAreaCode, educationCode);
        repository.deleteById(id);
    }
}