package at.resq.resq_backend.accidentPatient.medication.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 23.04.2026
 * Time: 08:41
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicationAdministrationRequestDto {

    private Long medicationId; // needed for PUT
    private String dosage;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;
}
