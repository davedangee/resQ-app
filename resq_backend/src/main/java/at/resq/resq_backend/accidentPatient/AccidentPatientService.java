package at.resq.resq_backend.accidentPatient;


import at.resq.resq_backend.accidentPatient.dto.AccidentPatientDtos;
import at.resq.resq_backend.accidentPatient.dto.AccidentPatientMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 22.04.2026
 * Time: 14:30
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class AccidentPatientService {
    private final AccidentPatientRepository accidentPatientRepository;
    private final AccidentPatientMapper accidentPatientMapper;

    public List<AccidentPatient> getAllAccidentPatients() {
        return accidentPatientRepository.findAll();
    }

    public AccidentPatient getAccidentPatientById(Long id) {
        return accidentPatientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("AccidentPatient not found with id: " + id));
    }

    public AccidentPatient createAccidentPatient(AccidentPatientDtos.AccidentPatientRequestDto dto) {
        AccidentPatient patient = accidentPatientMapper.toEntity(dto);
        return accidentPatientRepository.save(patient);
    }

    public AccidentPatient updateAccidentPatient(Long id, AccidentPatientDtos.AccidentPatientRequestDto dto) {
        AccidentPatient patient = accidentPatientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("AccidentPatient not found with id: " + id));

        AccidentPatient updated = accidentPatientMapper.toEntity(dto);
        updated.setId(patient.getId());

        return accidentPatientRepository.save(updated);
    }

    public void deleteAccidentPatient(Long id) {
        if (!accidentPatientRepository.existsById(id)) {
            throw new NoSuchElementException("AccidentPatient not found with id: " + id);
        }
        accidentPatientRepository.deleteById(id);
    }
}