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
public class IndividualProgramService {
    private final IndividualProgramRepository repository;
    private final IndividualProgramMapper mapper;
    private final ProgramRepository programRepository;

    public List<IndividualProgramDto> findAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public IndividualProgramDto findById(Short year, Integer programCode) {
        IndividualProgramId id = new IndividualProgramId(year, programCode);
        return repository.findById(id).map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("IndividualProgram not found"));
    }

    @Transactional
    public IndividualProgramDto create(IndividualProgramDto dto) {
        IndividualProgram entity = new IndividualProgram();
        IndividualProgramId id = new IndividualProgramId(dto.getYear(), dto.getProgramCode());
        entity.setId(id);
        entity.setProgram(programRepository.getReferenceById(dto.getProgramCode()));
        return mapper.toDto(repository.save(entity));
    }

    @Transactional
    public void delete(Short year, Integer programCode) {
        IndividualProgramId id = new IndividualProgramId(year, programCode);
        repository.deleteById(id);
    }
}