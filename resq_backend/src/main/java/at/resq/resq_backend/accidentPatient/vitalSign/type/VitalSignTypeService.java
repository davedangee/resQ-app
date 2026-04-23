package at.resq.resq_backend.accidentPatient.vitalSign.type;


import at.resq.resq_backend.accidentPatient.vitalSign.VitalSign;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 23.04.2026
 * Time: 10:40
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class VitalSignTypeService {
    private final VitalSignTypeRepository vitalSignTypeRepository;

    public List<VitalSignType> getAllVitalSignTypes() {
        return vitalSignTypeRepository.findAll();
    }

    public VitalSignType getVitalSignById(Long id) {
        return vitalSignTypeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("VitalSignType not found with id: " + id));
    }
}
