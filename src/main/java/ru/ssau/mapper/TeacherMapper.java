package ru.ssau.mapper;

import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.ssau.dto.TeacherDto;
import ru.ssau.entity.Teacher;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    TeacherDto toDto(Teacher entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)   // пароль не маппим через DTO
    @Mapping(target = "educations", ignore = true)
    Teacher toEntity(TeacherDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "educations", ignore = true)
    void updateEntity(TeacherDto dto, @MappingTarget Teacher entity);

    List<TeacherDto> toDtoList(List<Teacher> entities);
}