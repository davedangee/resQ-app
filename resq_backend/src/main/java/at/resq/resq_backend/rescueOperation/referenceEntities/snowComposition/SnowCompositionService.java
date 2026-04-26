package at.resq.resq_backend.rescueOperation.referenceEntities.snowComposition;


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
 * Time: 17:23
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class SnowCompositionService {
    private final SnowCompositionRepository snowCompositionRepository;

    public List<SnowComposition> getAllSnowComposition() {
        return snowCompositionRepository.findAll();
    }

    public SnowComposition getSnowCompositionById(@PathVariable Long id) {
        return snowCompositionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SnowComposition not found with id: " + id));
    }
}
