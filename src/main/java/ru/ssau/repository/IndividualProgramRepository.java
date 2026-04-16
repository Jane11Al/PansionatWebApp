package ru.ssau.repository;

import ru.ssau.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IndividualProgramRepository extends JpaRepository<IndividualProgram, Long> {
    boolean existsByYearAndProgramId(Short year, Long programId);
    Optional<IndividualProgram> findByYearAndProgramId(Short year, Long programId);
}