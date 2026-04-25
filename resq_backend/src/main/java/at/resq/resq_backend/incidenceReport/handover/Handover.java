package at.resq.resq_backend.incidenceReport.handover;


import at.resq.resq_backend.accidentPatient.AccidentPatient;
import at.resq.resq_backend.incidenceReport.IncidenceReport;
import at.resq.resq_backend.incidenceReport.handover.type.HandoverType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 24.04.2026
 * Time: 16:06
 */

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Handover {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String handoverUnit;

    private String targetHospital;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime handoverTime;

    @ManyToOne
    @JoinColumn(nullable = false, name = "handoverTypeId")
    private HandoverType handoverType;

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "incidenceReportId")
    private IncidenceReport incidenceReport;
}
