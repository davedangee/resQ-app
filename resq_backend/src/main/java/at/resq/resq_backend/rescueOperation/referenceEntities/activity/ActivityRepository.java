package at.resq.resq_backend.rescueOperation.referenceEntities.activity;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 26.04.2026
 * Time: 16:40
 */

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findAllByUserDefinedFalse();
}
