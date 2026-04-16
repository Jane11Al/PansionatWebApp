package ru.ssau.repository;

import ru.ssau.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PupilRepository extends JpaRepository<Pupil, Long> {
    Optional<Pupil> findByPersonalFileNumber(Integer personalFileNumber);
}