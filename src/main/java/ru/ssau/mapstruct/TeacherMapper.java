package ru.ssau.mapstruct;

import org.mapstruct.Mapping;
import ru.ssau.dto.TeacherDto;
import ru.ssau.entity.Teacher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    TeacherDto toDto(Teacher entity);

    @Mapping(target = "educations", ignore = true)
    Teacher toEntity(TeacherDto dto);
}