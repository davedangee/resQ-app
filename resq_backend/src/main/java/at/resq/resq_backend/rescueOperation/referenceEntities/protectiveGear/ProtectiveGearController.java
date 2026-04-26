package at.resq.resq_backend.rescueOperation.referenceEntities.protectiveGear;

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
 * Time: 17:09
 */

@RestController
@RequestMapping("api/v1/protectiveGear")
@RequiredArgsConstructor
@Slf4j
public class ProtectiveGearController {
    private final ProtectiveGearService protectiveGearService;

    @GetMapping("/all")
    public ResponseEntity<Iterable<ProtectiveGear>> getAllProtectiveGear() {
        return ResponseEntity.ok(protectiveGearService.getAllProtectiveGear());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProtectiveGear> getProtectiveGearById(@PathVariable Long id) {
        return ResponseEntity.ok(protectiveGearService.getProtectiveGearById(id));
    }

}
