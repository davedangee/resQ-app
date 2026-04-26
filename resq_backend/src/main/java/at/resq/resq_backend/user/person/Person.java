package at.resq.resq_backend.user.person;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 26.04.2026
 * Time: 01:37
 */

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String firstname;
    private String lastname;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "nationalityId", nullable = true)
    private Nationality nationality;
}