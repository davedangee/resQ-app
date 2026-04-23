package at.resq.resq_backend.accidentPatient.medication;


import at.resq.resq_backend.accidentPatient.AccidentPatient;
import at.resq.resq_backend.accidentPatient.AccidentPatientRepository;
import at.resq.resq_backend.accidentPatient.medication.dto.MedicationAdministrationRequestDto;
import at.resq.resq_backend.accidentPatient.medication.type.Medication;
import at.resq.resq_backend.accidentPatient.medication.type.MedicationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 23.04.2026
 * Time: 08:35
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class MedicationAdministrationService {
    private final MedicationAdministrationRepository medicationAdministrationRepository;
    private final AccidentPatientRepository accidentPatientRepository;
    private final MedicationRepository medicationRepository;

    public List<MedicationAdministration> getAllByPatientId(Long patientId) {
        accidentPatientRepository.findById(patientId).orElseThrow(() -> new NoSuchElementException("Patient not found with id: " + patientId));;
        return medicationAdministrationRepository.findAllByAccidentPatientId(patientId);
    }

    public MedicationAdministration getByIdAndPatientId(Long id, Long patientId) {
        return medicationAdministrationRepository.findByIdAndAccidentPatientId(id, patientId)
                .orElseThrow(() -> new NoSuchElementException("MedicationAdministration not found with id: " + id));
    }

    public MedicationAdministration create(Long patientId, Long medicationId, MedicationAdministrationRequestDto dto) {
        AccidentPatient patient = accidentPatientRepository.findById(patientId)
                .orElseThrow(() -> new NoSuchElementException("AccidentPatient not found with id: " + patientId));
        Medication medication = medicationRepository.findById(medicationId)
                .orElseThrow(() -> new NoSuchElementException("Medication not found with id: " + medicationId));

        MedicationAdministration administration = MedicationAdministration.builder()
                .accidentPatient(patient)
                .medication(medication)
                .dosage(dto.getDosage())
                .timestamp(dto.getTimestamp())
                .build();

        return medicationAdministrationRepository.save(administration);
    }

    public MedicationAdministration update(Long id, Long patientId, MedicationAdministrationRequestDto dto) {
        MedicationAdministration existing = medicationAdministrationRepository.findByIdAndAccidentPatientId(id, patientId)
                .orElseThrow(() -> new NoSuchElementException("MedicationAdministration not found with id: " + id));

        Medication medication = medicationRepository.findById(dto.getMedicationId())
                .orElseThrow(() -> new NoSuchElementException("Medication not found with id: " + dto.getMedicationId()));

        existing.setDosage(dto.getDosage());
        existing.setTimestamp(dto.getTimestamp());
        existing.setMedication(medication);

        return medicationAdministrationRepository.save(existing);
    }

    public void delete(Long id, Long patientId) {
        MedicationAdministration existing = medicationAdministrationRepository.findByIdAndAccidentPatientId(id, patientId)
                .orElseThrow(() -> new NoSuchElementException("MedicationAdministration not found with id: " + id));
        medicationAdministrationRepository.delete(existing);
    }
}
