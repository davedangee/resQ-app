package at.resq.resq_backend.incidenceReport.handover.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 25.04.2026
 * Time: 17:45
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HandoverRequestDto {
    private Long handoverTypeId;
    private String handoverUnit;
    private String targetHospital;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime handoverTime;
}
