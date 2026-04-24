package at.resq.resq_backend.accidentPatient.dto;


import at.resq.resq_backend.accidentPatient.AccidentPatient;
import at.resq.resq_backend.accidentPatient.embeddables.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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
    AccidentPatient toEntity(AccidentPatientRequestDtos.AccidentPatientRequestDto dto);

    // Nested mappers
    GuardianInfo toEntity(AccidentPatientRequestDtos.GuardianInfoDto dto);
    ConsciousnessAssessment toEntity(AccidentPatientRequestDtos.ConsciousnessAssessmentDto dto);
    AirwayAssessment toEntity(AccidentPatientRequestDtos.AirwayAssessmentDto dto);
    BreathingAssessment toEntity(AccidentPatientRequestDtos.BreathingAssessmentDto dto);
    CirculationAssessment toEntity(AccidentPatientRequestDtos.CirculationAssessmentDto dto);
    CprInfo toEntity(AccidentPatientRequestDtos.CprInfoDto dto);
    DisabilityAssessment toEntity(AccidentPatientRequestDtos.DisabilityAssessmentDto dto);
    ExposureAssessment toEntity(AccidentPatientRequestDtos.ExposureAssessmentDto dto);
    Sampler toEntity(AccidentPatientRequestDtos.SamplerDto dto);
}