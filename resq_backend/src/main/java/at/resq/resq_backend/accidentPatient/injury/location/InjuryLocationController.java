package at.resq.resq_backend.accidentPatient.injury.location;


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
 * Date: 23.04.2026
 * Time: 13:32
 */

@RestController
@RequestMapping("api/v1/injuryLocation")
@RequiredArgsConstructor
@Slf4j
public class InjuryLocationController {
    private final InjuryLocationService injuryLocationService;

    @GetMapping("/all")
    public ResponseEntity<Iterable<InjuryLocation>> getAllInjuryLocations() {
        try {
            return ResponseEntity.ok(injuryLocationService.getAllInjuryLocations());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<InjuryLocation> getInjuryLocationById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(injuryLocationService.getInjuryLocationById(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
