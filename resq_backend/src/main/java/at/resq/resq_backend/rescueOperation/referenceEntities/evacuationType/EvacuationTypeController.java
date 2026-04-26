package at.resq.resq_backend.rescueOperation.referenceEntities.evacuationType;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 26.04.2026
 * Time: 16:54
 */

@RestController
@RequestMapping("api/v1/evacuationType")
@RequiredArgsConstructor
@Slf4j
public class EvacuationTypeController {
    private final EvacuationTypeService evacuationTypeService;

    @GetMapping("/all")
    public ResponseEntity<Iterable<EvacuationType>> findAll() {
        return ResponseEntity.ok(evacuationTypeService.findAllByUserDefinedFalse());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvacuationType> findById(@PathVariable Long id) {
        return ResponseEntity.ok(evacuationTypeService.getEvacuationTypeById(id));
    }
}
