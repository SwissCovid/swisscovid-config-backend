package org.dpppt.switzerland.backend.sdk.config.ws.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.dpppt.switzerland.backend.sdk.config.ws.model.TestLocation;
import org.dpppt.switzerland.backend.sdk.config.ws.model.TestLocationCollection;
import org.dpppt.switzerland.backend.sdk.config.ws.poeditor.Messages;

/**
 * Holds the url for test locations for each canton.
 *
 * @author alig
 */
public class TestLocationHelper {

    private final Messages messages;
    private final TestLocationCollection testLocationCollection;
    private final Map<String, String> testInfoUrls;

    private static final List<String> ALL_CANTONS_AND_LIECHTENSTEIN =
            List.of(
                    "canton_aargau",
                    "canton_appenzell_ausserrhoden",
                    "canton_appenzell_innerrhoden",
                    "canton_basel_country",
                    "canton_basel_city",
                    "canton_berne",
                    "canton_fribourg",
                    "canton_geneva",
                    "canton_glarus",
                    "canton_graubuenden",
                    "canton_jura",
                    "canton_lucerne",
                    "canton_neuchatel",
                    "canton_nidwalden",
                    "canton_obwalden",
                    "canton_st_gallen",
                    "canton_schaffhausen",
                    "canton_schwyz",
                    "canton_solothurn",
                    "canton_thurgovia",
                    "canton_ticino",
                    "canton_uri",
                    "canton_valais",
                    "canton_vaud",
                    "canton_zug",
                    "canton_zurich",
                    "country_liechtenstein");

    public TestLocationHelper(Messages messages) {
        this.messages = messages;
        this.testLocationCollection = new TestLocationCollection();

        testLocationCollection.setBs(getTestLocationsForLanguage(Locale.forLanguageTag("bs")));
        testLocationCollection.setDe(getTestLocationsForLanguage(Locale.forLanguageTag("de")));
        testLocationCollection.setEn(getTestLocationsForLanguage(Locale.forLanguageTag("en")));
        testLocationCollection.setEs(getTestLocationsForLanguage(Locale.forLanguageTag("es")));
        testLocationCollection.setFr(getTestLocationsForLanguage(Locale.forLanguageTag("fr")));
        testLocationCollection.setHr(getTestLocationsForLanguage(Locale.forLanguageTag("hr")));
        testLocationCollection.setIt(getTestLocationsForLanguage(Locale.forLanguageTag("it")));
        testLocationCollection.setPt(getTestLocationsForLanguage(Locale.forLanguageTag("pt")));
        testLocationCollection.setRm(getTestLocationsForLanguage(Locale.forLanguageTag("rm")));
        testLocationCollection.setSq(getTestLocationsForLanguage(Locale.forLanguageTag("sq")));
        testLocationCollection.setSr(getTestLocationsForLanguage(Locale.forLanguageTag("sr")));
        testLocationCollection.setTi(getTestLocationsForLanguage(Locale.forLanguageTag("ti")));
        testLocationCollection.setTr(getTestLocationsForLanguage(Locale.forLanguageTag("tr")));

        this.testInfoUrls = new HashMap<>();
        testInfoUrls.put("bs", messages.getMessage("test_info_url", Locale.forLanguageTag("bs")));
        testInfoUrls.put("de", messages.getMessage("test_info_url", Locale.forLanguageTag("de")));
        testInfoUrls.put("en", messages.getMessage("test_info_url", Locale.forLanguageTag("en")));
        testInfoUrls.put("es", messages.getMessage("test_info_url", Locale.forLanguageTag("es")));
        testInfoUrls.put("fr", messages.getMessage("test_info_url", Locale.forLanguageTag("fr")));
        testInfoUrls.put("hr", messages.getMessage("test_info_url", Locale.forLanguageTag("hr")));
        testInfoUrls.put("it", messages.getMessage("test_info_url", Locale.forLanguageTag("it")));
        testInfoUrls.put("pt", messages.getMessage("test_info_url", Locale.forLanguageTag("pt")));
        testInfoUrls.put("rm", messages.getMessage("test_info_url", Locale.forLanguageTag("rm")));
        testInfoUrls.put("sq", messages.getMessage("test_info_url", Locale.forLanguageTag("sq")));
        testInfoUrls.put("sr", messages.getMessage("test_info_url", Locale.forLanguageTag("sr")));
        testInfoUrls.put("ti", messages.getMessage("test_info_url", Locale.forLanguageTag("ti")));
        testInfoUrls.put("tr", messages.getMessage("test_info_url", Locale.forLanguageTag("tr")));
    }

    private List<TestLocation> getTestLocationsForLanguage(Locale language) {
        List<TestLocation> testLocations = new ArrayList<>();
        for (String region : ALL_CANTONS_AND_LIECHTENSTEIN) {
            testLocations.add(
                    new TestLocation(
                            region, messages.getMessage("testlocation_url_" + region, language)));
        }
        return testLocations;
    }

    public TestLocationCollection getTestLocations() {
        return this.testLocationCollection;
    }

    public Map<String, String> getTestInfoUrls() {
        return this.testInfoUrls;
    }
}
