package at.resq.resq_backend.accidentPatient.injury;


import at.resq.resq_backend.accidentPatient.AccidentPatient;
import at.resq.resq_backend.accidentPatient.injury.location.InjuryLocation;
import at.resq.resq_backend.accidentPatient.injury.type.InjuryType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 18.04.2026
 * Time: 17:08
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "injury", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"injury_type_id", "injury_location_id", "accident_patient_id"})
})
public class Injury {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "injury_type_id", nullable = false)
    private InjuryType injuryType;

    @ManyToOne
    @JoinColumn(name = "injury_location_id", nullable = false)
    private InjuryLocation injuryLocation;

    @JsonBackReference
    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "accident_patient_id", nullable = false)
    private AccidentPatient accidentPatient;

    @Column(nullable = true, length = 255)
    private String note;
}
