package at.resq.resq_backend.rescueOperation.referenceEntities.season;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 26.04.2026
 * Time: 17:14
 */

@RestController
@RequestMapping("api/v1/season")
@RequiredArgsConstructor
@Slf4j
public class SeasonController {
    private final SeasonService seasonService;

    @GetMapping("/all")
    public ResponseEntity<Iterable<Season>> getAllSeasons() {
        return ResponseEntity.ok(seasonService.getAllSeasons());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Season> getSeasonById(@PathVariable Long id) {
        return ResponseEntity.ok(seasonService.getSeasonById(id));
    }
}
