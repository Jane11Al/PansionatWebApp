package ru.ssau.repository;

import ru.ssau.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PupilEducationRepository extends JpaRepository<PupilEducation, Long> {
    Optional<PupilEducation> findByCode(Integer code);
}