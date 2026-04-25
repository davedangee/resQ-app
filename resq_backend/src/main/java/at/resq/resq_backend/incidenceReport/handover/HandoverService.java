package at.resq.resq_backend.incidenceReport.handover;


import at.resq.resq_backend.incidenceReport.IncidenceReport;
import at.resq.resq_backend.incidenceReport.IncidenceReportRepository;
import at.resq.resq_backend.incidenceReport.handover.dto.HandoverRequestDto;
import at.resq.resq_backend.incidenceReport.handover.type.HandoverType;
import at.resq.resq_backend.incidenceReport.handover.type.HandoverTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 25.04.2026
 * Time: 17:45
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class HandoverService {
    private final HandoverRepository handoverRepository;
    private final HandoverTypeRepository handoverTypeRepository;
    private final IncidenceReportRepository incidenceReportRepository;

    public Handover getByReportId(Long reportId) {
        return handoverRepository.findByIncidenceReportId(reportId)
                .orElseThrow(() -> new NoSuchElementException("Handover not found for reportId: " + reportId));
    }

    public Handover create(Long reportId, HandoverRequestDto dto) {
        IncidenceReport report = incidenceReportRepository.findById(reportId)
                .orElseThrow(() -> new NoSuchElementException("IncidenceReport not found with id: " + reportId));

        if (handoverRepository.findByIncidenceReportId(reportId).isPresent()) {
            throw new IllegalArgumentException("Handover already exists for reportId: " + reportId);
        }

        HandoverType handoverType = handoverTypeRepository.findById(dto.getHandoverTypeId())
                .orElseThrow(() -> new NoSuchElementException("HandoverType not found with id: " + dto.getHandoverTypeId()));

        Handover handover = Handover.builder()
                .incidenceReport(report)
                .handoverType(handoverType)
                .handoverUnit(dto.getHandoverUnit())
                .targetHospital(dto.getTargetHospital())
                .handoverTime(dto.getHandoverTime())
                .build();

        return handoverRepository.save(handover);
    }

    public Handover update(Long reportId, HandoverRequestDto dto) {
        Handover existing = handoverRepository.findByIncidenceReportId(reportId)
                .orElseThrow(() -> new NoSuchElementException("Handover not found for reportId: " + reportId));

        HandoverType handoverType = handoverTypeRepository.findById(dto.getHandoverTypeId())
                .orElseThrow(() -> new NoSuchElementException("HandoverType not found with id: " + dto.getHandoverTypeId()));

        existing.setHandoverType(handoverType);
        existing.setHandoverUnit(dto.getHandoverUnit());
        existing.setTargetHospital(dto.getTargetHospital());
        existing.setHandoverTime(dto.getHandoverTime());

        return handoverRepository.save(existing);
    }

    public void delete(Long reportId) {
        Handover existing = handoverRepository.findByIncidenceReportId(reportId)
                .orElseThrow(() -> new NoSuchElementException("Handover not found for reportId: " + reportId));
        handoverRepository.delete(existing);
    }
}
