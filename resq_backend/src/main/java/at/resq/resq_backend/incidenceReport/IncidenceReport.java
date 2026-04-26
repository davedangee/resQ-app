package at.resq.resq_backend.incidenceReport;


import at.resq.resq_backend.accidentPatient.AccidentPatient;
import at.resq.resq_backend.incidenceReport.handover.Handover;
import at.resq.resq_backend.rescueOperation.RescueOperation;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 24.04.2026
 * Time: 15:58
 */

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@Builder
public class IncidenceReport {
    @Id
    @ToString.Include
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Long incidenceReportNumber;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime accidentTime;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime accidentReportTime;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime arrivalTime;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime emtRequest;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime endTime;

    private String location;

    private Long seaLevel;

    private String provisionalDiagnosis;

    @JsonManagedReference
    @OneToOne(mappedBy = "incidenceReport", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private Handover handover;

    @JsonManagedReference
    @ToString.Include
    @OneToOne(mappedBy = "incidenceReport", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private AccidentPatient accidentPatient;

    @JsonManagedReference
    @OneToOne(mappedBy = "incidenceReport", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private RescueOperation rescueOperation;

}
