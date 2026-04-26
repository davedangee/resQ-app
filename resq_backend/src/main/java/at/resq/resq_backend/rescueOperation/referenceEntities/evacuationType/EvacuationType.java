package at.resq.resq_backend.rescueOperation.referenceEntities.evacuationType;


import jakarta.persistence.*;
import lombok.*;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 26.04.2026
 * Time: 15:32
 */

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvacuationType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, unique = true)
    private String label;

    private Boolean userDefined;
}