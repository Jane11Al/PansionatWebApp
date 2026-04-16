package ru.ssau.repository;

import ru.ssau.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjectResultTypeRepository extends JpaRepository<SubjectResultType, Long> {
    Optional<SubjectResultType> findByEducationId(Long educationId);
}