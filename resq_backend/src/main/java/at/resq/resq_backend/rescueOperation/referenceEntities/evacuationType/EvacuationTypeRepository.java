package at.resq.resq_backend.rescueOperation.referenceEntities.evacuationType;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 26.04.2026
 * Time: 16:50
 */

public interface EvacuationTypeRepository extends JpaRepository<EvacuationType, Long> {
    List<EvacuationType> findAllByUserDefinedFalse();
}
