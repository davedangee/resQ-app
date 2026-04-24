package at.resq.resq_backend.accidentPatient.vitalSign.type;


import at.resq.resq_backend.accidentPatient.vitalSign.VitalSign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 22.04.2026
 * Time: 13:52
 */

public interface VitalSignTypeRepository extends JpaRepository<VitalSignType, Long> {
}
