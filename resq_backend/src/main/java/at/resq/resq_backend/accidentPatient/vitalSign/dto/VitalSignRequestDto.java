package at.resq.resq_backend.accidentPatient.vitalSign.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 23.04.2026
 * Time: 10:10
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VitalSignRequestDto {
    private Long vitalSignTypeId;
    private String value;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;
}