package at.resq.resq_backend.rescueOperation.referenceEntities.alertType;

import at.resq.resq_backend.exceptionHandling.exceptions.ResourceNotFoundException;
import at.resq.resq_backend.rescueOperation.referenceEntities.activity.Activity;
import at.resq.resq_backend.rescueOperation.referenceEntities.activity.ActivityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 26.04.2026
 * Time: 16:40
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class AlertTypeService {
    private final AlertTypeRepository alertTypeRepository;

    public List<AlertType> getAllAlertTypes() {
        return alertTypeRepository.findAllByUserDefinedFalse();
    }

    public AlertType getAlertTypeById(Long id) {
        return alertTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AlertType not found with id: " + id));
    }
}
