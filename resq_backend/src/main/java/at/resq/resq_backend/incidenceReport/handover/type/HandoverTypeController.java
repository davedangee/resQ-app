package at.resq.resq_backend.incidenceReport.handover.type;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 25.04.2026
 * Time: 17:26
 */

@RestController
@RequestMapping("api/v1/handoverType")
@RequiredArgsConstructor
@Slf4j
public class HandoverTypeController {
    private final HandoverTypeService handoverTypeService;

    @GetMapping("/all")
    public ResponseEntity<Iterable<HandoverType>> getAllHandoverTypes() {
        return ResponseEntity.ok(handoverTypeService.getAllHandoverTypes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HandoverType> getHandoverTypeById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(handoverTypeService.getHandoverTypeById(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

}
