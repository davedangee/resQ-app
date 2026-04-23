package at.resq.resq_backend.accidentPatient.dto;


import at.resq.resq_backend.accidentPatient.AccidentPatient;
import at.resq.resq_backend.accidentPatient.embeddables.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 22.04.2026
 * Time: 20:24
 */

@Mapper(componentModel = "spring")
public interface AccidentPatientMapper {

    @Mapping(source = "consciousnessAssessment", target = "consciousnessAssessment")
    @Mapping(source = "airwayAssessment", target = "airwayAssessment")
    @Mapping(source = "breathingAssessment", target = "breathingAssessment")
    @Mapping(source = "circulationAssessment", target = "circulationAssessment")
    @Mapping(source = "cprInfo", target = "cprInfo")
    @Mapping(source = "disabilityAssessment", target = "disabilityAssessment")
    @Mapping(source = "exposureAssessment", target = "exposureAssessment")
    @Mapping(source = "sampler", target = "sampler")
    AccidentPatient toEntity(AccidentPatientDtos.AccidentPatientRequestDto dto);

    // Nested mappers
    GuardianInfo toEntity(AccidentPatientDtos.GuardianInfoDto dto);
    ConsciousnessAssessment toEntity(AccidentPatientDtos.ConsciousnessAssessmentDto dto);
    AirwayAssessment toEntity(AccidentPatientDtos.AirwayAssessmentDto dto);
    BreathingAssessment toEntity(AccidentPatientDtos.BreathingAssessmentDto dto);
    CirculationAssessment toEntity(AccidentPatientDtos.CirculationAssessmentDto dto);
    CprInfo toEntity(AccidentPatientDtos.CprInfoDto dto);
    DisabilityAssessment toEntity(AccidentPatientDtos.DisabilityAssessmentDto dto);
    ExposureAssessment toEntity(AccidentPatientDtos.ExposureAssessmentDto dto);
    Sampler toEntity(AccidentPatientDtos.SamplerDto dto);
}