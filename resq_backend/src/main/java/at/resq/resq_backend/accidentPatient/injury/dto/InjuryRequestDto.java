package at.resq.resq_backend.accidentPatient.injury.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 23.04.2026
 * Time: 12:50
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InjuryRequestDto {
    private Long injuryLocationId;
    private String note;

    // !  use one or the other never both
    private Long injuryTypeId;
    private String customInjuryType;
}