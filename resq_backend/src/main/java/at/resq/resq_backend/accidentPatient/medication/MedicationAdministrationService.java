package at.resq.resq_backend.accidentPatient.medication;


import at.resq.resq_backend.accidentPatient.AccidentPatient;
import at.resq.resq_backend.accidentPatient.AccidentPatientRepository;
import at.resq.resq_backend.accidentPatient.medication.dto.MedicationAdministrationRequestDto;
import at.resq.resq_backend.accidentPatient.medication.type.Medication;
import at.resq.resq_backend.accidentPatient.medication.type.MedicationRepository;
import at.resq.resq_backend.exceptionHandling.exceptions.ResourceNotFoundException;
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

    public List<MedicationAdministration> getAllByReportId(Long reportId) {
        AccidentPatient accidentPatient = accidentPatientRepository.findByIncidenceReportId(reportId)
                .orElseThrow(() -> new ResourceNotFoundException("AccidentPatient not found for incidenceReport id: " + reportId));

        return medicationAdministrationRepository.findAllByAccidentPatientId(accidentPatient.getId());
    }

    public MedicationAdministration getByIdAndReportId(Long id, Long reportId) {
        AccidentPatient accidentPatient = accidentPatientRepository.findByIncidenceReportId(reportId)
                .orElseThrow(() -> new ResourceNotFoundException("AccidentPatient not found for incidenceReport id: " + reportId));

        return medicationAdministrationRepository.findByIdAndAccidentPatientId(id, accidentPatient.getId())
                .orElseThrow(() -> new ResourceNotFoundException("MedicationAdministration not found with id: " + id));
    }

    public MedicationAdministration create(Long reportId, Long medicationId, MedicationAdministrationRequestDto dto) {
        AccidentPatient patient = accidentPatientRepository.findByIncidenceReportId(reportId)
                .orElseThrow(() -> new ResourceNotFoundException("AccidentPatient not found for incidenceReport id: " + reportId));
        Medication medication = medicationRepository.findById(medicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Medication not found with id: " + medicationId));

        MedicationAdministration administration = MedicationAdministration.builder()
                .accidentPatient(patient)
                .medication(medication)
                .dosage(dto.getDosage())
                .timestamp(dto.getTimestamp())
                .build();

        return medicationAdministrationRepository.save(administration);
    }

    public MedicationAdministration update(Long id, Long reportId, MedicationAdministrationRequestDto dto) {
        AccidentPatient accidentPatient = accidentPatientRepository.findByIncidenceReportId(reportId)
                .orElseThrow(() -> new ResourceNotFoundException("AccidentPatient not found for incidenceReport id: " + reportId));

        MedicationAdministration existing = medicationAdministrationRepository.findByIdAndAccidentPatientId(id, accidentPatient.getId())
                .orElseThrow(() -> new ResourceNotFoundException("MedicationAdministration not found with id: " + id));

        Medication medication = medicationRepository.findById(dto.getMedicationId())
                .orElseThrow(() -> new ResourceNotFoundException("Medication not found with id: " + dto.getMedicationId()));

        existing.setDosage(dto.getDosage());
        existing.setTimestamp(dto.getTimestamp());
        existing.setMedication(medication);

        return medicationAdministrationRepository.save(existing);
    }

    public void delete(Long id, Long reportId) {
        AccidentPatient accidentPatient = accidentPatientRepository.findByIncidenceReportId(reportId)
                .orElseThrow(() -> new ResourceNotFoundException("AccidentPatient not found for incidenceReport id: " + reportId));

        MedicationAdministration existing = medicationAdministrationRepository.findByIdAndAccidentPatientId(id, accidentPatient.getId())
                .orElseThrow(() -> new ResourceNotFoundException("MedicationAdministration not found with id: " + id));
        medicationAdministrationRepository.delete(existing);
    }
}