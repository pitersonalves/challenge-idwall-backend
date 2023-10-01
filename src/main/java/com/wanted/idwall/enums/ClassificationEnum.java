package com.wanted.idwall.enums;

public enum ClassificationEnum {

    VIOLACION("VIOLACION", "SEXUAL_VIOLATION"),
    VIOLACIÓN("VIOLACIÓN", "SEXUAL_VIOLATION"),
    VIOLE("VIOLE", "SEXUAL_VIOLATION"),
    VIOLENT("VIOLENT", "SEXUAL_VIOLATION"),
    VIOLENCES("VIOLENCES", "SEXUAL_VIOLATION"),
    ASESINATO("ASESINATO", "MURDER"),
    MURDER("MURDER", "MURDER"),
    HOMICIDIO("HOMICIDIO", "MURDER"),
    HOMICIDE("HOMICIDE", "MURDER"),
    INFANTICIDIO("INFANTICIDIO", "INFANTICIDE"),
    MEURTRE("MEURTRE", "MURDER"),
    TERROR("TERROR", "TERRORISM"),
    TORTURA("TORTURA", "DIZZINESS"),
    SEXUAL("SEXUAL", "SEXUAL_VIOLATION"),
    SECUESTRO("SECUESTRO", "KIDNAPPING"),
    FEMICIDIO("FEMICIDIO", "FEMINICIDE"),
    FEMINICIDIO("FEMINICIDIO", "FEMINICIDE"),
    EXPLOSIVE("EXPLOSIVE", "EXPLOSIVE"),
    AGRUPACIONES_ILÍCITAS("AGRUPACIONES ILÍCITAS", "ILLEGAL_GROUPS"),
    CRIMINAL_ORGANIZATION("CRIMINAL ORGANIZATION", "CRIMINAL_ORGANIZATION"),
    CRIMINAL_CONSPIRACY("CRIMINAL CONSPIRACY", "CRIMINAL_CONSPIRACY"),
    ASSOCIATION("ASSOCIATION", "ASSOCIATION"),
    FORGERY("FORGERY", "FORGERY"),
    ÉVASION("ÉVASION", "EVASION"),
    EVASION("EVASION", "EVASION"),
    MONEY("MONEY", "MONEY"),
    DRIVING("DRIVING", "DRIVING"),
    WOUNDING("WOUNDING", "WOUNDING"),
    COCAÏNE("COCAÏNE", "COCAINE"),
    STUPÉFIANTS("STUPÉFIANTS", "NARCOTIC"),
    NARCOTICS("NARCOTICS", "NARCOTIC"),
    NARCOTIC("NARCOTIC", "NARCOTIC"),
    NAROTICS("NAROTICS", "NARCOTIC"),
    TRAFICO("TRAFICO", "TRAFFICKING"),
    TRÁFICO("TRÁFICO", "TRAFFICKING"),
    EXTORTION("EXTORTION", "EXTORTION"),
    EXTORSIÓN("EXTORSIÓN", "EXTORTION"),
    ARRESTATION("ARRESTATION", "ARRESTATION"),
    BANDE_ORGANISÉE("BANDE ORGANISÉE", "CRIMINAL_ORGANIZATION"),
    BANDE_ORGANISEE("BANDE ORGANISEE", "CRIMINAL_ORGANIZATION"),
    STATUTORY("STATUTORY", "STATUTORY"),
    STOLEN("STOLEN", "STOLEN"),
    ARMED("ARMED", "ARMED"),
    MENACE("MENACE", "MENACE"),
    DAMAGE("DAMAGE", "DAMAGE"),
    SUSTANCIAS("SUSTANCIAS", "NARCOTIC");

    private String name;

    private String code;

    ClassificationEnum(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public static ClassificationEnum getEnumByName(String name) {
        for (ClassificationEnum classificationEnum : ClassificationEnum.values()) {
            if (classificationEnum.getName().equalsIgnoreCase(name)) {
                return classificationEnum;
            }
        }
        return null;
    }

}

