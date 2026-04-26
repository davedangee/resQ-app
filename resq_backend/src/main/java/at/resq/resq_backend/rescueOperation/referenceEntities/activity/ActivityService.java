package at.resq.resq_backend.rescueOperation.referenceEntities.activity;

import at.resq.resq_backend.exceptionHandling.exceptions.ResourceNotFoundException;
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
public class ActivityService {
    private final ActivityRepository activityRepository;

    public List<Activity> getAllActivities() {
        return activityRepository.findAllByUserDefinedFalse();
    }

    public Activity getActivityById(Long id) {
        return activityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Activity not found with id: " + id));
    }
}
