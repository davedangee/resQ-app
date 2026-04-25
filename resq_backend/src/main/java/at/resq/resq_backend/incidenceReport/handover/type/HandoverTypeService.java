package at.resq.resq_backend.incidenceReport.handover.type;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 25.04.2026
 * Time: 17:26
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class HandoverTypeService {
    private final HandoverTypeRepository handoverTypeRepository;

    public List<HandoverType> getAllHandoverTypes() {
        return handoverTypeRepository.findAll();
    }

    public HandoverType getHandoverTypeById(Long id){
        return handoverTypeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("HandoverType not found with id: " + id));
    }
}
