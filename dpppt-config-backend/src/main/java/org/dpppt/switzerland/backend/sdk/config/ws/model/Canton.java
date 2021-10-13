package org.dpppt.switzerland.backend.sdk.config.ws.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Canton {
    AG("ag", "canton_aargau"),
    AR("ar", "canton_appenzell_ausserrhoden"),
    AI("ai", "canton_appenzell_innerrhoden"),
    BL("bl", "canton_basel_country"),
    BS("bs", "canton_basel_city"),
    BE("be", "canton_berne"),
    FR("fr", "canton_fribourg"),
    GE("ge", "canton_geneva"),
    GL("gl", "canton_glarus"),
    GR("gr", "canton_graubuenden"),
    JU("ju", "canton_jura"),
    LU("lu", "canton_lucerne"),
    NE("ne", "canton_neuchatel"),
    NW("nw", "canton_nidwalden"),
    OW("ow", "canton_obwalden"),
    SG("sg", "canton_st_gallen"),
    SH("sh", "canton_schaffhausen"),
    SZ("sz", "canton_schwyz"),
    SO("so", "canton_solothurn"),
    TG("tg", "canton_thurgovia"),
    TI("ti", "canton_ticino"),
    UR("ur", "canton_uri"),
    VS("vs", "canton_valais"),
    VD("vd", "canton_vaud"),
    ZG("zg", "canton_zug"),
    ZH("zh", "canton_zurich");

    private String id;
    private String namePoeditorKey;

    Canton(String id, String namePoeditorKey) {
        this.id = id;
        this.namePoeditorKey = namePoeditorKey;
    }

    public String getId() {
        return id;
    }

    public String getNamePoeditorKey() {
        return namePoeditorKey;
    }

    public String getAndroidIconAssetName() {
        return "icon_" + id;
    }

    public String getIosIconAssetName() {
        return "icon-" + id;
    }

    public static List<String> namePoeditorKeys() {
        return Arrays.stream(Canton.values())
                .map(Canton::getNamePoeditorKey)
                .collect(Collectors.toList());
    }
}
