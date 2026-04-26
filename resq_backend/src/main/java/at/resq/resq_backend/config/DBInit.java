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
import at.resq.resq_backend.incidenceReport.handover.type.HandoverType;
import at.resq.resq_backend.incidenceReport.handover.type.HandoverTypeRepository;
import at.resq.resq_backend.rescueOperation.referenceEntities.activity.Activity;
import at.resq.resq_backend.rescueOperation.referenceEntities.activity.ActivityRepository;
import at.resq.resq_backend.rescueOperation.referenceEntities.alertType.AlertType;
import at.resq.resq_backend.rescueOperation.referenceEntities.alertType.AlertTypeRepository;
import at.resq.resq_backend.rescueOperation.referenceEntities.evacuationType.EvacuationType;
import at.resq.resq_backend.rescueOperation.referenceEntities.evacuationType.EvacuationTypeRepository;
import at.resq.resq_backend.rescueOperation.referenceEntities.policeRole.PoliceRole;
import at.resq.resq_backend.rescueOperation.referenceEntities.policeRole.PoliceRoleRepository;
import at.resq.resq_backend.rescueOperation.referenceEntities.protectiveGear.ProtectiveGear;
import at.resq.resq_backend.rescueOperation.referenceEntities.protectiveGear.ProtectiveGearRepository;
import at.resq.resq_backend.rescueOperation.referenceEntities.season.Season;
import at.resq.resq_backend.rescueOperation.referenceEntities.season.SeasonRepository;
import at.resq.resq_backend.rescueOperation.referenceEntities.slopeCondition.SlopeCondition;
import at.resq.resq_backend.rescueOperation.referenceEntities.slopeCondition.SlopeConditionRepository;
import at.resq.resq_backend.rescueOperation.referenceEntities.snowComposition.SnowComposition;
import at.resq.resq_backend.rescueOperation.referenceEntities.snowComposition.SnowCompositionRepository;
import at.resq.resq_backend.rescueOperation.referenceEntities.weather.Weather;
import at.resq.resq_backend.rescueOperation.referenceEntities.weather.WeatherRepository;
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
    private final HandoverTypeRepository handoverTypeRepository;
    private final WeatherRepository weatherRepository;
    private final SlopeConditionRepository slopeConditionRepository;
    private final SnowCompositionRepository snowCompositionRepository;
    private final SeasonRepository seasonRepository;
    private final PoliceRoleRepository policeRoleRepository;
    private final ActivityRepository activityRepository;
    private final EvacuationTypeRepository evacuationTypeRepository;
    private final ProtectiveGearRepository protectiveGearRepository;
    private final AlertTypeRepository alertTypeRepository;

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

        if (injuryLocationRepository.count() == 0)
            LOCATIONS.forEach(location -> injuryLocationRepository.save(new InjuryLocation(null, location)));

        if (injuryTypeRepository.count() == 0)
            TYPES.forEach(type -> injuryTypeRepository.save(new InjuryType(null, type, false)));

        if (medicationRepository.count() == 0)
            MEDICATIONS.forEach(medication -> medicationRepository.save(new Medication(null, medication)));

        if (vitalSignTypeRepository.count() == 0)
            VITALS.forEach((vital, unit) -> vitalSignTypeRepository.save(new VitalSignType(null, vital, unit)));

        if (handoverTypeRepository.count() == 0)
            HANDOVER_TYPES.forEach((type, description) -> handoverTypeRepository.save(new HandoverType(null, type, description)));

        if (weatherRepository.count() == 0)
            WEATHER.forEach(label -> weatherRepository.save(new Weather(null, label)));

        if (slopeConditionRepository.count() == 0)
            SLOPE_CONDITIONS.forEach(label -> slopeConditionRepository.save(new SlopeCondition(null, label)));

        if (snowCompositionRepository.count() == 0)
            SNOW_COMPOSITIONS.forEach(label -> snowCompositionRepository.save(new SnowComposition(null, label)));

        if (seasonRepository.count() == 0)
            SEASONS.forEach(label -> seasonRepository.save(new Season(null, label)));

        if (policeRoleRepository.count() == 0)
            POLICE_ROLES.forEach(label -> policeRoleRepository.save(new PoliceRole(null, label)));

        if (activityRepository.count() == 0)
            ACTIVITIES.forEach(label -> activityRepository.save(new Activity(null, label, false)));

        if (evacuationTypeRepository.count() == 0)
            EVACUATION_TYPES.forEach(label -> evacuationTypeRepository.save(new EvacuationType(null, label, false)));

        if (protectiveGearRepository.count() == 0)
            PROTECTIVE_GEARS.forEach(label -> protectiveGearRepository.save(new ProtectiveGear(null, label, false)));

        if (alertTypeRepository.count() == 0)
            ALERT_TYPES.forEach(label -> alertTypeRepository.save(new AlertType(null, label, false)));
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

    public static final Map<String, String> HANDOVER_TYPES = Map.of(
            "HELICOPTER_EMS", "Helicopter Emergency Medical Service",
            "PHYSICIAN_RESPONSE_VEHICLE", "Emergency physician response vehicle",
            "AMBULANCE", "Ambulance",
            "PRIVATE_TRANSPORT", "Private transport",
            "DOCTOR", "Doctor"
    );

    public static final List<String> WEATHER = List.of(
            "SUNSHINE",
            "SNOWFALL",
            "CLOUDY",
            "FOG",
            "RAIN"
    );

    public static final List<String> SLOPE_CONDITIONS = List.of(
            "GRIPPY",
            "SMOOTH_HARD",
            "SOFT",
            "FRESH_SNOW",
            "DEEP_SNOW",
            "LOW_SNOW_SPOT"
    );

    public static final List<String> SNOW_COMPOSITIONS = List.of(
            "NATURAL_SNOW",
            "ARTIFICIAL_SNOW",
            "MOSTLY_NATURAL_SNOW",
            "MOSTLY_ARTIFICIAL_SNOW"
    );

    public static final List<String> SEASONS = List.of(
            "2024/25", "2025/26", "2026/27", "2027/28", "2028/29",
            "2029/30", "2030/31", "2031/32", "2032/33", "2033/34",
            "2034/35", "2035/36", "2036/37", "2037/38", "2038/39",
            "2039/40", "2040/41", "2041/42", "2042/43", "2043/44",
            "2044/45", "2045/46", "2046/47", "2047/48", "2048/49",
            "2049/50"
    );

    public static final List<String> POLICE_ROLES = List.of(
            "POLICE_NOTIFIED",
            "POLICE_PRESENT"
    );

    public static final List<String> ACTIVITIES = List.of(
            "SKIING",
            "SNOWBOARDING",
            "SKI_TOURING"
    );

    public static final List<String> EVACUATION_TYPES = List.of(
            "AKJA",
            "SKIDOO",
            "SKIDOO_WITH_AKJA_TRAILER",
            "CABLE_CAR_DESCENT",
            "SELF_DESCENT",
            "HELICOPTER"
    );

    public static final List<String> PROTECTIVE_GEARS = List.of(
            "HELMET",
            "BACK_PROTECTOR"
    );

    public static final List<String> ALERT_TYPES = List.of(
            "RADIO",
            "PHONE"
    );
}
