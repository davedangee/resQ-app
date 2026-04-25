package at.resq.resq_backend.accidentPatient;


import at.resq.resq_backend.accidentPatient.dto.AccidentPatientRequestDtos;
import at.resq.resq_backend.accidentPatient.dto.AccidentPatientMapper;
import at.resq.resq_backend.accidentPatient.embeddables.*;
import at.resq.resq_backend.accidentPatient.enums.*;
import at.resq.resq_backend.exceptionHandling.exceptions.InvalidRequestException;
import at.resq.resq_backend.exceptionHandling.exceptions.ResourceAlreadyExistsException;
import at.resq.resq_backend.exceptionHandling.exceptions.ResourceNotFoundException;
import at.resq.resq_backend.incidenceReport.IncidenceReport;
import at.resq.resq_backend.incidenceReport.IncidenceReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.function.Consumer;

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
    private final IncidenceReportRepository incidenceReportRepository;
    private static final Set<String> FORBIDDEN_PATCH_FIELDS = Set.of("id", "incidenceReport", "injuryList", "vitalSignList", "medicationAdministrationList");

    public List<AccidentPatient> getAllAccidentPatients() {
        return accidentPatientRepository.findAll();
    }

    public AccidentPatient getAccidentPatientByReportId(Long reportId) {
        return accidentPatientRepository.findByIncidenceReportId(reportId)
                .orElseThrow(() -> new ResourceNotFoundException("AccidentPatient not found for incidenceReport id: " + reportId));
    }

    public AccidentPatient createAccidentPatient(Long reportId, AccidentPatientRequestDtos.AccidentPatientRequestDto dto) {
        IncidenceReport incidenceReport = incidenceReportRepository.findById(reportId)
                .orElseThrow(() -> new ResourceNotFoundException("Incidence Report not found for reportId: " + reportId));

        if(accidentPatientRepository.findByIncidenceReportId(incidenceReport.getId()).isPresent()) {
            throw new ResourceAlreadyExistsException("AccidentPatient already exists for reportId: " + incidenceReport.getId());
        }

        AccidentPatient accidentPatient = accidentPatientMapper.toEntity(dto);
        accidentPatient.setIncidenceReport(incidenceReport);
        incidenceReport.setAccidentPatient(accidentPatient);
        return accidentPatientRepository.save(accidentPatient);
    }

    public AccidentPatient updateAccidentPatient(Long reportId, AccidentPatientRequestDtos.AccidentPatientRequestDto dto) {
        AccidentPatient existing = accidentPatientRepository.findByIncidenceReportId(reportId)
                .orElseThrow(() -> new ResourceNotFoundException("AccidentPatient not found for incidenceReport id: " + reportId));

        existing.setMountainRescueInsurance(dto.getMountainRescueInsurance());
        existing.setGuardian(accidentPatientMapper.toEntity(dto.getGuardian()));
        existing.setGuestType(dto.getGuestType());
        existing.setHolidayAddress(dto.getHolidayAddress());
        existing.setDayOfHoliday(dto.getDayOfHoliday());
        existing.setPatientPosition(dto.getPatientPosition());
        existing.setPatientPositionSpecification(dto.getPatientPositionSpecification());
        existing.setConsciousnessAssessment(accidentPatientMapper.toEntity(dto.getConsciousnessAssessment()));
        existing.setAirwayAssessment(accidentPatientMapper.toEntity(dto.getAirwayAssessment()));
        existing.setBreathingAssessment(accidentPatientMapper.toEntity(dto.getBreathingAssessment()));
        existing.setCirculationAssessment(accidentPatientMapper.toEntity(dto.getCirculationAssessment()));
        existing.setDisabilityAssessment(accidentPatientMapper.toEntity(dto.getDisabilityAssessment()));
        existing.setExposureAssessment(accidentPatientMapper.toEntity(dto.getExposureAssessment()));
        existing.setSampler(accidentPatientMapper.toEntity(dto.getSampler()));
        existing.setCprInfo(accidentPatientMapper.toEntity(dto.getCprInfo()));

        return accidentPatientRepository.save(existing);
    }

    public AccidentPatient patchAccidentPatient(Long reportId, JsonNode patchNode) {
        AccidentPatient existing = accidentPatientRepository.findByIncidenceReportId(reportId)
                .orElseThrow(() -> new ResourceNotFoundException("AccidentPatient not found for incidenceReport id: " + reportId));

        validatePatchPayload(patchNode);

        applyText(patchNode, "mountainRescueInsurance", existing::setMountainRescueInsurance);
        applyEnum(patchNode, "guestType", GuestType.class, existing::setGuestType);
        applyText(patchNode, "holidayAddress", existing::setHolidayAddress);
        applyInteger(patchNode, "dayOfHoliday", existing::setDayOfHoliday);
        applyEnum(patchNode, "patientPosition", PatientPosition.class, existing::setPatientPosition);
        applyText(patchNode, "patientPositionSpecification", existing::setPatientPositionSpecification);

        patchGuardian(existing, patchNode.get("guardian"));
        patchConsciousness(existing, patchNode.get("consciousnessAssessment"));
        patchAirway(existing, patchNode.get("airwayAssessment"));
        patchBreathing(existing, patchNode.get("breathingAssessment"));
        patchCirculation(existing, patchNode.get("circulationAssessment"));
        patchDisability(existing, patchNode.get("disabilityAssessment"));
        patchExposure(existing, patchNode.get("exposureAssessment"));
        patchSampler(existing, patchNode.get("sampler"));
        patchCpr(existing, patchNode.get("cprInfo"));

        return accidentPatientRepository.save(existing);
    }

    public void deleteAccidentPatientByReportId(Long reportId) {
        AccidentPatient existing = accidentPatientRepository.findByIncidenceReportId(reportId)
                .orElseThrow(() -> new ResourceNotFoundException("AccidentPatient not found for incidenceReport id: " + reportId));

        IncidenceReport incidenceReport = existing.getIncidenceReport();
        if (incidenceReport != null) {
            incidenceReport.setAccidentPatient(null);
        }

        accidentPatientRepository.delete(existing);
    }

    // --------------------------------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------------------------------


    void validatePatchPayload(JsonNode patchNode) {
        if (patchNode == null || !patchNode.isObject()) {
            throw new InvalidRequestException("Patch body must be a JSON object");
        }

        FORBIDDEN_PATCH_FIELDS.forEach(field -> {
            if (patchNode.has(field)) {
                throw new InvalidRequestException("Field '" + field + "' cannot be patched here");
            }
        });
    }

    private void patchGuardian(AccidentPatient existing, JsonNode node) {
        if (node == null) {
            return;
        }
        if (node.isNull()) {
            existing.setGuardian(null);
            return;
        }
        if (!node.isObject()) {
            throw new InvalidRequestException("Field 'guardian' must be a JSON object");
        }

        GuardianInfo guardian = existing.getGuardian() != null ? existing.getGuardian() : new GuardianInfo();
        applyText(node, "firstName", guardian::setFirstName);
        applyText(node, "lastName", guardian::setLastName);
        applyText(node, "socialSecurityNumber", guardian::setSocialSecurityNumber);
        applyText(node, "phoneNumber", guardian::setPhoneNumber);
        existing.setGuardian(guardian);
    }

    private void patchConsciousness(AccidentPatient existing, JsonNode node) {
        if (node == null) {
            return;
        }
        if (node.isNull()) {
            existing.setConsciousnessAssessment(null);
            return;
        }
        if (!node.isObject()) {
            throw new InvalidRequestException("Field 'consciousnessAssessment' must be a JSON object");
        }

        ConsciousnessAssessment value = existing.getConsciousnessAssessment() != null
                ? existing.getConsciousnessAssessment()
                : new ConsciousnessAssessment();

        applyEnum(node, "state", ConsciousnessState.class, value::setState);
        applyBoolean(node, "orientedInTime", value::setOrientedInTime);
        applyBoolean(node, "orientedInPlace", value::setOrientedInPlace);
        applyBoolean(node, "orientedToPerson", value::setOrientedToPerson);
        applyBoolean(node, "orientedToSituation", value::setOrientedToSituation);

        existing.setConsciousnessAssessment(value);
    }

    private void patchAirway(AccidentPatient existing, JsonNode node) {
        if (node == null) {
            return;
        }
        if (node.isNull()) {
            existing.setAirwayAssessment(null);
            return;
        }
        if (!node.isObject()) {
            throw new InvalidRequestException("Field 'airwayAssessment' must be a JSON object");
        }

        AirwayAssessment value = existing.getAirwayAssessment() != null
                ? existing.getAirwayAssessment()
                : new AirwayAssessment();

        applyBoolean(node, "isAirwayClear", value::setIsAirwayClear);
        applyEnum(node, "airwayClearanceMethod", AirwayClearanceMethod.class, value::setAirwayClearanceMethod);
        applyText(node, "specificAirwayMeasure", value::setSpecificAirwayMeasure);

        existing.setAirwayAssessment(value);
    }

    private void patchBreathing(AccidentPatient existing, JsonNode node) {
        if (node == null) {
            return;
        }
        if (node.isNull()) {
            existing.setBreathingAssessment(null);
            return;
        }
        if (!node.isObject()) {
            throw new InvalidRequestException("Field 'breathingAssessment' must be a JSON object");
        }

        BreathingAssessment value = existing.getBreathingAssessment() != null
                ? existing.getBreathingAssessment()
                : new BreathingAssessment();

        applyBoolean(node, "isRespirationNormal", value::setIsRespirationNormal);
        applyBoolean(node, "isBreathShortened", value::setIsBreathShortened);
        applyBoolean(node, "manualVentilation", value::setManualVentilation);
        applyEnum(node, "intubationMethod", IntubationMethod.class, value::setIntubationMethod);
        applyInteger(node, "oxygenAdministration", value::setOxygenAdministration);
        applyBoolean(node, "pleuralPuncture", value::setPleuralPuncture);

        existing.setBreathingAssessment(value);
    }

    private void patchCirculation(AccidentPatient existing, JsonNode node) {
        if (node == null) {
            return;
        }
        if (node.isNull()) {
            existing.setCirculationAssessment(null);
            return;
        }
        if (!node.isObject()) {
            throw new InvalidRequestException("Field 'circulationAssessment' must be a JSON object");
        }

        CirculationAssessment value = existing.getCirculationAssessment() != null
                ? existing.getCirculationAssessment()
                : new CirculationAssessment();

        applyBoolean(node, "isSkinCold", value::setIsSkinCold);
        applyBoolean(node, "isSkinSweaty", value::setIsSkinSweaty);
        applyBoolean(node, "isSkinPale", value::setIsSkinPale);
        applyBoolean(node, "isSkinCyanotic", value::setIsSkinCyanotic);
        applyBoolean(node, "strongBleeding", value::setStrongBleeding);
        applyBoolean(node, "exhaustion", value::setExhaustion);
        applyBoolean(node, "shockPosition", value::setShockPosition);
        applyBoolean(node, "pressureBandage", value::setPressureBandage);
        applyDateTime(node, "tourniquetSince", value::setTourniquetSince);
        applyBoolean(node, "pelvicBinder", value::setPelvicBinder);
        applyText(node, "specificCirculationMeasure", value::setSpecificCirculationMeasure);

        existing.setCirculationAssessment(value);
    }

    private void patchDisability(AccidentPatient existing, JsonNode node) {
        if (node == null) {
            return;
        }
        if (node.isNull()) {
            existing.setDisabilityAssessment(null);
            return;
        }
        if (!node.isObject()) {
            throw new InvalidRequestException("Field 'disabilityAssessment' must be a JSON object");
        }

        DisabilityAssessment value = existing.getDisabilityAssessment() != null
                ? existing.getDisabilityAssessment()
                : new DisabilityAssessment();

        applyBoolean(node, "headache", value::setHeadache);
        applyBoolean(node, "seizure", value::setSeizure);
        applyBoolean(node, "speakingDisorder", value::setSpeakingDisorder);
        applyEnum(node, "limbSensation", LimbSensation.class, value::setLimbSensation);
        applyBoolean(node, "fastTestSuspicious", value::setFastTestSuspicious);
        applyInteger(node, "gcs", value::setGcs);
        applyBoolean(node, "isPupilLeftLightRigid", value::setIsPupilLeftLightRigid);
        applyBoolean(node, "isPupilRightLightRigid", value::setIsPupilRightLightRigid);
        applyEnum(node, "pupilSizeLeft", PupilSize.class, value::setPupilSizeLeft);
        applyEnum(node, "pupilSizeRight", PupilSize.class, value::setPupilSizeRight);

        existing.setDisabilityAssessment(value);
    }

    private void patchExposure(AccidentPatient existing, JsonNode node) {
        if (node == null) {
            return;
        }
        if (node.isNull()) {
            existing.setExposureAssessment(null);
            return;
        }
        if (!node.isObject()) {
            throw new InvalidRequestException("Field 'exposureAssessment' must be a JSON object");
        }

        ExposureAssessment value = existing.getExposureAssessment() != null
                ? existing.getExposureAssessment()
                : new ExposureAssessment();

        applyEnum(node, "hypothermiaState", HypothermiaState.class, value::setHypothermiaState);
        applyInteger(node, "painScaleValue", value::setPainScaleValue);
        applyInteger(node, "nacaStatus", value::setNacaStatus);
        applyEnum(node, "patientPositioning", PatientPositioning.class, value::setPatientPositioning);

        existing.setExposureAssessment(value);
    }

    private void patchSampler(AccidentPatient existing, JsonNode node) {
        if (node == null) {
            return;
        }
        if (node.isNull()) {
            existing.setSampler(null);
            return;
        }
        if (!node.isObject()) {
            throw new InvalidRequestException("Field 'sampler' must be a JSON object");
        }

        Sampler value = existing.getSampler() != null
                ? existing.getSampler()
                : new Sampler();

        applyText(node, "symptomsAndPain", value::setSymptomsAndPain);
        applyText(node, "allergies", value::setAllergies);
        applyText(node, "patientMedication", value::setPatientMedication);
        applyText(node, "patientHistory", value::setPatientHistory);
        applyText(node, "lastMealAndDrink", value::setLastMealAndDrink);
        applyText(node, "incident", value::setIncident);
        applyText(node, "risks", value::setRisks);
        applyBoolean(node, "bloodThinnedPatient", value::setBloodThinnedPatient);

        existing.setSampler(value);
    }

    private void patchCpr(AccidentPatient existing, JsonNode node) {
        if (node == null) {
            return;
        }
        if (node.isNull()) {
            existing.setCprInfo(null);
            return;
        }
        if (!node.isObject()) {
            throw new InvalidRequestException("Field 'cprInfo' must be a JSON object");
        }

        CprInfo value = existing.getCprInfo() != null
                ? existing.getCprInfo()
                : new CprInfo();

        applyBoolean(node, "active", value::setActive);
        applyDateTime(node, "startTime", value::setStartTime);
        applyDateTime(node, "stopTime", value::setStopTime);
        applyInteger(node, "amountOfShocks", value::setAmountOfShocks);
        applyText(node, "abortDoctorsName", value::setAbortDoctorsName);
        applyBoolean(node, "ekgActive", value::setEkgActive);

        existing.setCprInfo(value);
    }

    private void applyText(JsonNode patchNode, String fieldName, Consumer<String> setter) {
        if (!patchNode.has(fieldName)) {
            return;
        }

        JsonNode valueNode = patchNode.get(fieldName);
        setter.accept(valueNode.isNull() ? null : valueNode.asText());
    }

    private void applyInteger(JsonNode patchNode, String fieldName, Consumer<Integer> setter) {
        if (!patchNode.has(fieldName)) {
            return;
        }

        JsonNode valueNode = patchNode.get(fieldName);
        if (valueNode.isNull()) {
            setter.accept(null);
            return;
        }

        if (!valueNode.isNumber()) {
            throw new InvalidRequestException("Field '" + fieldName + "' must be a number");
        }

        setter.accept(valueNode.intValue());
    }

    private void applyBoolean(JsonNode patchNode, String fieldName, Consumer<Boolean> setter) {
        if (!patchNode.has(fieldName)) {
            return;
        }

        JsonNode valueNode = patchNode.get(fieldName);
        if (valueNode.isNull()) {
            setter.accept(null);
            return;
        }

        if (!valueNode.isBoolean()) {
            throw new InvalidRequestException("Field '" + fieldName + "' must be a boolean");
        }

        setter.accept(valueNode.booleanValue());
    }

    private void applyLong(JsonNode patchNode, String fieldName, Consumer<Long> setter) {
        if (!patchNode.has(fieldName)) {
            return;
        }

        JsonNode valueNode = patchNode.get(fieldName);
        if (valueNode.isNull()) {
            setter.accept(null);
            return;
        }

        if (!valueNode.isNumber()) {
            throw new InvalidRequestException("Field '" + fieldName + "' must be a number");
        }

        setter.accept(valueNode.longValue());
    }

    private void applyDateTime(JsonNode patchNode, String fieldName, Consumer<LocalDateTime> setter) {
        if (!patchNode.has(fieldName)) {
            return;
        }

        JsonNode valueNode = patchNode.get(fieldName);
        if (valueNode.isNull()) {
            setter.accept(null);
            return;
        }

        if (!valueNode.isString()) {
            throw new InvalidRequestException("Field '" + fieldName + "' must be a string with format yyyy-MM-dd'T'HH:mm[:ss]");
        }

        setter.accept(LocalDateTime.parse(valueNode.asText()));
    }

    private <E extends Enum<E>> void applyEnum(
            JsonNode patchNode,
            String fieldName,
            Class<E> enumClass,
            Consumer<E> setter
    ) {
        if (!patchNode.has(fieldName)) {
            return;
        }

        JsonNode valueNode = patchNode.get(fieldName);
        if (valueNode.isNull()) {
            setter.accept(null);
            return;
        }

        if (!valueNode.isString()) {
            throw new InvalidRequestException("Field '" + fieldName + "' must be a string");
        }

        try {
            setter.accept(Enum.valueOf(enumClass, valueNode.asText()));
        } catch (IllegalArgumentException e) {
            throw new InvalidRequestException("Field '" + fieldName + "' has invalid value '" + valueNode.asText() + "'");
        }
    }
}