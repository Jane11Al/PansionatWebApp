package ru.ssau.mapper;

import org.mapstruct.MappingTarget;
import ru.ssau.dto.GuardianPupilDto;
import ru.ssau.entity.GuardianPupil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {GuardianMapper.class, PupilMapper.class})
public interface GuardianPupilMapper {
    @Mapping(source = "guardian.id", target = "guardianId")
    @Mapping(source = "guardian.fullName", target = "guardianFullName")
    @Mapping(source = "pupil.id", target = "pupilId")
    @Mapping(source = "pupil.personalFileNumber", target = "pupilPersonalFileNumber")
    @Mapping(source = "pupil.fullName", target = "pupilFullName")
    GuardianPupilDto toDto(GuardianPupil entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "guardian", ignore = true)
    @Mapping(target = "pupil", ignore = true)
    GuardianPupil toEntity(GuardianPupilDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "guardian", ignore = true)
    @Mapping(target = "pupil", ignore = true)
    void updateEntity(GuardianPupilDto dto, @MappingTarget GuardianPupil entity);

    List<GuardianPupilDto> toDtoList(List<GuardianPupil> entities);
}