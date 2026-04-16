package ru.ssau.mapper;

import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.ssau.dto.SubjectResultTypeDto;
import ru.ssau.entity.SubjectResultType;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PupilEducationMapper.class})
public interface SubjectResultTypeMapper {
    @Mapping(source = "education.id", target = "educationId")
    @Mapping(source = "education.code", target = "educationCode")
    SubjectResultTypeDto toDto(SubjectResultType entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "education", ignore = true)
    SubjectResultType toEntity(SubjectResultTypeDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "education", ignore = true)
    void updateEntity(SubjectResultTypeDto dto, @MappingTarget SubjectResultType entity);

    List<SubjectResultTypeDto> toDtoList(List<SubjectResultType> entities);
}
