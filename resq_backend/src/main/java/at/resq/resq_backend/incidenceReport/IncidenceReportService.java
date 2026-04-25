package at.resq.resq_backend.incidenceReport;

import at.resq.resq_backend.exceptionHandling.exceptions.InvalidRequestException;
import at.resq.resq_backend.exceptionHandling.exceptions.ResourceNotFoundException;
import at.resq.resq_backend.incidenceReport.dto.IncidenceReportRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class IncidenceReportService {

    private static final Set<String> FORBIDDEN_PATCH_FIELDS =
            Set.of("accidentPatient", "handover", "skiPatrol", "skiPatrolMembership");

    private final IncidenceReportRepository incidenceReportRepository;

    public List<IncidenceReport> getAllIncidenceReports() {
        return incidenceReportRepository.findAll();
    }

    public IncidenceReport getIncidenceReportById(Long id) {
        return incidenceReportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Incidence Report Not Found with id: " + id));
    }

    public IncidenceReport create(IncidenceReportRequestDto dto) {
        IncidenceReport incidenceReport = IncidenceReport.builder()
                .incidenceReportNumber(dto.getIncidenceReportNumber())
                .accidentTime(dto.getAccidentTime())
                .accidentReportTime(dto.getAccidentReportTime())
                .arrivalTime(dto.getArrivalTime())
                .emtRequest(dto.getEmtRequest())
                .endTime(dto.getEndTime())
                .location(dto.getLocation())
                .seaLevel(dto.getSeaLevel())
                .provisionalDiagnosis(dto.getProvisionalDiagnosis())
                .build();

        return incidenceReportRepository.save(incidenceReport);
    }

    public IncidenceReport patch(Long id, JsonNode patchNode) {
        IncidenceReport existing = incidenceReportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("IncidenceReport not found with id: " + id));

        validatePatchPayload(patchNode);

        applyLong(patchNode, "incidenceReportNumber", existing::setIncidenceReportNumber);
        applyDateTime(patchNode, "accidentTime", existing::setAccidentTime);
        applyDateTime(patchNode, "accidentReportTime", existing::setAccidentReportTime);
        applyDateTime(patchNode, "arrivalTime", existing::setArrivalTime);
        applyDateTime(patchNode, "emtRequest", existing::setEmtRequest);
        applyDateTime(patchNode, "endTime", existing::setEndTime);
        applyText(patchNode, "location", existing::setLocation);
        applyLong(patchNode, "seaLevel", existing::setSeaLevel);
        applyText(patchNode, "provisionalDiagnosis", existing::setProvisionalDiagnosis);

        return incidenceReportRepository.save(existing);
    }

    public void deleteIncidenceReport(Long id) {
        incidenceReportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Incidence Report Not Found with id: " + id));

        incidenceReportRepository.deleteById(id);
    }

    private void validatePatchPayload(JsonNode patchNode) {
        if (patchNode == null || !patchNode.isObject()) {
            throw new InvalidRequestException("Patch body must be a JSON object");
        }

        FORBIDDEN_PATCH_FIELDS.forEach(field -> {
            if (patchNode.has(field)) {
                throw new InvalidRequestException("Field '" + field + "' cannot be patched here");
            }
        });
    }

    private void applyText(JsonNode patchNode, String fieldName, java.util.function.Consumer<String> setter) {
        if (!patchNode.has(fieldName)) {
            return;
        }

        JsonNode valueNode = patchNode.get(fieldName);
        setter.accept(valueNode.isNull() ? null : valueNode.asText());
    }

    private void applyLong(JsonNode patchNode, String fieldName, java.util.function.Consumer<Long> setter) {
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

    private void applyDateTime(JsonNode patchNode, String fieldName, java.util.function.Consumer<LocalDateTime> setter) {
        if (!patchNode.has(fieldName)) {
            return;
        }

        JsonNode valueNode = patchNode.get(fieldName);
        if (valueNode.isNull()) {
            setter.accept(null);
            return;
        }

        if (!valueNode.isString()) {
            throw new InvalidRequestException("Field '" + fieldName + "' must be a string with format yyyy-MM-dd'T'HH:mm");
        }

        setter.accept(LocalDateTime.parse(valueNode.asText()));
    }
}