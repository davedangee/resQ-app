package at.resq.resq_backend.rescueOperation;


import at.resq.resq_backend.incidenceReport.IncidenceReport;
import at.resq.resq_backend.rescueOperation.referenceEntities.activity.Activity;
import at.resq.resq_backend.rescueOperation.referenceEntities.alertType.AlertType;
import at.resq.resq_backend.rescueOperation.referenceEntities.evacuationType.EvacuationType;
import at.resq.resq_backend.rescueOperation.referenceEntities.policeRole.PoliceRole;
import at.resq.resq_backend.rescueOperation.referenceEntities.protectiveGear.ProtectiveGear;
import at.resq.resq_backend.rescueOperation.referenceEntities.season.Season;
import at.resq.resq_backend.rescueOperation.referenceEntities.slopeCondition.SlopeCondition;
import at.resq.resq_backend.rescueOperation.referenceEntities.snowComposition.SnowComposition;
import at.resq.resq_backend.rescueOperation.referenceEntities.weather.Weather;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 26.04.2026
 * Time: 14:39
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RescueOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Boolean pictureOfAccidentTaken;
    private Boolean pictureOfAccidentOnWall;

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "incidenceReportId", nullable = false)
    private IncidenceReport incidenceReport;

    @ManyToOne
    @JoinColumn(name = "activityId")
    private Activity activity;

    @ManyToOne
    @JoinColumn(name = "weatherId")
    private Weather weather;

    @ManyToOne
    @JoinColumn(name = "snowCompositionId")
    private SnowComposition snowComposition;

    @ManyToOne
    @JoinColumn(name = "slopeConditionId")
    private SlopeCondition slopeCondition;

    @ManyToOne
    @JoinColumn(name = "seasonId")
    private Season season;

    @ManyToMany
    @JoinTable(name = "rescue_operation_police_role",
            joinColumns = @JoinColumn(name = "rescue_operation_id"),
            inverseJoinColumns = @JoinColumn(name = "police_role_id"))
    private List<PoliceRole> policeRoles = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "rescue_operation_evacuation_type",
            joinColumns = @JoinColumn(name = "rescue_operation_id"),
            inverseJoinColumns = @JoinColumn(name = "evacuation_type_id"))
    private List<EvacuationType> evacuationTypes = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "rescue_operation_protective_gear",
            joinColumns = @JoinColumn(name = "rescue_operation_id"),
            inverseJoinColumns = @JoinColumn(name = "protective_gear_id"))
    private List<ProtectiveGear> protectiveGears = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "rescue_operation_alert_type",
            joinColumns = @JoinColumn(name = "rescue_operation_id"),
            inverseJoinColumns = @JoinColumn(name = "alert_type_id"))
    private List<AlertType> alertTypes = new ArrayList<>();
}
