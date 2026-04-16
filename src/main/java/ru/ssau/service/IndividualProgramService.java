package ru.ssau.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ssau.dto.IndividualProgramDto;
import ru.ssau.entity.IndividualProgram;
import ru.ssau.exception.EntityNotFoundException;
import ru.ssau.mapper.IndividualProgramMapper;
import ru.ssau.repository.IndividualProgramRepository;
import ru.ssau.repository.ProgramRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IndividualProgramService implements BaseService<IndividualProgramDto, Long> {

    private final IndividualProgramRepository repository;
    private final ProgramRepository programRepository;
    private final IndividualProgramMapper mapper;

    @Override
    public List<IndividualProgramDto> findAll() {
        return mapper.toDtoList(repository.findAll());
    }

    @Override
    public IndividualProgramDto findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Индивидуальная программа", id));
    }

    @Override
    @Transactional
    public IndividualProgramDto create(IndividualProgramDto dto) {
        IndividualProgram entity = mapper.toEntity(dto);
        if (dto.getProgramId() != null) {
            entity.setProgram(programRepository.getReferenceById(dto.getProgramId()));
        }
        return mapper.toDto(repository.save(entity));
    }

    @Override
    @Transactional
    public IndividualProgramDto update(Long id, IndividualProgramDto dto) {
        IndividualProgram existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Индивидуальная программа", id));
        mapper.updateEntity(dto, existing);
        if (dto.getProgramId() != null) {
            existing.setProgram(programRepository.getReferenceById(dto.getProgramId()));
        } else {
            existing.setProgram(null);
        }
        return mapper.toDto(repository.save(existing));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Индивидуальная программа", id);
        }
        repository.deleteById(id);
    }
}