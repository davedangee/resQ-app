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
        try {
            return ResponseEntity.ok(accidentPatientService.getAccidentPatientByReportId(reportId));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<AccidentPatient> createAccidentPatient(@PathVariable Long reportId, @RequestBody @Valid AccidentPatientRequestDtos.AccidentPatientRequestDto dto) {
        try {
            AccidentPatient created = accidentPatientService.createAccidentPatient(reportId, dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<AccidentPatient> updateAccidentPatient(@PathVariable Long reportId, @RequestBody @Valid AccidentPatientRequestDtos.AccidentPatientRequestDto dto) {
        try {
            AccidentPatient updated = accidentPatientService.updateAccidentPatient(reportId, dto);
            return ResponseEntity.ok(updated);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping
    public ResponseEntity<AccidentPatient> patch(
            @PathVariable Long reportId,
            @RequestBody JsonNode patchNode
    ) {
        try {
            return ResponseEntity.ok(accidentPatientService.patchAccidentPatient(reportId, patchNode));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAccidentPatient(@PathVariable Long reportId) {
        try {
            accidentPatientService.deleteAccidentPatientByReportId(reportId);
            return ResponseEntity.noContent().build(); // 204
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
