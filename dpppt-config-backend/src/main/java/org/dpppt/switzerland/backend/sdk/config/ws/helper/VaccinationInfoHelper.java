package org.dpppt.switzerland.backend.sdk.config.ws.helper;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.dpppt.switzerland.backend.sdk.config.ws.model.Canton;
import org.dpppt.switzerland.backend.sdk.config.ws.model.Language;
import org.dpppt.switzerland.backend.sdk.config.ws.model.VaccinationBookingCanton;
import org.dpppt.switzerland.backend.sdk.config.ws.model.VaccinationBookingInfo;
import org.dpppt.switzerland.backend.sdk.config.ws.poeditor.Messages;

public class VaccinationInfoHelper {

    private static final String VACCINATION_BOOKING_PREFIX = "vaccination_booking_";

    private static final String VACCINATION_BOOKING_INFO_PREFIX = "vaccination_booking_info_";

    private static final String VACCINATION_IMPF_CHECK_PREFIX = "vaccination_impf_check_";

    private final Messages msg;

    public VaccinationInfoHelper(Messages msg) {
        this.msg = msg;
    }

    public Map<Language, List<VaccinationBookingCanton>> getVaccinationBookingCantons() {
        Map<Language, List<VaccinationBookingCanton>> result = new EnumMap<>(Language.class);
        for (Language language : Language.values()) {
            Locale l = language.toLocale();
            List<VaccinationBookingCanton> cantonInfos = new ArrayList<>();
            for (Canton canton : Canton.values()) {
                VaccinationBookingCanton cantonInfo = new VaccinationBookingCanton();
                cantonInfo.setName(msg.getMessage(canton.getNamePoeditorKey(), l));
                cantonInfo.setIconAndroid(canton.getAndroidIconAssetName());
                cantonInfo.setIconIos(canton.getIosIconAssetName());
                cantonInfo.setLinkUrl(
                        msg.getMessage(VACCINATION_BOOKING_PREFIX + canton.getId() + "_url", l));
                cantonInfos.add(cantonInfo);
            }
            result.put(language, cantonInfos);
        }
        return result;
    }

    public Map<Language, VaccinationBookingInfo> getVaccinationBookingInfo() {
        Map<Language, VaccinationBookingInfo> result = new EnumMap<>(Language.class);
        for (Language language : Language.values()) {
            Locale l = language.toLocale();
            VaccinationBookingInfo info = new VaccinationBookingInfo();
            info.setTitle(msg.getMessage(VACCINATION_BOOKING_INFO_PREFIX + "title", l));
            info.setText(msg.getMessage(VACCINATION_BOOKING_INFO_PREFIX + "text", l));
            info.setInfo(msg.getMessage(VACCINATION_BOOKING_INFO_PREFIX + "info", l));

            info.setImpfcheckTitle(msg.getMessage(VACCINATION_IMPF_CHECK_PREFIX + "title", l));
            info.setImpfcheckText(msg.getMessage(VACCINATION_IMPF_CHECK_PREFIX + "info_text", l));
            info.setImpfcheckButton(msg.getMessage(VACCINATION_IMPF_CHECK_PREFIX + "action", l));
            info.setImpfcheckUrl(msg.getMessage(VACCINATION_IMPF_CHECK_PREFIX + "url", l));

            result.put(language, info);
        }
        return result;
    }
}
