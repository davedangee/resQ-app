package at.resq.resq_backend.accidentPatient.injury.type;


import jakarta.persistence.*;
import lombok.*;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 18.04.2026
 * Time: 17:18
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class InjuryType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean userDefined;
}
