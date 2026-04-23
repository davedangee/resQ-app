package at.resq.resq_backend.accidentPatient.embeddables;


import at.resq.resq_backend.accidentPatient.enums.HypothermiaState;
import at.resq.resq_backend.accidentPatient.enums.PatientPositioning;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 22.04.2026
 * Time: 12:04
 */

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExposureAssessment {
    @Enumerated(EnumType.STRING)
    private HypothermiaState hypothermiaState;

    @Min(0) @Max(10)
    private Integer painScaleValue;

    @Min(1) @Max(7)
    private Integer nacaStatus;

    @Enumerated(EnumType.STRING)
    private PatientPositioning patientPositioning;
}