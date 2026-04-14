package ru.ssau.mapstruct;

import org.mapstruct.Mapping;
import ru.ssau.dto.ProgramDto;
import ru.ssau.entity.Program;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProgramMapper {
    ProgramDto toDto(Program entity);

    @Mapping(target = "individualPrograms", ignore = true)
    Program toEntity(ProgramDto dto);
}