package at.resq.resq_backend.config;

import at.resq.resq_backend.accidentPatient.injury.Injury;
import at.resq.resq_backend.accidentPatient.injury.location.InjuryLocation;
import at.resq.resq_backend.accidentPatient.injury.location.InjuryLocationRepository;
import at.resq.resq_backend.accidentPatient.injury.type.InjuryType;
import at.resq.resq_backend.accidentPatient.injury.type.InjuryTypeRepository;
import at.resq.resq_backend.accidentPatient.medication.type.Medication;
import at.resq.resq_backend.accidentPatient.medication.type.MedicationRepository;
import at.resq.resq_backend.accidentPatient.vitalSign.type.VitalSignType;
import at.resq.resq_backend.accidentPatient.vitalSign.type.VitalSignTypeRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class DBInit {

    private final MedicationRepository medicationRepository;
    private final VitalSignTypeRepository vitalSignTypeRepository;
    private final InjuryLocationRepository injuryLocationRepository;
    private final InjuryTypeRepository injuryTypeRepository;


    @PostConstruct
    public void initialize() {
        System.out.println("""
                                      ________    __________                __                      .___
                _______   ____   _____\\_____  \\   \\______   \\_____    ____ |  | __ ____   ____    __| _/
                \\_  __ \\_/ __ \\ /  ___//  / \\  \\   |    |  _/\\__  \\ _/ ___\\|  |/ // __ \\ /    \\  / __ |\s
                 |  | \\/\\  ___/ \\___ \\/   \\_/.  \\  |    |   \\ / __ \\\\  \\___|    <\\  ___/|   |  \\/ /_/ |\s
                 |__|    \\___  >____  >_____\\ \\_/  |______  /(____  /\\___  >__|_ \\\\___  >___|  /\\____ |\s
                             \\/     \\/       \\__>         \\/      \\/     \\/     \\/    \\/     \\/      \\/        \s""");
        System.out.println("DBInit.initialize");

        LOCATIONS.forEach(location -> {
            injuryLocationRepository.save(new InjuryLocation(null, location));
        });

        TYPES.forEach(type -> {
            injuryTypeRepository.save(new InjuryType(null, type, false));
        });

        MEDICATIONS.forEach(medication -> {
            medicationRepository.save(new Medication(null, medication));
        });

        VITALS.forEach((vital, unit) -> {
            vitalSignTypeRepository.save(new VitalSignType(null, vital, unit));
        });

    }

    public static final List<String> LOCATIONS = List.of(
            "HEAD",
            "NECK",
            "CHEST",
            "UPPER_BACK",
            "LOWER_BACK",
            "ABDOMEN",
            "PELVIS",
            "LEFT_SHOULDER",
            "RIGHT_SHOULDER",
            "LEFT_UPPER_ARM",
            "RIGHT_UPPER_ARM",
            "LEFT_ELBOW",
            "RIGHT_ELBOW",
            "LEFT_FOREARM",
            "RIGHT_FOREARM",
            "LEFT_HAND",
            "RIGHT_HAND",
            "LEFT_THIGH",
            "RIGHT_THIGH",
            "LEFT_KNEE",
            "RIGHT_KNEE",
            "LEFT_LOWER_LEG",
            "RIGHT_LOWER_LEG",
            "LEFT_FOOT",
            "RIGHT_FOOT"
    );

    public static final List<String> TYPES = List.of(
            "BRUISE",
            "FRACTURE",
            "WOUND",
            "FROSTBITE",
            "BURN",
            "PAIN",
            "SENSORY_IMPAIRMENT",
            "CIRCULATION_IMPAIRMENT",
            "PARALYSIS"
    );

    public static final List<String> MEDICATIONS = List.of(
            "SUPRARENIN_1MG_ML",
            "DIBONDRIN_30MG_2ML",
            "HISTAKUT_4MG_4ML",
            "PREDNISOLUT_250MG_5ML",
            "ASS_GENERICON_TABL_500MG",
            "BERODUAL_DOSIERAEROSOL",
            "COMBIVENT_INHALATIONSLSG",
            "SULTANOL_INHALATIONSLSG",
            "MEXALEN_TABLETTE",
            "MEXALEN_SUPPOSITORIUM",
            "STESOLID_REKTIOLE",
            "MIDAZOLAM_ACCORD_5MG_ML",
            "GLUCOSE",
            "ELO_MEL_ISOTON",
            "EBRANTIL_25MG_5ML",
            "NALOXON_0_4MG_ML",
            "PARACETAMOL_10MG_ML",
            "PENTHROP_METHOXYFLURAN",
            "KETANEST_S_50MG_2ML",
            "MIDAZOLAM_ACCORD_5MG_5ML",
            "ONDANSETRON_4MG_2ML",
            "FLUMAZENIL_0_5MG_5ML",
            "CYCLOKAPRON_500MG_5ML",
            // Atem-Kreislaufstillstand
            "L_ADRENALIN_1_10000",
            "AMIODARON_150MG_3ML"
    );

    public static final Map<String, String> VITALS = Map.of(
            "BLOOD_PRESSURE", "mmHg",
            "HEART_RATE", "bpm",
            "OXYGEN_SATURATION", "%",
            "RESPIRATORY_RATE", "breaths/min",
            "TEMPERATURE", "°C",
            "BLOOD_GLUCOSE", "mg/dL",
            "GCS", "points",
            "HYPOTHERMIA_STAGE", "stage"
    );
}
