package at.resq.resq_backend.incidenceReport.handover;


import at.resq.resq_backend.accidentPatient.vitalSign.VitalSign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 25.04.2026
 * Time: 17:47
 */

public interface HandoverRepository extends JpaRepository<Handover,Long> {
    Optional<Handover> findByIncidenceReportId(Long reportId);
}
