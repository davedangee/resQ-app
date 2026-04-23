package at.resq.resq_backend.accidentPatient.vitalSign;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 23.04.2026
 * Time: 10:13
 */

@Repository
public interface VitalSignRepository extends JpaRepository<VitalSign, Long> {
    List<VitalSign> findAllByAccidentPatientId(Long patientId);
    Optional<VitalSign> findByIdAndAccidentPatientId(Long id, Long patientId);
}
