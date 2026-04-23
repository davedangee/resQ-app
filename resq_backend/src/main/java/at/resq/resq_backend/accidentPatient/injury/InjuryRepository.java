package at.resq.resq_backend.accidentPatient.injury;


import at.resq.resq_backend.accidentPatient.vitalSign.type.VitalSignType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 23.04.2026
 * Time: 12:52
 */

public interface InjuryRepository extends JpaRepository<Injury, Long> {
    List<Injury> findAllByAccidentPatientId(Long patientId);
    Optional<Injury> findByIdAndAccidentPatientId(Long id, Long patientId);
}
