package org.dpppt.switzerland.backend.sdk.config.ws.helper;

import java.util.Locale;
import org.dpppt.switzerland.backend.sdk.config.ws.model.ConfigResponse;
import org.dpppt.switzerland.backend.sdk.config.ws.model.InfoBox;
import org.dpppt.switzerland.backend.sdk.config.ws.model.InfoBoxCollection;
import org.dpppt.switzerland.backend.sdk.config.ws.poeditor.Messages;

public class MockHelper {

	public static ConfigResponse mockConfigResponseWithInfoBox(boolean dismissible,
			Messages messages) {
		ConfigResponse configResponse = new ConfigResponse();

		String hearingImpairedInfo = messages.getMessage("hearing_impaired_info", Locale.forLanguageTag("de"));

		InfoBox infoBoxde = new InfoBox();
		infoBoxde.setMsg("Hier steht ein Text. Das kann ein Hinweis sein. Je länger umso mehr Platz DE");
		infoBoxde.setTitle("Hinweis DE");
		infoBoxde.setUrlTitle("Und ein externer Link DE");
		infoBoxde.setUrl("https://www.bag.admin.ch/bag/de/home.html");
		infoBoxde.setIsDismissible(dismissible);
		infoBoxde.setHearingImpairedInfo(hearingImpairedInfo + " DE");

		InfoBox infoBoxfr = new InfoBox();
		infoBoxfr.setMsg("Hier steht ein Text. Das kann ein Hinweis sein. Je länger umso mehr Platz FR");
		infoBoxfr.setTitle("Hinweis FR");
		infoBoxfr.setUrlTitle("Und ein externer Link FR");
		infoBoxfr.setUrl("https://www.bag.admin.ch/bag/fr/home.html");
		infoBoxfr.setIsDismissible(dismissible);
		infoBoxfr.setHearingImpairedInfo(hearingImpairedInfo + " FR");

		InfoBox infoBoxit = new InfoBox();
		infoBoxit.setMsg("Hier steht ein Text. Das kann ein Hinweis sein. Je länger umso mehr Platz IT");
		infoBoxit.setTitle("Hinweis IT");
		infoBoxit.setUrlTitle("Und ein externer Link IT");
		infoBoxit.setUrl("https://www.bag.admin.ch/bag/it/home.html");
		infoBoxit.setIsDismissible(dismissible);
		infoBoxit.setHearingImpairedInfo(hearingImpairedInfo + " IT");

		InfoBox infoBoxen = new InfoBox();
		infoBoxen.setMsg("Hier steht ein Text. Das kann ein Hinweis sein. Je länger umso mehr Platz EN");
		infoBoxen.setTitle("Hinweis EN");
		infoBoxen.setUrlTitle("Und ein externer Link EN");
		infoBoxen.setUrl("https://www.bag.admin.ch/bag/en/home.html");
		infoBoxen.setIsDismissible(dismissible);
		infoBoxen.setHearingImpairedInfo(hearingImpairedInfo + " EN");

		InfoBox infoBoxpt = new InfoBox();
		infoBoxpt.setMsg("Hier steht ein Text. Das kann ein Hinweis sein. Je länger umso mehr Platz PT");
		infoBoxpt.setTitle("Hinweis PT");
		infoBoxpt.setUrlTitle("Und ein externer Link PT");
		infoBoxpt.setUrl("https://www.bag.admin.ch/bag/pt/home.html");
		infoBoxpt.setIsDismissible(dismissible);
		infoBoxpt.setHearingImpairedInfo(hearingImpairedInfo + " PT");

		InfoBox infoBoxes = new InfoBox();
		infoBoxes.setMsg("Hier steht ein Text. Das kann ein Hinweis sein. Je länger umso mehr Platz ES");
		infoBoxes.setTitle("Hinweis ES");
		infoBoxes.setUrlTitle("Und ein externer Link ES");
		infoBoxes.setUrl("https://www.bag.admin.ch/bag/en/home.html");
		infoBoxes.setIsDismissible(dismissible);
		infoBoxes.setHearingImpairedInfo(hearingImpairedInfo + " ES");

		InfoBox infoBoxsq = new InfoBox();
		infoBoxsq.setMsg("Hier steht ein Text. Das kann ein Hinweis sein. Je länger umso mehr Platz SQ");
		infoBoxsq.setTitle("Hinweis SQ");
		infoBoxsq.setUrlTitle("Und ein externer Link SQ");
		infoBoxsq.setUrl("https://www.bag.admin.ch/bag/en/home.html");
		infoBoxsq.setIsDismissible(dismissible);
		infoBoxsq.setHearingImpairedInfo(hearingImpairedInfo + " SQ");

		InfoBox infoBoxbs = new InfoBox();
		infoBoxbs.setMsg("Hier steht ein Text. Das kann ein Hinweis sein. Je länger umso mehr Platz BS");
		infoBoxbs.setTitle("Hinweis BS");
		infoBoxbs.setUrlTitle("Und ein externer Link BS");
		infoBoxbs.setUrl("https://www.bag.admin.ch/bag/en/home.html");
		infoBoxbs.setIsDismissible(dismissible);
		infoBoxbs.setHearingImpairedInfo(hearingImpairedInfo + " BS");

		InfoBox infoBoxhr = new InfoBox();
		infoBoxhr.setMsg("Hier steht ein Text. Das kann ein Hinweis sein. Je länger umso mehr Platz HR");
		infoBoxhr.setTitle("Hinweis HR");
		infoBoxhr.setUrlTitle("Und ein externer Link HR");
		infoBoxhr.setUrl("https://www.bag.admin.ch/bag/en/home.html");
		infoBoxhr.setIsDismissible(dismissible);
		infoBoxhr.setHearingImpairedInfo(hearingImpairedInfo + " HR");

		InfoBox infoBoxrm = new InfoBox();
		infoBoxrm.setMsg("Hier steht ein Text. Das kann ein Hinweis sein. Je länger umso mehr Platz RM");
		infoBoxrm.setTitle("Hinweis RM");
		infoBoxrm.setUrlTitle("Und ein externer Link RM");
		infoBoxrm.setUrl("https://www.bag.admin.ch/bag/en/home.html");
		infoBoxrm.setIsDismissible(dismissible);
		infoBoxrm.setHearingImpairedInfo(hearingImpairedInfo + " RM");

		InfoBox infoBoxsr = new InfoBox();
		infoBoxsr.setMsg("Hier steht ein Text. Das kann ein Hinweis sein. Je länger umso mehr Platz SR");
		infoBoxsr.setTitle("Hinweis SR");
		infoBoxsr.setUrlTitle("Und ein externer Link SR");
		infoBoxsr.setUrl("https://www.bag.admin.ch/bag/en/home.html");
		infoBoxsr.setIsDismissible(dismissible);
		infoBoxsr.setHearingImpairedInfo(hearingImpairedInfo + " SR");

		InfoBox infoBoxtr = new InfoBox();
		infoBoxtr.setMsg("Hier steht ein Text. Das kann ein Hinweis sein. Je länger umso mehr Platz TR");
		infoBoxtr.setTitle("Hinweis TR");
		infoBoxtr.setUrlTitle("Und ein externer Link TR");
		infoBoxtr.setUrl("https://www.bag.admin.ch/bag/en/home.html");
		infoBoxtr.setIsDismissible(dismissible);
		infoBoxtr.setHearingImpairedInfo(hearingImpairedInfo + " TR");

		InfoBox infoBoxti = new InfoBox();
		infoBoxti.setMsg("Hier steht ein Text. Das kann ein Hinweis sein. Je länger umso mehr Platz TI");
		infoBoxti.setTitle("Hinweis TI");
		infoBoxti.setUrlTitle("Und ein externer Link TI");
		infoBoxti.setUrl("https://www.bag.admin.ch/bag/en/home.html");
		infoBoxti.setIsDismissible(dismissible);
		infoBoxti.setHearingImpairedInfo(hearingImpairedInfo + " TI");

		InfoBoxCollection collection = new InfoBoxCollection();
		collection.setDeInfoBox(infoBoxde);
		collection.setEnInfoBox(infoBoxen);
		collection.setFrInfoBox(infoBoxfr);
		collection.setItInfoBox(infoBoxit);
		collection.setPtInfoBox(infoBoxpt);
		collection.setEsInfoBox(infoBoxes);
		collection.setSqInfoBox(infoBoxsq);
		collection.setHrInfoBox(infoBoxhr);
		collection.setBsInfoBox(infoBoxbs);
		collection.setRmInfoBox(infoBoxrm);
		collection.setSrInfoBox(infoBoxsr);
		collection.setTrInfoBox(infoBoxtr);
		collection.setTiInfoBox(infoBoxti);

		configResponse.setInfoBox(collection);

		return configResponse;
	}
}
