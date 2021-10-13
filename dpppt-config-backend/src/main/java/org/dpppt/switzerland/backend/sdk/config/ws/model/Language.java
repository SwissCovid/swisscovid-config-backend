package org.dpppt.switzerland.backend.sdk.config.ws.model;

import com.fasterxml.jackson.annotation.JsonValue;
import java.util.List;
import java.util.Locale;

public enum Language {
    BS("bs"),
    DE("de"),
    EN("en"),
    ES("es"),
    FR("fr"),
    HR("hr"),
    IT("it"),
    PT("pt"),
    RM("rm"),
    SQ("sq"),
    SR("sr"),
    TI("ti"),
    TR("tr");

    private String key;

    Language(String key) {
        this.key = key;
    }

    @JsonValue
    public String getKey() {
        return key;
    }

    public Locale toLocale() {
        return Locale.forLanguageTag(this.getKey());
    }

    /**
     * for use during development when missing translations
     *
     * @return
     */
    public static List<Language> deOnly() {
        return List.of(Language.DE);
    }
}
