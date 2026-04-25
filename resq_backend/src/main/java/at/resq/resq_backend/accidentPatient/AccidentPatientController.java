package at.resq.resq_backend.accidentPatient;

import at.resq.resq_backend.accidentPatient.dto.AccidentPatientRequestDtos;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.JsonNode;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 22.04.2026
 * Time: 14:29
 */

@RequestMapping("api/v1/incidenceReport/{reportId}/accidentPatient")
@RestController
@RequiredArgsConstructor
@Slf4j
public class AccidentPatientController {
    private final AccidentPatientService accidentPatientService;

//    @GetMapping("/all")
//    public ResponseEntity<Iterable<AccidentPatient>> getAllAccidentPatients() {
//        try {
//            List<AccidentPatient> accidentPatientList = accidentPatientService.getAllAccidentPatients();
//            return ResponseEntity.ok(accidentPatientList);
//        } catch (Exception e){
//            e.printStackTrace();
//            return ResponseEntity.badRequest().build();
//        }
//    }

    @GetMapping
    public ResponseEntity<AccidentPatient> getAccidentPatientById(@PathVariable Long reportId) {
        return ResponseEntity.ok(accidentPatientService.getAccidentPatientByReportId(reportId));
    }

    @PostMapping
    public ResponseEntity<AccidentPatient> createAccidentPatient(@PathVariable Long reportId, @RequestBody @Valid AccidentPatientRequestDtos.AccidentPatientRequestDto dto) {
        AccidentPatient created = accidentPatientService.createAccidentPatient(reportId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping
    public ResponseEntity<AccidentPatient> updateAccidentPatient(@PathVariable Long reportId, @RequestBody @Valid AccidentPatientRequestDtos.AccidentPatientRequestDto dto) {
        AccidentPatient updated = accidentPatientService.updateAccidentPatient(reportId, dto);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping
    public ResponseEntity<AccidentPatient> patch(@PathVariable Long reportId, @RequestBody JsonNode patchNode) {
        return ResponseEntity.ok(accidentPatientService.patchAccidentPatient(reportId, patchNode));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAccidentPatient(@PathVariable Long reportId) {
        accidentPatientService.deleteAccidentPatientByReportId(reportId);
        return ResponseEntity.noContent().build(); // 204
    }
}
