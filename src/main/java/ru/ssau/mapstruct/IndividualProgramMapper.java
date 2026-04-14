package ru.ssau.mapstruct;

import ru.ssau.dto.IndividualProgramDto;
import ru.ssau.entity.IndividualProgram;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IndividualProgramMapper {
    @Mapping(source = "id.year", target = "year")
    @Mapping(source = "id.programCode", target = "programCode")
    @Mapping(source = "program.name", target = "programName")
    IndividualProgramDto toDto(IndividualProgram entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "program", ignore = true)
    @Mapping(target = "educations", ignore = true)
    @Mapping(target = "subjects", ignore = true)
    IndividualProgram toEntity(IndividualProgramDto dto);
}