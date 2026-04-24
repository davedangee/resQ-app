package at.resq.resq_backend.accidentPatient.medication;

import at.resq.resq_backend.accidentPatient.AccidentPatient;
import at.resq.resq_backend.accidentPatient.medication.type.Medication;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 18.04.2026
 * Time: 18:01
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MedicationAdministration {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "medication_id", nullable = false)
    private Medication medication;

    @Column(nullable = false)
    private String dosage;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accident_patient_id", nullable = false)
    private AccidentPatient accidentPatient;
}
