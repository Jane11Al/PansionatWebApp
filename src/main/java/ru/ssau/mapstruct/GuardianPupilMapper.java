package ru.ssau.mapstruct;

import ru.ssau.dto.GuardianPupilDto;
import ru.ssau.entity.GuardianPupil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GuardianPupilMapper {
    @Mapping(source = "guardian.fullName", target = "guardianFullName")
    @Mapping(source = "pupil.fullName", target = "pupilFullName")
    GuardianPupilDto toDto(GuardianPupil entity);

    @Mapping(target = "guardian", ignore = true)
    @Mapping(target = "pupil", ignore = true)
    GuardianPupil toEntity(GuardianPupilDto dto);
}