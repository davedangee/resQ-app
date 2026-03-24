package at.resq.resq_backend.accidentPatient;

import at.resq.resq_backend.accidentPatient.enums.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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
@Setter
public class AccidentPatient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mountainRescueInsurance;

    private String guardianFirstName;
    private String guardianLastName;
    private String guardianSocialSecurityNumber;
    private String guardianPhoneNumber;

    @Enumerated(EnumType.STRING) // day_guest, vacation_guest, unknown
    private GuestType guestType;
    private String holidayAddress;
    private Integer dayOfHoliday;

    // ********** ABCDE - Schema **********
    // Patient found + Consciousness
    @Enumerated(EnumType.STRING)
    private PatientPosition patientPosition; // walking_standing, seating, laying, hanging, pronePosition, supinePosition, lateralPosition
    private String patientPositionSpecification; // Sonstiges: _______________

    @Enumerated(EnumType.STRING)
    private ConsciousnessState consciousnessState; // AWAKE, RESPONDS_TO_VOICE, RESPONDS_TO_PAIN, UNRESPONSIVE

    private Boolean orientedInTime;        // zeitlich orientiert
    private Boolean orientedInPlace;       // örtlich orientiert
    private Boolean orientedToPerson;      // persönlich orientiert
    private Boolean orientedToSituation;   // situativ orientiert

    // *** A - B ***
    private Boolean isAirwayClear;
    private Boolean isRespirationNormal;
    private Boolean isBreathShortened;

    @Enumerated(EnumType.STRING)
    private AirwayClearanceMethod airwayClearanceMethod; // MANUALLY, SUCTION, MAGILL_FORCEPS, ABDOMINAL_THRUST

    // Measures
    private Boolean manualVentilation;
    private IntubationMethod intubationMethod;
    private Integer oxygenAdministration;
    private Boolean pleuralPuncture;
    private String specificAirwayMeasure;

    // *** C ***
    // Skin
    private Boolean isSkinCold;
    private Boolean isSkinSweaty;
    private Boolean isSkinPale;
    private Boolean isSkinCyanotic;

    private Boolean strongBleeding;
    private Boolean cpr;
    private LocalDateTime cprStartTime;
    private LocalDateTime cprStopTime;
    private Integer amountOfShocks;
    private String cprAbortDoctorsName;
    private Boolean ekgActive;

    private Boolean exhaustion;

    // Measures
    private Boolean shockPosition;
    private Boolean pressureBandage;
    private LocalDateTime tourniquetSince;
    private Boolean pelvicBinder;
    private String specificCirculationMeasure;

    // *** D ***
    private Boolean headache;
    private Boolean seizure;
    private Boolean speakingDisorder;

    private LimbSensation limbSensation; // NORMAL, TINGLING, NUMBNESS, PARALYSIS

    private Boolean isPupilLeftLightRigid;
    private Boolean isPupilRightLightRigid;

    private PupilSize pupilSizeLeft; // NARROW, MIDDLE, WIDE
    private PupilSize pupilSizeRight;

    private Boolean fastTestSuspicious;
    private Integer gcs;

}
