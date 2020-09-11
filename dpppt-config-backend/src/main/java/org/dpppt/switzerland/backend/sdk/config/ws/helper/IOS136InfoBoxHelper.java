package org.dpppt.switzerland.backend.sdk.config.ws.helper;

import org.dpppt.switzerland.backend.sdk.config.ws.model.ConfigResponse;
import org.dpppt.switzerland.backend.sdk.config.ws.model.InfoBox;
import org.dpppt.switzerland.backend.sdk.config.ws.model.InfoBoxCollection;

public class IOS136InfoBoxHelper {

	public static void setInfoTextForiOS136(ConfigResponse configResponse) {
		InfoBoxCollection infoBoxCollection = new InfoBoxCollection();
		configResponse.setInfoBox(infoBoxCollection);

		InfoBox infoBoxDe = new InfoBox();
		infoBoxCollection.setDeInfoBox(infoBoxDe);
		infoBoxDe.setMsg(
				"Das Betriebssystem Ihres Mobiltelefons informiert Sie im wöchentlichen Update über die Anzahl identifizierter Begegnungen. Sie können diesen Hinweis ignorieren. Ab iOS 13.7 wird diese Information nicht mehr angezeigt; wir empfehlen Ihnen, Ihr Mobiltelefon zu aktualisieren.\n"
						+ "\n"
						+ "Relevante Meldungen über mögliche Ansteckungen erhalten Sie als Mitteilung von der SwissCovid App. Die Übersicht finden Sie in der App im Abschnitt «Meldungen».");
		infoBoxDe.setTitle("Hinweis");
		infoBoxDe.setUrl(
				"https://www.bag.admin.ch/bag/de/home/krankheiten/ausbrueche-epidemien-pandemien/aktuelle-ausbrueche-epidemien/novel-cov/faq-kontakte-downloads/haeufig-gestellte-fragen.html?faq-url=/de/technik/wieso-erscheint-auf-ios-136-w%C3%B6chtlich-eine-benachrichtigung-dein-ger%C3%A4t-hat-%C2%AB0-m%C3%B6gliche");
		infoBoxDe.setUrlTitle("Weitere Informationen");
		infoBoxDe.setIsDismissible(true);

		InfoBox infoBoxFr = new InfoBox();
		infoBoxCollection.setFrInfoBox(infoBoxFr);
		infoBoxFr.setMsg(
				"Lors de la mise à jour hebdomadaire, le système d'exploitation de votre téléphone vous indique le nombre de contacts identifiés. Vous pouvez ignorer cette information. À partir de la version 13.7 d'iOS, ce renseignement n'apparaît plus ; nous vous recommandons de mettre à jour votre téléphone.\n"
						+ "\n"
						+ "L'application SwissCovid vous signalera les infections par le biais d'une notification. Vous trouverez l'aperçu sous la rubrique \"Notifications\".");
		infoBoxFr.setTitle("Note");
		infoBoxFr.setUrl(
				"https://www.bag.admin.ch/bag/fr/home/krankheiten/ausbrueche-epidemien-pandemien/aktuelle-ausbrueche-epidemien/novel-cov/faq-kontakte-downloads/haeufig-gestellte-fragen.html?faq-url=/fr/pourquoi-l%E2%80%99application-swisscovid-ne-me-signale-t-elle-aucune-infection-potentielle-bien-que");
		infoBoxFr.setUrlTitle("Plus d'informations");
		infoBoxFr.setIsDismissible(true);

		InfoBox infoBoxIt = new InfoBox();
		infoBoxCollection.setItInfoBox(infoBoxIt);
		infoBoxIt.setMsg(
				"Il sistema operativo del tuo telefono cellulare ti informa con un aggiornamento settimanale sul numero di incontri identificati. Puoi ignorare questo avviso. A partire da iOS 13.7 questa informazione non viene più visualizzata; ti consigliamo di aggiornare il tuo telefono cellulare.\n"
						+ "\n"
						+ "Riceverai le segnalazioni rilevanti sui possibili contagi sotto forma di messaggio dell'app SwissCovid. La panoramica si trova nell'app, nella sezione «Segnalazioni».");
		infoBoxIt.setTitle("Nota");
		infoBoxIt.setUrl(
				"https://www.bag.admin.ch/bag/it/home/krankheiten/ausbrueche-epidemien-pandemien/aktuelle-ausbrueche-epidemien/novel-cov/faq-kontakte-downloads/haeufig-gestellte-fragen.html?faq-url=/it/perch%C3%A9-dall%E2%80%99app-swisscovid-non-ricevo-segnalazioni-su-un-possibile-contagio-quando-invece-ios-136-mi");
		infoBoxIt.setUrlTitle("Ulteriori informazioni");
		infoBoxIt.setIsDismissible(true);

		InfoBox infoBoxEn = new InfoBox();
		infoBoxCollection.setEnInfoBox(infoBoxEn);
		infoBoxEn.setMsg(
				"Your mobile phone's operating system will give you a weekly update of the number of identified encounters. You can ignore this message. This information is no longer shown as of  iOS 13.7. We recommend you to update your mobile phone.\n"
						+ "\n"
						+ "You will receive relevant notifications about possible infections in the form of a message from the SwissCovid App. You will find the overview in the app, under \"Reports\".");
		infoBoxEn.setTitle("Note");
		infoBoxEn.setUrl(
				"https://www.bag.admin.ch/bag/en/home/krankheiten/ausbrueche-epidemien-pandemien/aktuelle-ausbrueche-epidemien/novel-cov/faq-kontakte-downloads/haeufig-gestellte-fragen.html?faq-url=/en/swisscovid-app-how-it-works-technology/why-do-i-not-receive-warning-swisscovid-app-about-possible");
		infoBoxEn.setUrlTitle("Further information");
		infoBoxEn.setIsDismissible(true);

		InfoBox infoBoxEs = new InfoBox();
		infoBoxCollection.setEsInfoBox(infoBoxEs);
		infoBoxEs.setMsg(
				"El sistema operativo de su teléfono móvil le informa semanalmente sobre el número de contactos identificados. Puede usted ignorar esta información. A partir de la versión iOS 13.7 esta información ya no se indica; le recomendamos que actualice su teléfono móvil.\n"
						+ "\n"
						+ "Cualquier aviso relevante sobre un posible contagio se transmite mediante una notificación de la aplicación SwissCovid. Consulte la lista en la aplicación bajo la sección «Notificaciones».");
		infoBoxEs.setTitle("Nota");
		infoBoxEs.setUrl(
				"https://www.bag.admin.ch/bag/en/home/krankheiten/ausbrueche-epidemien-pandemien/aktuelle-ausbrueche-epidemien/novel-cov/faq-kontakte-downloads/haeufig-gestellte-fragen.html?faq-url=/en/swisscovid-app-how-it-works-technology/why-do-i-not-receive-warning-swisscovid-app-about-possible");
		infoBoxEs.setUrlTitle("Más informaciones");
		infoBoxEs.setIsDismissible(true);

		InfoBox infoBoxPt = new InfoBox();
		infoBoxCollection.setPtInfoBox(infoBoxPt);
		infoBoxPt.setMsg(
				"O sistema operativo do seu telemóvel informa-o com atualizações semanais sobre o número de contactos que teve com pessoas infetadas. Pode optar por ignorar estes avisos. A partir da versão iOS 13.7, esta informação deixará de ser exibida; aconselhamo-lo a atualizar o telemóvel.\n"
						+ "\n"
						+ "As notificações relevantes sobre possíveis contágios ser-lhe-ão enviadas por mensagem pela app SwissCovid. Poderá consultar o resumo das notificações da app na secção «Notificações».");
		infoBoxPt.setTitle("Nota");
		infoBoxPt.setUrl(
				"https://www.bag.admin.ch/bag/en/home/krankheiten/ausbrueche-epidemien-pandemien/aktuelle-ausbrueche-epidemien/novel-cov/faq-kontakte-downloads/haeufig-gestellte-fragen.html?faq-url=/en/swisscovid-app-how-it-works-technology/why-do-i-not-receive-warning-swisscovid-app-about-possible");
		infoBoxPt.setUrlTitle("Mais informações");
		infoBoxPt.setIsDismissible(true);

		InfoBox infoBoxBs = new InfoBox();
		infoBoxCollection.setBsInfoBox(infoBoxBs);
		infoBoxBs.setMsg(
				"Operativni sistem na mobilnom telefonu vas obaveštava na nedeljnom nivou o broju identifikovanih kontakata. Možete da zanemarite te napomene. Te informacije se više neće prikazivati od verzije iOS 13.7. Preporučujemo vam da ažurirate mobilni telefon.\n"
						+ "\n"
						+ "Relevantne poruke o mogućim infekcijama dobijaćete u vidu obaveštenja iz aplikacije SwissCovid. Pregled možete naći u odeljku „Poruke“ u aplikaciji.");
		infoBoxBs.setTitle("Bilješka");
		infoBoxBs.setUrl(
				"https://www.bag.admin.ch/bag/en/home/krankheiten/ausbrueche-epidemien-pandemien/aktuelle-ausbrueche-epidemien/novel-cov/faq-kontakte-downloads/haeufig-gestellte-fragen.html?faq-url=/en/swisscovid-app-how-it-works-technology/why-do-i-not-receive-warning-swisscovid-app-about-possible");
		infoBoxBs.setUrlTitle("Više informacija");
		infoBoxBs.setIsDismissible(true);

		InfoBox infoBoxSq = new InfoBox();
		infoBoxCollection.setSqInfoBox(infoBoxSq);
		infoBoxSq.setMsg(
				"Në përditësimin javor sistemi operativ i celularit tuaj ju informon për numrin e kontakteve të identifikuara. Mund ta shpërfillni këtë njoftim. Duke nisur nga versioni iOS 13.7 ky informacion nuk shfaqet më; ju rekomandojmë që ta përditësoni celularin tuaj.\n"
						+ "\n"
						+ "Mesazhet përkatëse për infektimet e mundshme i merrni si njoftime nga aplikacioni \"SwissCovid\". Pamjen e përgjithshme mund ta gjeni në seksionin \"Njoftimet\" në aplikacion.");
		infoBoxSq.setTitle("Shënim");
		infoBoxSq.setUrl(
				"https://www.bag.admin.ch/bag/en/home/krankheiten/ausbrueche-epidemien-pandemien/aktuelle-ausbrueche-epidemien/novel-cov/faq-kontakte-downloads/haeufig-gestellte-fragen.html?faq-url=/en/swisscovid-app-how-it-works-technology/why-do-i-not-receive-warning-swisscovid-app-about-possible");
		infoBoxSq.setUrlTitle("Informacione të tjera");
		infoBoxSq.setIsDismissible(true);

		InfoBox infoBoxRm = new InfoBox();
		infoBoxCollection.setRmInfoBox(infoBoxRm);
		infoBoxRm.setMsg(
				"Il sistem operativ da Voss telefonin As infurmescha cun l'update emnil davart il dumber da scuntradas identifitgadas. Vus pudais ignorar questa infurmaziun. A partir dad iOS 13.7 na vegn questa infurmaziun betg pli inditgada. Nus As recumandain, d'actualisar Voss telefonin.\n"
						+ "\n"
						+"Annunzias relevantas davart infecziuns pussaivlas survegnis Vus sco communicaziun da l'app SwissCovid. La survista chattais Vus en l'app en la part «Avis».");
		infoBoxRm.setTitle("Indicaziun");
		infoBoxRm.setUrl(
				"https://www.bag.admin.ch/bag/en/home/krankheiten/ausbrueche-epidemien-pandemien/aktuelle-ausbrueche-epidemien/novel-cov/faq-kontakte-downloads/haeufig-gestellte-fragen.html?faq-url=/en/swisscovid-app-how-it-works-technology/why-do-i-not-receive-warning-swisscovid-app-about-possible");
		infoBoxRm.setUrlTitle("Ulteriuras infurmaziuns");
		infoBoxRm.setIsDismissible(true);

		InfoBox infoBoxTr = new InfoBox();
		infoBoxCollection.setTrInfobox(infoBoxTr);
		infoBoxTr.setMsg(
				"Cep telefonunuzun işletim sistemi, tespit edilen temaslı kişilerin sayısı hakkında haftalık güncelleme ile sizi bilgilendirir. Dilerseniz bu bildirimleri engelleyebilirsiniz. iOS 13.7 sürümünden itibaren bu bilgi artık görüntülenmemektedir; telefonunuzu güncellemenizi tavsiye ediyoruz.\n"
						+ "\n"
						+ "Olası bulaşma riski ile ilgili bildirimleri SwissCovid uygulamasından mesaj olarak alacaksınız. Özetini, uygulama içerisindeki «Bildirimler» bölümünde bulabilirsiniz.");
		infoBoxTr.setTitle("Bilgi");
		infoBoxTr.setUrl(
				"https://www.bag.admin.ch/bag/en/home/krankheiten/ausbrueche-epidemien-pandemien/aktuelle-ausbrueche-epidemien/novel-cov/faq-kontakte-downloads/haeufig-gestellte-fragen.html?faq-url=/en/swisscovid-app-how-it-works-technology/why-do-i-not-receive-warning-swisscovid-app-about-possible");
		infoBoxTr.setUrlTitle("Daha fazla bilgi");
		infoBoxTr.setIsDismissible(true);

		InfoBox infoBoxTi = new InfoBox();
		infoBoxCollection.setTiInfobox(infoBoxTi);
		infoBoxTi.setMsg(
				"እቲ ስርዓተ መስርሕ ኣብ ሞባይልኩም ኩሉ ሰሙን ምስቲ ዝመጽእ ኣፕደይት ብዛዕባ መጠን ዝተረጋገጸ ምንቅስቓስ ይሕብረኩም። እዚ ምልክት ከተስተውዕሉ ኣየድልየኩምን። ካብ ቨርዝዮን iOS 13.7 ንየው እዚ ሓበሬታ ኣይክረአን እዩ፤ ሞባይላትኩም ከተሕድስዎም ንመኽረኩም ኢና።\n"
						+ "\n"
						+ "ካብ SwissCovid ኤፕ ኣገደስቲ ሓበሬታታት ብዛዕባ ዝኾኑ ልበዳታት ክትወሃቡ ኢኹም። ሓፈሻዊ ሓበሬታ ኣብቲ ኤፕ ኣብ «ሓበሬታ» ዝብሃል ክፋል ክትረኽቡ ኢኹም።");
		infoBoxTi.setTitle("ምልክታ");
		infoBoxTi.setUrl(
				"https://www.bag.admin.ch/bag/en/home/krankheiten/ausbrueche-epidemien-pandemien/aktuelle-ausbrueche-epidemien/novel-cov/faq-kontakte-downloads/haeufig-gestellte-fragen.html?faq-url=/en/swisscovid-app-how-it-works-technology/why-do-i-not-receive-warning-swisscovid-app-about-possible");
		infoBoxTi.setUrlTitle("ተወሰኽቲ ሓበሬታታት");
		infoBoxTi.setIsDismissible(true);

	}

}
