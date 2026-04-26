package at.resq.resq_backend.user.person;

import jakarta.persistence.*;
import lombok.*;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 26.04.2026
 * Time: 01:37
 */

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Nationality {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true, nullable = false)
    private String countryName;

    @Column(unique = true, nullable = false)
    private String countryCode;

    private String flagUrl;
}