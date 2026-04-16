package ru.ssau.mapper;

import org.mapstruct.MappingTarget;
import ru.ssau.dto.IndividualProgramDto;
import ru.ssau.entity.IndividualProgram;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProgramMapper.class})
public interface IndividualProgramMapper {
    @Mapping(source = "program.id", target = "programId")
    @Mapping(source = "program.code", target = "programCode")
    @Mapping(source = "program.name", target = "programName")
    IndividualProgramDto toDto(IndividualProgram entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "program", ignore = true)
    IndividualProgram toEntity(IndividualProgramDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "program", ignore = true)
    void updateEntity(IndividualProgramDto dto, @MappingTarget IndividualProgram entity);

    List<IndividualProgramDto> toDtoList(List<IndividualProgram> entities);
}