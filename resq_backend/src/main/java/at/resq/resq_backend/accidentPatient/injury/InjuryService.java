package at.resq.resq_backend.accidentPatient.injury;


import at.resq.resq_backend.accidentPatient.AccidentPatient;
import at.resq.resq_backend.accidentPatient.AccidentPatientRepository;
import at.resq.resq_backend.accidentPatient.injury.dto.InjuryRequestDto;
import at.resq.resq_backend.accidentPatient.injury.location.InjuryLocation;
import at.resq.resq_backend.accidentPatient.injury.location.InjuryLocationRepository;
import at.resq.resq_backend.accidentPatient.injury.type.InjuryType;
import at.resq.resq_backend.accidentPatient.injury.type.InjuryTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 23.04.2026
 * Time: 12:53
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class InjuryService {
    private final InjuryRepository injuryRepository;
    private final InjuryTypeRepository injuryTypeRepository;
    private final InjuryLocationRepository injuryLocationRepository;
    private final AccidentPatientRepository accidentPatientRepository;

    public List<Injury> getAllByPatientId(Long patientId) {
        accidentPatientRepository.findById(patientId)
                .orElseThrow(() -> new NoSuchElementException("Patient not found with id: " + patientId));
        return injuryRepository.findAllByAccidentPatientId(patientId);
    }

    public Injury getByIdAndPatientId(Long id, Long patientId) {
        return injuryRepository.findByIdAndAccidentPatientId(id, patientId)
                .orElseThrow(() -> new NoSuchElementException("Injury not found with id: " + id));
    }

    public Injury create(Long patientId, InjuryRequestDto dto) {
        // validation
        if (dto.getInjuryTypeId() != null && dto.getCustomInjuryType() != null) {
            throw new IllegalArgumentException("Only one of injuryTypeId or customInjuryType can be provided");
        }
        if (dto.getInjuryTypeId() == null && dto.getCustomInjuryType() == null) {
            throw new IllegalArgumentException("Either injuryTypeId or customInjuryType must be provided");
        }

        AccidentPatient patient = accidentPatientRepository.findById(patientId)
                .orElseThrow(() -> new NoSuchElementException("AccidentPatient not found with id: " + patientId));

        InjuryLocation injuryLocation = injuryLocationRepository.findById(dto.getInjuryLocationId())
                .orElseThrow(() -> new NoSuchElementException("InjuryLocation not found with id: " + dto.getInjuryLocationId()));

        InjuryType injuryType;
        if (dto.getCustomInjuryType() != null) {
            Optional<InjuryType> injuryTypeInDb = injuryTypeRepository.findByName(dto.getCustomInjuryType());
            if (injuryTypeInDb.isPresent()) {
                injuryType = injuryTypeInDb.get();
            }
            else {
                injuryType = InjuryType.builder()
                        .name(dto.getCustomInjuryType())
                        .userDefined(true)
                        .build();
                injuryType = injuryTypeRepository.save(injuryType);
            }
        } else {
            injuryType = injuryTypeRepository.findById(dto.getInjuryTypeId())
                    .orElseThrow(() -> new NoSuchElementException("InjuryType not found with id: " + dto.getInjuryTypeId()));
        }

        try {
            Injury injury = Injury.builder()
                    .accidentPatient(patient)
                    .injuryLocation(injuryLocation)
                    .injuryType(injuryType)
                    .note(dto.getNote())
                    .build();
            return injuryRepository.save(injury);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("This injury type at this location already exists for this patient");
        }
    }

    public Injury update(Long id, Long patientId, InjuryRequestDto dto) {
        if (dto.getInjuryTypeId() != null && dto.getCustomInjuryType() != null) {
            throw new IllegalArgumentException("Only one of injuryTypeId or customInjuryType can be provided");
        }
        if (dto.getInjuryTypeId() == null && dto.getCustomInjuryType() == null) {
            throw new IllegalArgumentException("Either injuryTypeId or customInjuryType must be provided");
        }

        Injury existing = injuryRepository.findByIdAndAccidentPatientId(id, patientId)
                .orElseThrow(() -> new RuntimeException("Injury not found with id: " + id));

        InjuryLocation injuryLocation = injuryLocationRepository.findById(dto.getInjuryLocationId())
                .orElseThrow(() -> new RuntimeException("InjuryLocation not found with id: " + dto.getInjuryLocationId()));
        
        InjuryType injuryType;
        if (dto.getCustomInjuryType() != null) {
            Optional<InjuryType> injuryTypeInDb = injuryTypeRepository.findByName(dto.getCustomInjuryType());
            if (injuryTypeInDb.isPresent()) {
                injuryType = injuryTypeInDb.get();
            }
            else {
                injuryType = InjuryType.builder()
                        .name(dto.getCustomInjuryType())
                        .userDefined(true)
                        .build();
                injuryType = injuryTypeRepository.save(injuryType);
            }
        } else {
            injuryType = injuryTypeRepository.findById(dto.getInjuryTypeId())
                    .orElseThrow(() -> new RuntimeException("InjuryType not found with id: " + dto.getInjuryTypeId()));
        }

        try {
            existing.setInjuryLocation(injuryLocation);
            existing.setInjuryType(injuryType);
            existing.setNote(dto.getNote());
            return injuryRepository.save(existing);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("This injury type at this location already exists for this patient");
        }
    }

    public void delete(Long id, Long patientId) {
        Injury existing = injuryRepository.findByIdAndAccidentPatientId(id, patientId)
                .orElseThrow(() -> new NoSuchElementException("Injury not found with id: " + id));
        injuryRepository.delete(existing);
    }
}