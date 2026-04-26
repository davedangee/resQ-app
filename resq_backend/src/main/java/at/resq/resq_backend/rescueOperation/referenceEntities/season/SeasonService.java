package at.resq.resq_backend.rescueOperation.referenceEntities.season;


import at.resq.resq_backend.exceptionHandling.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 26.04.2026
 * Time: 17:13
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class SeasonService {
    private final SeasonRepository seasonRepository;

    public List<Season> getAllSeasons() {
        return seasonRepository.findAll();
    }

    public Season getSeasonById(Long id) {
        return seasonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Season not found with id " + id));
    }
}
