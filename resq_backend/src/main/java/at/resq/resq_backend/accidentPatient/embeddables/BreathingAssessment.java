package at.resq.resq_backend.accidentPatient.embeddables;


import at.resq.resq_backend.accidentPatient.enums.IntubationMethod;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 22.04.2026
 * Time: 11:51
 */

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BreathingAssessment {
    private Boolean isRespirationNormal;
    private Boolean isBreathShortened;
    private Boolean manualVentilation;

    @Enumerated(EnumType.STRING)
    private IntubationMethod intubationMethod;

    private Integer oxygenAdministration;
    private Boolean pleuralPuncture;
}