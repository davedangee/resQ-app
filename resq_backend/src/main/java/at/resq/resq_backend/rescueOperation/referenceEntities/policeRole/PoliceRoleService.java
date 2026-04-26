package at.resq.resq_backend.rescueOperation.referenceEntities.policeRole;


import at.resq.resq_backend.exceptionHandling.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 26.04.2026
 * Time: 17:00
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class PoliceRoleService {
    private final PoliceRoleRepository policeRoleRepository;

    public List<PoliceRole> getAllPoliceRoles() {
        return policeRoleRepository.findAll();
    }

    public PoliceRole getPoliceRoleById(Long id) {
        return policeRoleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PoliceRole not found with id " + id));
    }
}
