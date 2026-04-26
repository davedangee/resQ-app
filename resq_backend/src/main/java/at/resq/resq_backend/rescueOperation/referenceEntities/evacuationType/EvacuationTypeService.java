package at.resq.resq_backend.rescueOperation.referenceEntities.evacuationType;


import at.resq.resq_backend.exceptionHandling.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 26.04.2026
 * Time: 16:51
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class EvacuationTypeService {
    private final EvacuationTypeRepository evacuationTypeRepository;

    public List<EvacuationType> findAllByUserDefinedFalse() {
        return evacuationTypeRepository.findAllByUserDefinedFalse();
    }

    public EvacuationType getEvacuationTypeById(Long id) {
        return evacuationTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EvacuationType not found with id " + id));
    }
}
