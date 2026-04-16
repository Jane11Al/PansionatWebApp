package ru.ssau.repository;

import ru.ssau.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasicActionResultTypeRepository extends JpaRepository<BasicActionResultType, Long> {
    Optional<BasicActionResultType> findByEducationId(Long educationId);
}