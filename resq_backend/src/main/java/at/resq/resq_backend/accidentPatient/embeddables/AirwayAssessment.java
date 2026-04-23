package at.resq.resq_backend.accidentPatient.embeddables;


import at.resq.resq_backend.accidentPatient.enums.AirwayClearanceMethod;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 22.04.2026
 * Time: 11:48
 */

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AirwayAssessment {
    private Boolean isAirwayClear;

    @Enumerated(EnumType.STRING)
    private AirwayClearanceMethod airwayClearanceMethod;

    private String specificAirwayMeasure;
}