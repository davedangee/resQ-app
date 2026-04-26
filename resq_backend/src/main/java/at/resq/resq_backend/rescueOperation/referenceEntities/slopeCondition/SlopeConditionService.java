package at.resq.resq_backend.rescueOperation.referenceEntities.slopeCondition;


import at.resq.resq_backend.exceptionHandling.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 26.04.2026
 * Time: 17:18
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class SlopeConditionService {
    private final SlopeConditionRepository slopeConditionRepository;

    public List<SlopeCondition> getAllSlopeConditions() {
        return slopeConditionRepository.findAll();
    }

    public SlopeCondition getSlopeConditionById(Long id) {
        return slopeConditionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SlopeCondition not found with id: " + id));
    }
}
