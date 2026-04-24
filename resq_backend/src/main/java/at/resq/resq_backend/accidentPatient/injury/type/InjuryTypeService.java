package at.resq.resq_backend.accidentPatient.injury.type;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 23.04.2026
 * Time: 13:39
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class InjuryTypeService {
    private final InjuryTypeRepository injuryTypeRepository;

    public List<InjuryType> getAllInjuryTypes() {
        return injuryTypeRepository.findAll()
                .stream()
                .filter(injuryType -> injuryType.getUserDefined().equals(Boolean.FALSE))
                .toList();
    }

    public InjuryType getInjuryTypeById(Long id) {
        return injuryTypeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("InjuryType not found with id: " + id));
    }
}
