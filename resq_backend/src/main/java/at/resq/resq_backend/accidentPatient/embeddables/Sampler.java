package at.resq.resq_backend.accidentPatient.embeddables;


import jakarta.persistence.Embeddable;
import lombok.*;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 22.04.2026
 * Time: 12:07
 */

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sampler {
    private String symptomsAndPain;
    private String allergies;
    private String patientMedication;
    private String patientHistory;
    private String lastMealAndDrink;
    private String incident;
    private String risks;
    private Boolean bloodThinnedPatient;
}
