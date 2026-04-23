package at.resq.resq_backend.accidentPatient.injury.type;


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
 * Time: 13:40
 */

@RestController
@RequestMapping("api/v1/injuryType")
@RequiredArgsConstructor
@Slf4j
public class InjuryTypeController {
    private final InjuryTypeService injuryTypeService;

    @GetMapping("/all")
    public ResponseEntity<Iterable<InjuryType>> getAllInjuryTypes() {
        try {
            return ResponseEntity.ok(injuryTypeService.getAllInjuryTypes());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<InjuryType> getInjuryTypeById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(injuryTypeService.getInjuryTypeById(id));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
