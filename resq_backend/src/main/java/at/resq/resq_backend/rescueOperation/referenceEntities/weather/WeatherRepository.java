package at.resq.resq_backend.rescueOperation.referenceEntities.weather;


import at.resq.resq_backend.rescueOperation.referenceEntities.snowComposition.SnowComposition;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 26.04.2026
 * Time: 17:28
 */

public interface WeatherRepository extends JpaRepository<Weather, Long> {
}
