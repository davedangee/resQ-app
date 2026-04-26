package at.resq.resq_backend.rescueOperation.referenceEntities.policeRole;


import at.resq.resq_backend.rescueOperation.referenceEntities.evacuationType.EvacuationType;
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
 * Time: 17:02
 */

@RestController
@RequestMapping("api/v1/policeRole")
@RequiredArgsConstructor
@Slf4j
public class PoliceRoleController {
    private final PoliceRoleService policeRoleService;

    @GetMapping("/all")
    public ResponseEntity<Iterable<PoliceRole>> findAll() {
        return ResponseEntity.ok(policeRoleService.getAllPoliceRoles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PoliceRole> findById(@PathVariable Long id) {
        return ResponseEntity.ok(policeRoleService.getPoliceRoleById(id));
    }
}
