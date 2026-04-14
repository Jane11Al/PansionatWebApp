package ru.ssau.repository;

import ru.ssau.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PupilRepository extends JpaRepository<Pupil, Integer> {
}