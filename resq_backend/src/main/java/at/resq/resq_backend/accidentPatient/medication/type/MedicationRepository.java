package at.resq.resq_backend.accidentPatient.medication.type;


import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 22.04.2026
 * Time: 13:51
 */

public interface MedicationRepository extends JpaRepository<Medication, Integer> {
}
