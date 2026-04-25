package at.resq.resq_backend.accidentPatient;

import at.resq.resq_backend.accidentPatient.embeddables.*;
import at.resq.resq_backend.accidentPatient.enums.*;
import at.resq.resq_backend.accidentPatient.injury.Injury;
import at.resq.resq_backend.accidentPatient.medication.MedicationAdministration;
import at.resq.resq_backend.accidentPatient.vitalSign.VitalSign;
import at.resq.resq_backend.incidenceReport.IncidenceReport;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Project: ${PROJECT_NAME}
 * Created by: Leitner David
 * Date: ${DATE}
 * Time: ${TIME}
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
@Setter
public class AccidentPatient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // AccidentPatient owns
    @JsonBackReference
    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "incidenceReportId", unique = true)
    private IncidenceReport incidenceReport;

    private String mountainRescueInsurance;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "firstName", column = @Column(name = "guardian_first_name")),
            @AttributeOverride(name = "lastName", column = @Column(name = "guardian_last_name")),
            @AttributeOverride(name = "socialSecurityNumber", column = @Column(name = "guardian_social_security_number")),
            @AttributeOverride(name = "phoneNumber", column = @Column(name = "guardian_phone_number"))
    })
    private GuardianInfo guardian;

    @Enumerated(EnumType.STRING) // day_guest, vacation_guest, unknown
    private GuestType guestType;
    private String holidayAddress;
    private Integer dayOfHoliday;

    // Patient found + Consciousness
    @Enumerated(EnumType.STRING)
    private PatientPosition patientPosition; // walking_standing, seating, laying, hanging, pronePosition, supinePosition, lateralPosition
    private String patientPositionSpecification; // Sonstiges: _______________

    @Embedded
    private ConsciousnessAssessment consciousnessAssessment;

    // ABCDE - Schema
    @Embedded private AirwayAssessment airwayAssessment;
    @Embedded private BreathingAssessment breathingAssessment;
    @Embedded private CirculationAssessment circulationAssessment;
    @Embedded private DisabilityAssessment disabilityAssessment;
    @Embedded private ExposureAssessment exposureAssessment;
    @Embedded private Sampler sampler;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "active", column = @Column(name = "cpr_active")),
            @AttributeOverride(name = "startTime", column = @Column(name = "cpr_start_time")),
            @AttributeOverride(name = "stopTime", column = @Column(name = "cpr_stop_time")),
            @AttributeOverride(name = "amountOfShocks", column = @Column(name = "cpr_amount_of_shocks")),
            @AttributeOverride(name = "abortDoctorsName", column = @Column(name = "cpr_abort_doctors_name"))
    })
    private CprInfo cprInfo;

    @JsonManagedReference
    @ToString.Exclude
    @OneToMany(mappedBy = "accidentPatient", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Injury> injuryList;

    @JsonManagedReference
    @ToString.Exclude
    @OneToMany(mappedBy = "accidentPatient", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private List<VitalSign> vitalSignList;

    @JsonManagedReference
    @ToString.Exclude
    @OneToMany(mappedBy = "accidentPatient", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private List<MedicationAdministration> medicationAdministrationList;

}
