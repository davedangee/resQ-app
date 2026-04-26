package at.resq.resq_backend.rescueOperation.referenceEntities.weather;


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
 * Time: 17:30
 */

@RestController
@RequestMapping("api/v1/weather")
@RequiredArgsConstructor
@Slf4j
public class WeatherController {
    private final WeatherService weatherService;

    @GetMapping("/all")
    public ResponseEntity<Iterable<Weather>> getAllWeather() {
        return ResponseEntity.ok(weatherService.getAllWeather());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Weather> getWeatherById(@PathVariable Long id) {
        return ResponseEntity.ok(weatherService.getWeatherById(id));
    }
}
