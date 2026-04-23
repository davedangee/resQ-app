package at.resq.resq_backend.accidentPatient.injury.location;


import at.resq.resq_backend.accidentPatient.injury.type.InjuryType;
import at.resq.resq_backend.accidentPatient.vitalSign.type.VitalSignType;
import at.resq.resq_backend.accidentPatient.vitalSign.type.VitalSignTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 23.04.2026
 * Time: 13:32
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class InjuryLocationService {
    private final InjuryLocationRepository injuryLocationRepository;

    public List<InjuryLocation> getAllInjuryLocations() {
        return injuryLocationRepository.findAll();
    }

    public InjuryLocation getInjuryLocationById(Long id) {
        return injuryLocationRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("VitalSignType not found with id: " + id));
    }
}
