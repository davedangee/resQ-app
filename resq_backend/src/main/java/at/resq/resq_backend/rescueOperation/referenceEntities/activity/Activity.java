package at.resq.resq_backend.rescueOperation.referenceEntities.activity;


import jakarta.persistence.*;
import lombok.*;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 26.04.2026
 * Time: 15:09
 */

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, unique = true)
    private String activityName;

    @Column(nullable = false)
    private Boolean userDefined;
}
