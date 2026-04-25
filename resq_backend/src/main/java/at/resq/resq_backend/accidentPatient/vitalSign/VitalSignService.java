package at.resq.resq_backend.accidentPatient.vitalSign;


import at.resq.resq_backend.accidentPatient.AccidentPatient;
import at.resq.resq_backend.accidentPatient.AccidentPatientRepository;
import at.resq.resq_backend.accidentPatient.vitalSign.dto.VitalSignRequestDto;
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
 * Time: 10:14
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class VitalSignService {
    private final VitalSignRepository vitalSignRepository;
    private final AccidentPatientRepository accidentPatientRepository;
    private final VitalSignTypeRepository vitalSignTypeRepository;

    public List<VitalSign> getAllByReportId(Long reportId) {
        AccidentPatient accidentPatient = accidentPatientRepository.findByIncidenceReportId(reportId)
                .orElseThrow(() -> new NoSuchElementException("AccidentPatient not found for incidenceReport id: " + reportId));
        return vitalSignRepository.findAllByAccidentPatientId(accidentPatient.getId());
    }

    public VitalSign getByIdAndReportId(Long id, Long reportId) {
        AccidentPatient accidentPatient = accidentPatientRepository.findByIncidenceReportId(reportId)
                .orElseThrow(() -> new NoSuchElementException("AccidentPatient not found for incidenceReport id: " + reportId));

        return vitalSignRepository.findByIdAndAccidentPatientId(id, accidentPatient.getId())
                .orElseThrow(() -> new NoSuchElementException("VitalSign not found with id: " + id));
    }

    public VitalSign create(Long reportId, VitalSignRequestDto dto) {
        AccidentPatient accidentPatient = accidentPatientRepository.findByIncidenceReportId(reportId)
                .orElseThrow(() -> new NoSuchElementException("AccidentPatient not found for incidenceReport id: " + reportId));
        VitalSignType vitalSignType = vitalSignTypeRepository.findById(dto.getVitalSignTypeId())
                .orElseThrow(() -> new NoSuchElementException("VitalSignType not found with id: " + dto.getVitalSignTypeId()));

        VitalSign vitalSign = VitalSign.builder()
                .accidentPatient(accidentPatient)
                .vitalSignType(vitalSignType)
                .value(dto.getValue())
                .timestamp(dto.getTimestamp())
                .build();

        return vitalSignRepository.save(vitalSign);
    }

    public VitalSign update(Long id, Long reportId, VitalSignRequestDto dto) {
        AccidentPatient accidentPatient = accidentPatientRepository.findByIncidenceReportId(reportId)
                .orElseThrow(() -> new NoSuchElementException("AccidentPatient not found for incidenceReport id: " + reportId));

        VitalSign existing = vitalSignRepository.findByIdAndAccidentPatientId(id, accidentPatient.getId())
                .orElseThrow(() -> new NoSuchElementException("VitalSign not found with id: " + id));

        VitalSignType vitalSignType = vitalSignTypeRepository.findById(dto.getVitalSignTypeId())
                .orElseThrow(() -> new NoSuchElementException("VitalSignType not found with id: " + dto.getVitalSignTypeId()));

        existing.setVitalSignType(vitalSignType);
        existing.setValue(dto.getValue());
        existing.setTimestamp(dto.getTimestamp());

        return vitalSignRepository.save(existing);
    }

    public void delete(Long id, Long reportId) {
        AccidentPatient accidentPatient = accidentPatientRepository.findByIncidenceReportId(reportId)
                .orElseThrow(() -> new NoSuchElementException("AccidentPatient not found for incidenceReport id: " + reportId));

        VitalSign existing = vitalSignRepository.findByIdAndAccidentPatientId(id, accidentPatient.getId())
                .orElseThrow(() -> new NoSuchElementException("VitalSign not found with id: " + id));
        vitalSignRepository.delete(existing);
    }
}