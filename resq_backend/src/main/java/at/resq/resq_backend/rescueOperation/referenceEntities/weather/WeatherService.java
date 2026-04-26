package at.resq.resq_backend.rescueOperation.referenceEntities.weather;


import at.resq.resq_backend.exceptionHandling.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 26.04.2026
 * Time: 17:28
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherService {
    private final WeatherRepository weatherRepository;

    public List<Weather> getAllWeather() {
        return weatherRepository.findAll();
    }

    public Weather getWeatherById(Long id) {
        return weatherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Weather not found with id " + id));
    }
}
