package at.resq.resq_backend.rescueOperation.referenceEntities.snowComposition;


import lombok.Getter;
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
 * Time: 17:24
 */

@RestController
@RequestMapping("api/v1/snowComposition")
@RequiredArgsConstructor
@Slf4j
public class SnowCompositionController {
    private final SnowCompositionService snowCompositionService;

    @GetMapping("/all")
    public ResponseEntity<Iterable<SnowComposition>> getAllSnowCompositions() {
        return ResponseEntity.ok(snowCompositionService.getAllSnowComposition());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SnowComposition> getSnowCompositionById(@PathVariable Long id) {
        return ResponseEntity.ok(snowCompositionService.getSnowCompositionById(id));
    }
}
