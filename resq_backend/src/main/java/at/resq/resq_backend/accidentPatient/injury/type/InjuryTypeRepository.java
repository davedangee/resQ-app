package at.resq.resq_backend.accidentPatient.injury.type;


import at.resq.resq_backend.accidentPatient.injury.Injury;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 22.04.2026
 * Time: 13:53
 */

public interface InjuryTypeRepository extends JpaRepository<InjuryType, Integer> {
}
