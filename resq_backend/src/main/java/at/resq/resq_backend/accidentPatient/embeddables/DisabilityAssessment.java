package at.resq.resq_backend.accidentPatient.embeddables;


import at.resq.resq_backend.accidentPatient.enums.LimbSensation;
import at.resq.resq_backend.accidentPatient.enums.PupilSize;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 22.04.2026
 * Time: 12:00
 */

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DisabilityAssessment {
    private Boolean headache;
    private Boolean seizure;
    private Boolean speakingDisorder;

    @Enumerated(EnumType.STRING)
    private LimbSensation limbSensation;

    private Boolean fastTestSuspicious;
    private Integer gcs;

    // Pupil assessment
    private Boolean isPupilLeftLightRigid;
    private Boolean isPupilRightLightRigid;

    @Enumerated(EnumType.STRING)
    private PupilSize pupilSizeLeft;

    @Enumerated(EnumType.STRING)
    private PupilSize pupilSizeRight;
}