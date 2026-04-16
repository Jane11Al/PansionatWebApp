package ru.ssau.mapper;

import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.ssau.dto.SubjectAreaDto;
import ru.ssau.entity.SubjectArea;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubjectAreaMapper {
    SubjectAreaDto toDto(SubjectArea entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "subjects", ignore = true)
    SubjectArea toEntity(SubjectAreaDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "subjects", ignore = true)
    void updateEntity(SubjectAreaDto dto, @MappingTarget SubjectArea entity);

    List<SubjectAreaDto> toDtoList(List<SubjectArea> entities);
}