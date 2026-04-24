package at.resq.resq_backend.accidentPatient.medication;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 23.04.2026
 * Time: 08:35
 */

@Repository
public interface MedicationAdministrationRepository extends JpaRepository<MedicationAdministration, Long> {
    List<MedicationAdministration> findAllByAccidentPatientId(Long patientId);
    Optional<MedicationAdministration> findByIdAndAccidentPatientId(Long id, Long patientId);
}
