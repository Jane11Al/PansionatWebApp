package ru.ssau.repository;

import ru.ssau.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuardianPupilRepository extends JpaRepository<GuardianPupil, Long> {
    boolean existsByGuardianIdAndPupilId(Long guardianId, Long pupilId);
}