package org.dpppt.switzerland.backend.sdk.config.ws.helper;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.dpppt.switzerland.backend.sdk.config.ws.model.Canton;
import org.dpppt.switzerland.backend.sdk.config.ws.model.Language;
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
    private final Map<Language, String> testInfoUrls;

    private static final List<String> ALL_CANTONS_AND_LIECHTENSTEIN;

    static {
        {
            ALL_CANTONS_AND_LIECHTENSTEIN = Canton.namePoeditorKeys();
            ALL_CANTONS_AND_LIECHTENSTEIN.add("country_liechtenstein");
        }
    }

    public TestLocationHelper(Messages messages) {
        this.messages = messages;
        this.testLocationCollection = new TestLocationCollection();

        testLocationCollection.setBs(getTestLocationsForLanguage(Language.BS.toLocale()));
        testLocationCollection.setDe(getTestLocationsForLanguage(Language.DE.toLocale()));
        testLocationCollection.setEn(getTestLocationsForLanguage(Language.EN.toLocale()));
        testLocationCollection.setEs(getTestLocationsForLanguage(Language.ES.toLocale()));
        testLocationCollection.setFr(getTestLocationsForLanguage(Language.FR.toLocale()));
        testLocationCollection.setHr(getTestLocationsForLanguage(Language.HR.toLocale()));
        testLocationCollection.setIt(getTestLocationsForLanguage(Language.IT.toLocale()));
        testLocationCollection.setPt(getTestLocationsForLanguage(Language.PT.toLocale()));
        testLocationCollection.setRm(getTestLocationsForLanguage(Language.RM.toLocale()));
        testLocationCollection.setSq(getTestLocationsForLanguage(Language.SQ.toLocale()));
        testLocationCollection.setSr(getTestLocationsForLanguage(Language.SR.toLocale()));
        testLocationCollection.setTi(getTestLocationsForLanguage(Language.TI.toLocale()));
        testLocationCollection.setTr(getTestLocationsForLanguage(Language.TR.toLocale()));

        this.testInfoUrls = new EnumMap<>(Language.class);
        testInfoUrls.put(Language.BS, messages.getMessage("test_info_url", Language.BS.toLocale()));
        testInfoUrls.put(Language.DE, messages.getMessage("test_info_url", Language.DE.toLocale()));
        testInfoUrls.put(Language.EN, messages.getMessage("test_info_url", Language.EN.toLocale()));
        testInfoUrls.put(Language.ES, messages.getMessage("test_info_url", Language.ES.toLocale()));
        testInfoUrls.put(Language.FR, messages.getMessage("test_info_url", Language.FR.toLocale()));
        testInfoUrls.put(Language.HR, messages.getMessage("test_info_url", Language.HR.toLocale()));
        testInfoUrls.put(Language.IT, messages.getMessage("test_info_url", Language.IT.toLocale()));
        testInfoUrls.put(Language.PT, messages.getMessage("test_info_url", Language.PT.toLocale()));
        testInfoUrls.put(Language.RM, messages.getMessage("test_info_url", Language.RM.toLocale()));
        testInfoUrls.put(Language.SQ, messages.getMessage("test_info_url", Language.SQ.toLocale()));
        testInfoUrls.put(Language.SR, messages.getMessage("test_info_url", Language.SR.toLocale()));
        testInfoUrls.put(Language.TI, messages.getMessage("test_info_url", Language.TI.toLocale()));
        testInfoUrls.put(Language.TR, messages.getMessage("test_info_url", Language.TR.toLocale()));
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

    public Map<Language, String> getTestInfoUrls() {
        return this.testInfoUrls;
    }
}
