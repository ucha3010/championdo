package com.championdo.torneo.util;

public enum SectionEnum {
    MORE18(Constantes.SECCION_AUTORIZACION_MAYOR_18,"Inscripción mayores de 18 años"),
    LESS18(Constantes.SECCION_AUTORIZACION_MENOR_18,"Inscripción menores de 18 años"),
    MANDATO(Constantes.SECCION_MANDATO,"Autorización para presentar licencia federativa"),
    NORMATIVASEPA(Constantes.SECCION_NORMATIVA_SEPA,"Autorización de adeudos SEPA"),
    NORMATIVASEPAFIRMADO(Constantes.SECCION_NORMATIVA_SEPA_FIRMADO,"Autorización de adeudos SEPA firmado"),
    TOURNAMENT(Constantes.SECCION_TORNEO,"Inscripción a torneo"),
    WHATSAPP(Constantes.SECCION_WHATSAPP,"Autorización para enviar información por WhatsApp");

    private final String name;
    private final String description;

    SectionEnum(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
