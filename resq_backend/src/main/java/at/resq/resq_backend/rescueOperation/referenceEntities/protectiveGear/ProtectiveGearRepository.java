package at.resq.resq_backend.rescueOperation.referenceEntities.protectiveGear;


import at.resq.resq_backend.rescueOperation.referenceEntities.policeRole.PoliceRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 26.04.2026
 * Time: 17:06
 */

public interface ProtectiveGearRepository extends JpaRepository<ProtectiveGear, Long> {
    List<ProtectiveGear> findAllByUserDefinedFalse();
}
