package org.dpppt.switzerland.backend.sdk.config.ws.helper;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Holds the url for test locations for each canton.
 * 
 * @author alig
 *
 */
public class TestLocationHelper {

	// make sure its ordered
	private static final Map<String, String> TEST_LOCATIONS = new LinkedHashMap<>();

	static {
		TEST_LOCATIONS.put("canton_aargau", "https://www.ag.ch/de/themen_1/coronavirus_2/coronavirus.jsp");
		TEST_LOCATIONS.put("canton_appenzell_ausserrhoden",
				"https://www.ar.ch/verwaltung/departement-gesundheit-und-soziales/amt-fuer-gesundheit/informationsseite-coronavirus/");
		TEST_LOCATIONS.put("canton_appenzell_innerrhoden",
				"https://www.ai.ch/themen/gesundheit-alter-und-soziales/gesundheitsfoerderung-und-praevention/uebertragbare-krankheiten/coronavirus");
		TEST_LOCATIONS.put("canton_basel_country",
				"https://www.baselland.ch/politik-und-behorden/direktionen/volkswirtschafts-und-gesundheitsdirektion/amt-fur-gesundheit/medizinische-dienste/kantonsarztlicher-dienst/aktuelles");
		TEST_LOCATIONS.put("canton_basel_city", "https://www.coronavirus.bs.ch/");
		TEST_LOCATIONS.put("canton_berne", "http://www.be.ch/corona");
		TEST_LOCATIONS.put("canton_fribourg",
				"https://www.fr.ch/de/gesundheit/covid-19/coronavirus-aktuelle-informationen");
		TEST_LOCATIONS.put("canton_geneva", "https://www.ge.ch/covid-19-se-proteger-prevenir-nouvelle-vague");
		TEST_LOCATIONS.put("canton_glarus",
				"https://www.gl.ch/verwaltung/finanzen-und-gesundheit/gesundheit/coronavirus.html/4817");
		TEST_LOCATIONS.put("canton_graubuenden",
				"https://www.gr.ch/DE/institutionen/verwaltung/djsg/ga/coronavirus/info/Seiten/Start.aspx");
		TEST_LOCATIONS.put("canton_jura",
				"https://www.jura.ch/fr/Autorites/Coronavirus/Accueil/Coronavirus-Informations-officielles-a-la-population-jurassienne.html");
		TEST_LOCATIONS.put("canton_lucerne",
				"https://gesundheit.lu.ch/themen/Humanmedizin/Infektionskrankheiten/Coronavirus");
		TEST_LOCATIONS.put("canton_neuchatel",
				"https://www.ne.ch/autorites/DFS/SCSP/medecin-cantonal/maladies-vaccinations/Pages/Coronavirus.aspx");
		TEST_LOCATIONS.put("canton_nidwalden", "https://www.nw.ch/gesundheitsamtdienste/6044");
		TEST_LOCATIONS.put("canton_obwalden", "https://www.ow.ch/de/verwaltung/dienstleistungen/?dienst_id=5962");
		TEST_LOCATIONS.put("canton_st_gallen", "https://www.sg.ch/tools/informationen-coronavirus.html");
		TEST_LOCATIONS.put("canton_schaffhausen",
				"https://sh.ch/CMS/Webseite/Kanton-Schaffhausen/Beh-rde/Verwaltung/Departement-des-Innern/Gesundheitsamt-2954701-DE.html");
		TEST_LOCATIONS.put("canton_schwyz",
				"https://www.sz.ch/behoerden/information-medien/medienmitteilungen/coronavirus.html/72-416-412-1379-6948");
		TEST_LOCATIONS.put("canton_solothurn", "https://corona.so.ch/");
		TEST_LOCATIONS.put("canton_thurgovia", "https://www.tg.ch/news/fachdossier-coronavirus.html/10552");
		TEST_LOCATIONS.put("canton_ticino", "https://www4.ti.ch/dss/dsp/covid19/home/");
		TEST_LOCATIONS.put("canton_uri", "https://www.ur.ch/themen/2962");
		TEST_LOCATIONS.put("canton_valais", "https://www.vs.ch/de/web/coronavirus");
		TEST_LOCATIONS.put("canton_vaud",
				"https://www.vd.ch/toutes-les-actualites/hotline-et-informations-sur-le-coronavirus/");
		TEST_LOCATIONS.put("canton_zug", "https://www.zg.ch/behoerden/gesundheitsdirektion/amt-fuer-gesundheit/corona");
		TEST_LOCATIONS.put("canton_zurich", "https://www.zh.ch/de/gesundheit/coronavirus.html");
		TEST_LOCATIONS.put("country_liechtenstein", "https://www.llv.li/inhalt/118724/amtsstellen/coronavirus");
	}

	public static Map<String, String> getTestLocations() {
		return TEST_LOCATIONS;
	}

}
