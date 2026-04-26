package at.resq.resq_backend.rescueOperation.referenceEntities.protectiveGear;


import at.resq.resq_backend.exceptionHandling.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 26.04.2026
 * Time: 17:07
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class ProtectiveGearService {
    private final ProtectiveGearRepository protectiveGearRepository;

    public List<ProtectiveGear> getAllProtectiveGear() {
        return protectiveGearRepository.findAllByUserDefinedFalse();
    }

    public ProtectiveGear getProtectiveGearById(Long id) {
        return protectiveGearRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ProtectiveGear not found with id " + id));
    }
}
