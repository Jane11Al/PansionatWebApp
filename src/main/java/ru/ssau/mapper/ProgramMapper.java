package ru.ssau.mapper;

import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.ssau.dto.ProgramDto;
import ru.ssau.entity.Program;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProgramMapper {
    ProgramDto toDto(Program entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "individualPrograms", ignore = true)
    Program toEntity(ProgramDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "individualPrograms", ignore = true)
    void updateEntity(ProgramDto dto, @MappingTarget Program entity);

    List<ProgramDto> toDtoList(List<Program> entities);
}