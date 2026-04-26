package at.resq.resq_backend.rescueOperation.referenceEntities.slopeCondition;


import jakarta.persistence.*;
import lombok.*;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 26.04.2026
 * Time: 15:14
 */

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SlopeCondition {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true, nullable = false)
    private String label;
}
