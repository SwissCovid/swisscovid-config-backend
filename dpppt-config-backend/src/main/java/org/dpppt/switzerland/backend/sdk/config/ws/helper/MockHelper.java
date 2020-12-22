package org.dpppt.switzerland.backend.sdk.config.ws.helper;

import org.dpppt.switzerland.backend.sdk.config.ws.model.ConfigResponse;
import org.dpppt.switzerland.backend.sdk.config.ws.model.InfoBox;
import org.dpppt.switzerland.backend.sdk.config.ws.model.InfoBoxCollection;
import org.dpppt.switzerland.backend.sdk.config.ws.model.SDKConfig;

public class MockHelper {

	public static ConfigResponse mockConfigResponseWithInfoBox(boolean dismissible) {
		ConfigResponse configResponse = new ConfigResponse();

		InfoBox infoBoxde = new InfoBox();
		infoBoxde.setMsg("Hier steht ein Text. Das kann ein Hinweis sein. Je länger umso mehr Platz DE");
		infoBoxde.setTitle("Hinweis DE");
		infoBoxde.setUrlTitle("Und ein externer Link DE");
		infoBoxde.setUrl("https://www.bag.admin.ch/bag/de/home.html");
		infoBoxde.setIsDismissible(dismissible);

		InfoBox infoBoxfr = new InfoBox();
		infoBoxfr.setMsg("Hier steht ein Text. Das kann ein Hinweis sein. Je länger umso mehr Platz FR");
		infoBoxfr.setTitle("Hinweis FR");
		infoBoxfr.setUrlTitle("Und ein externer Link FR");
		infoBoxfr.setUrl("https://www.bag.admin.ch/bag/fr/home.html");
		infoBoxfr.setIsDismissible(dismissible);

		InfoBox infoBoxit = new InfoBox();
		infoBoxit.setMsg("Hier steht ein Text. Das kann ein Hinweis sein. Je länger umso mehr Platz IT");
		infoBoxit.setTitle("Hinweis IT");
		infoBoxit.setUrlTitle("Und ein externer Link IT");
		infoBoxit.setUrl("https://www.bag.admin.ch/bag/it/home.html");
		infoBoxit.setIsDismissible(dismissible);

		InfoBox infoBoxen = new InfoBox();
		infoBoxen.setMsg("Hier steht ein Text. Das kann ein Hinweis sein. Je länger umso mehr Platz EN");
		infoBoxen.setTitle("Hinweis EN");
		infoBoxen.setUrlTitle("Und ein externer Link EN");
		infoBoxen.setUrl("https://www.bag.admin.ch/bag/en/home.html");
		infoBoxen.setIsDismissible(dismissible);

		InfoBox infoBoxpt = new InfoBox();
		infoBoxpt.setMsg("Hier steht ein Text. Das kann ein Hinweis sein. Je länger umso mehr Platz PT");
		infoBoxpt.setTitle("Hinweis PT");
		infoBoxpt.setUrlTitle("Und ein externer Link PT");
		infoBoxpt.setUrl("https://www.bag.admin.ch/bag/pt/home.html");
		infoBoxpt.setIsDismissible(dismissible);

		InfoBox infoBoxes = new InfoBox();
		infoBoxes.setMsg("Hier steht ein Text. Das kann ein Hinweis sein. Je länger umso mehr Platz ES");
		infoBoxes.setTitle("Hinweis ES");
		infoBoxes.setUrlTitle("Und ein externer Link ES");
		infoBoxes.setUrl("https://www.bag.admin.ch/bag/en/home.html");
		infoBoxes.setIsDismissible(dismissible);

		InfoBox infoBoxsq = new InfoBox();
		infoBoxsq.setMsg("Hier steht ein Text. Das kann ein Hinweis sein. Je länger umso mehr Platz SQ");
		infoBoxsq.setTitle("Hinweis SQ");
		infoBoxsq.setUrlTitle("Und ein externer Link SQ");
		infoBoxsq.setUrl("https://www.bag.admin.ch/bag/en/home.html");
		infoBoxsq.setIsDismissible(dismissible);

		InfoBox infoBoxbs = new InfoBox();
		infoBoxbs.setMsg("Hier steht ein Text. Das kann ein Hinweis sein. Je länger umso mehr Platz BS");
		infoBoxbs.setTitle("Hinweis BS");
		infoBoxbs.setUrlTitle("Und ein externer Link BS");
		infoBoxbs.setUrl("https://www.bag.admin.ch/bag/en/home.html");
		infoBoxbs.setIsDismissible(dismissible);

		InfoBox infoBoxhr = new InfoBox();
		infoBoxhr.setMsg("Hier steht ein Text. Das kann ein Hinweis sein. Je länger umso mehr Platz HR");
		infoBoxhr.setTitle("Hinweis HR");
		infoBoxhr.setUrlTitle("Und ein externer Link HR");
		infoBoxhr.setUrl("https://www.bag.admin.ch/bag/en/home.html");
		infoBoxhr.setIsDismissible(dismissible);

		InfoBox infoBoxrm = new InfoBox();
		infoBoxrm.setMsg("Hier steht ein Text. Das kann ein Hinweis sein. Je länger umso mehr Platz RM");
		infoBoxrm.setTitle("Hinweis RM");
		infoBoxrm.setUrlTitle("Und ein externer Link RM");
		infoBoxrm.setUrl("https://www.bag.admin.ch/bag/en/home.html");
		infoBoxrm.setIsDismissible(dismissible);

		InfoBox infoBoxsr = new InfoBox();
		infoBoxsr.setMsg("Hier steht ein Text. Das kann ein Hinweis sein. Je länger umso mehr Platz SR");
		infoBoxsr.setTitle("Hinweis SR");
		infoBoxsr.setUrlTitle("Und ein externer Link SR");
		infoBoxsr.setUrl("https://www.bag.admin.ch/bag/en/home.html");
		infoBoxsr.setIsDismissible(dismissible);

		InfoBox Infoboxtr = new InfoBox();
		Infoboxtr.setMsg("Hier steht ein Text. Das kann ein Hinweis sein. Je länger umso mehr Platz TR");
		Infoboxtr.setTitle("Hinweis TR");
		Infoboxtr.setUrlTitle("Und ein externer Link TR");
		Infoboxtr.setUrl("https://www.bag.admin.ch/bag/en/home.html");
		Infoboxtr.setIsDismissible(dismissible);

		InfoBox infoBoxti = new InfoBox();
		infoBoxti.setMsg("Hier steht ein Text. Das kann ein Hinweis sein. Je länger umso mehr Platz TI");
		infoBoxti.setTitle("Hinweis TI");
		infoBoxti.setUrlTitle("Und ein externer Link TI");
		infoBoxti.setUrl("https://www.bag.admin.ch/bag/en/home.html");
		infoBoxti.setIsDismissible(dismissible);

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
		collection.setTrInfobox(Infoboxtr);
		collection.setTiInfobox(infoBoxti);

		configResponse.setInfoBox(collection);

		return configResponse;
	}
}
