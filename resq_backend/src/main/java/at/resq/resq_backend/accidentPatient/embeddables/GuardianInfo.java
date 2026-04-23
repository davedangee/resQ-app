package at.resq.resq_backend.accidentPatient.embeddables;


import jakarta.persistence.Embeddable;
import lombok.*;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 22.04.2026
 * Time: 11:30
 */

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuardianInfo {
    private String firstName;
    private String lastName;
    private String socialSecurityNumber;
    private String phoneNumber;
}