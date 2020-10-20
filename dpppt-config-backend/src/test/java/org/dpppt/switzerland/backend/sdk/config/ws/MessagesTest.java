package org.dpppt.switzerland.backend.sdk.config.ws;

import static org.junit.Assert.assertEquals;

import org.dpppt.switzerland.backend.sdk.config.ws.model.ConfigResponse;
import org.dpppt.switzerland.backend.sdk.config.ws.model.WhatToDoPositiveTestTextsCollection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles({"cloud-dev"})
public class MessagesTest {

    @Test
    public void testWHatToDoPositiveTestTextLanguage() throws Exception {
        ConfigResponse configResponse = new ConfigResponse();
        WhatToDoPositiveTestTextsCollection whatToDoPositiveTestTexts =
                configResponse.getWhatToDoPositiveTestTexts();
        assertEquals(
                "Mit dem Covidcode...",
                whatToDoPositiveTestTexts.getDe().getEnterCovidcodeBoxSupertitle());
        assertEquals(
                "Avec le code COVID…",
                whatToDoPositiveTestTexts.getFr().getEnterCovidcodeBoxSupertitle());
        assertEquals(
                "Con il codice Covid...",
                whatToDoPositiveTestTexts.getIt().getEnterCovidcodeBoxSupertitle());
        assertEquals(
                "With the Covidcode",
                whatToDoPositiveTestTexts.getEn().getEnterCovidcodeBoxSupertitle());
        assertEquals(
                "Com o código COVID...",
                whatToDoPositiveTestTexts.getPt().getEnterCovidcodeBoxSupertitle());
        assertEquals(
                "Con el código Covid…",
                whatToDoPositiveTestTexts.getEs().getEnterCovidcodeBoxSupertitle());
        assertEquals(
                "Me kodin Covid...",
                whatToDoPositiveTestTexts.getSq().getEnterCovidcodeBoxSupertitle());
        assertEquals(
                "Sa Covid šifrom...",
                whatToDoPositiveTestTexts.getBs().getEnterCovidcodeBoxSupertitle());
        assertEquals(
                "Sa Covid šifrom...",
                whatToDoPositiveTestTexts.getHr().getEnterCovidcodeBoxSupertitle());
        assertEquals(
                "Sa Covid šifrom...",
                whatToDoPositiveTestTexts.getSr().getEnterCovidcodeBoxSupertitle());
        assertEquals(
                "Cun il code covid...",
                whatToDoPositiveTestTexts.getRm().getEnterCovidcodeBoxSupertitle());
        assertEquals(
                "Kovid kodu ile...",
                whatToDoPositiveTestTexts.getTr().getEnterCovidcodeBoxSupertitle());
        assertEquals("ብኮቪድኮድ", whatToDoPositiveTestTexts.getTi().getEnterCovidcodeBoxSupertitle());
    }
}
