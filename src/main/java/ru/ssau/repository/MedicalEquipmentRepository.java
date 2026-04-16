package ru.ssau.repository;

import ru.ssau.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicalEquipmentRepository extends JpaRepository<MedicalEquipment, Long> {
    Optional<MedicalEquipment> findByInventoryNumber(Integer inventoryNumber);
}