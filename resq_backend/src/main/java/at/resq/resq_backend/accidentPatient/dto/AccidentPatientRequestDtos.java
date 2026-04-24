package at.resq.resq_backend.accidentPatient.dto;


import at.resq.resq_backend.accidentPatient.enums.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 22.04.2026
 * Time: 20:07
 */

public class AccidentPatientRequestDtos {

    @Data @Builder @NoArgsConstructor @AllArgsConstructor
    public static class AccidentPatientRequestDto {
        private String mountainRescueInsurance;
        private GuestType guestType;
        private String holidayAddress;
        private Integer dayOfHoliday;
        private PatientPosition patientPosition;
        private String patientPositionSpecification;

        @Valid private GuardianInfoDto guardian;
        @Valid private ConsciousnessAssessmentDto consciousnessAssessment;
        @Valid private AirwayAssessmentDto airwayAssessment;
        @Valid private BreathingAssessmentDto breathingAssessment;
        @Valid private CirculationAssessmentDto circulationAssessment;
        @Valid private CprInfoDto cprInfo;
        @Valid private DisabilityAssessmentDto disabilityAssessment;
        @Valid private ExposureAssessmentDto exposureAssessment;
        @Valid private SamplerDto sampler;
    }

    @Data @Builder @NoArgsConstructor @AllArgsConstructor
    public static class GuardianInfoDto {
        private String firstName;
        private String lastName;
        private String socialSecurityNumber;
        private String phoneNumber;
    }

    @Data @Builder @NoArgsConstructor @AllArgsConstructor
    public static class ConsciousnessAssessmentDto {
        private ConsciousnessState state;
        private Boolean orientedInTime;
        private Boolean orientedInPlace;
        private Boolean orientedToPerson;
        private Boolean orientedToSituation;
    }

    @Data @Builder @NoArgsConstructor @AllArgsConstructor
    public static class AirwayAssessmentDto {
        private Boolean isAirwayClear;
        private AirwayClearanceMethod airwayClearanceMethod;
        private String specificAirwayMeasure;
    }

    @Data @Builder @NoArgsConstructor @AllArgsConstructor
    public static class BreathingAssessmentDto {
        private Boolean isRespirationNormal;
        private Boolean isBreathShortened;
        private Boolean manualVentilation;
        private IntubationMethod intubationMethod;
        private Integer oxygenAdministration;
        private Boolean pleuralPuncture;
    }

    @Data @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CirculationAssessmentDto {
        private Boolean isSkinCold;
        private Boolean isSkinSweaty;
        private Boolean isSkinPale;
        private Boolean isSkinCyanotic;
        private Boolean strongBleeding;
        private Boolean exhaustion;
        private Boolean shockPosition;
        private Boolean pressureBandage;
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime tourniquetSince;
        private Boolean pelvicBinder;
        private String specificCirculationMeasure;
    }

    @Data @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CprInfoDto {
        private Boolean active;
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime startTime;
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime stopTime;
        private Integer amountOfShocks;
        private String abortDoctorsName;
        private Boolean ekgActive;
    }

    @Data @Builder @NoArgsConstructor @AllArgsConstructor
    public static class DisabilityAssessmentDto {
        private Boolean headache;
        private Boolean seizure;
        private Boolean speakingDisorder;
        private LimbSensation limbSensation;
        private Boolean fastTestSuspicious;
        private Integer gcs;
        private Boolean isPupilLeftLightRigid;
        private Boolean isPupilRightLightRigid;
        private PupilSize pupilSizeLeft;
        private PupilSize pupilSizeRight;
    }

    @Data @Builder @NoArgsConstructor @AllArgsConstructor
    public static class ExposureAssessmentDto {
        private HypothermiaState hypothermiaState;
        @Min(0) @Max(10)
        private Integer painScaleValue;
        @Min(1) @Max(7)
        private Integer nacaStatus;
        private PatientPositioning patientPositioning;
    }

    @Data @Builder @NoArgsConstructor @AllArgsConstructor
    public static class SamplerDto {
        private String symptomsAndPain;
        private String allergies;
        private String patientMedication;
        private String patientHistory;
        private String lastMealAndDrink;
        private String incident;
        private String risks;
        private Boolean bloodThinnedPatient;
    }
}
