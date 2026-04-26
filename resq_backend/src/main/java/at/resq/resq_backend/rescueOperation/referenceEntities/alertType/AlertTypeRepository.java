package at.resq.resq_backend.rescueOperation.referenceEntities.alertType;


import at.resq.resq_backend.rescueOperation.referenceEntities.activity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 26.04.2026
 * Time: 16:40
 */

public interface AlertTypeRepository extends JpaRepository<AlertType, Long> {
    List<AlertType> findAllByUserDefinedFalse();
}
