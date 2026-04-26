package at.resq.resq_backend.rescueOperation.referenceEntities.slopeCondition;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 26.04.2026
 * Time: 17:19
 */

@RestController
@RequestMapping("api/v1/slopeCondition")
@RequiredArgsConstructor
@Slf4j
public class SlopeConditionController {
    private final SlopeConditionService slopeConditionService;

    @GetMapping("/all")
    public ResponseEntity<Iterable<SlopeCondition>> getAllSlopeConditions() {
        return ResponseEntity.ok(slopeConditionService.getAllSlopeConditions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SlopeCondition> getSlopeConditionById(@PathVariable Long id) {
        return ResponseEntity.ok(slopeConditionService.getSlopeConditionById(id));
    }
}
