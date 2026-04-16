package ru.ssau.mapper;

import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.ssau.dto.PersonalResultTypeDto;
import ru.ssau.entity.PersonalResultType;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PupilEducationMapper.class})
public interface PersonalResultTypeMapper {
    @Mapping(source = "education.id", target = "educationId")
    @Mapping(source = "education.code", target = "educationCode")
    PersonalResultTypeDto toDto(PersonalResultType entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "education", ignore = true)
    PersonalResultType toEntity(PersonalResultTypeDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "education", ignore = true)
    void updateEntity(PersonalResultTypeDto dto, @MappingTarget PersonalResultType entity);

    List<PersonalResultTypeDto> toDtoList(List<PersonalResultType> entities);
}
