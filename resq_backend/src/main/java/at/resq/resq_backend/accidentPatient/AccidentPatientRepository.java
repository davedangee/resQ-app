package at.resq.resq_backend.accidentPatient;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 22.04.2026
 * Time: 14:30
 */

public interface AccidentPatientRepository extends JpaRepository<AccidentPatient, Long> {
    Optional<AccidentPatient> findByIncidenceReportId(Long incidenceReportId);
}
