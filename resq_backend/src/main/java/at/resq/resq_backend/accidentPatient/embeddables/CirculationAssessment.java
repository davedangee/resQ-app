package at.resq.resq_backend.accidentPatient.embeddables;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 22.04.2026
 * Time: 11:52
 */

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CirculationAssessment {
    private Boolean isSkinCold;
    private Boolean isSkinSweaty;
    private Boolean isSkinPale;
    private Boolean isSkinCyanotic;

    private Boolean strongBleeding;
    private Boolean exhaustion;

    private Boolean shockPosition;
    private Boolean pressureBandage;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime tourniquetSince;
    private Boolean pelvicBinder;
    private String specificCirculationMeasure;
}