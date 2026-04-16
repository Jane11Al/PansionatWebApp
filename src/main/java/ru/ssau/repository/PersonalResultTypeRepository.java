package ru.ssau.repository;

import ru.ssau.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonalResultTypeRepository extends JpaRepository<PersonalResultType, Long> {
    Optional<PersonalResultType> findByEducationId(Long educationId);
}
