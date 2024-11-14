package PaisesXML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Class FitxategiOperazioak.
 */
public class FitxategiOperazioak {
	/** Kodea. */
	static String[] Kodea = { "31", "376", "90", "261", "685", "213", "291", "595", "30", "964" };

	/** Estatua. */
	static String[] Estatua = { "Holanda", "Andorra", "Turkia", "Madagascar", "Samoa Occidental", "Argelia", "Eritrea",
			"Paraguay", "Grecia", "Irak" };

	/** Bizi esperantza. */
	static int[] BiziEsperantza = { 78, 0, 67, 52, 68, 70, 0, 68, 78, 66 };

	/** Data sortu. */
	static LocalDate[] DataSortu = { LocalDate.of(1581, 7, 26), LocalDate.of(1993, 3, 14), LocalDate.of(1923, 10, 29),
			LocalDate.of(1960, 6, 26), LocalDate.of(1962, 1, 1), LocalDate.of(1962, 7, 5), LocalDate.of(1993, 5, 24),
			LocalDate.of(1825, 8, 25), LocalDate.of(1830, 2, 3), LocalDate.of(1958, 7, 14) };

	/** Poblazioa. */
	static double[] Poblazioa = { 15460000, 64000, 61058000, 13651000, 165000, 27959000, 3400000, 4828000, 10467000,
			20097000 };

	/** Kapitala. */
	static String[] Kapitala = { "Amsterdam", "Andorra La Vieja", "Ankara", "Antananarivo", "Apia", "Argel", "Asmara",
			"Asuncion", "Atenas", "Bagdad" };

	/** Gure dataren Formatua. */
	private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	/**
	 * Fitxategia bete.
	 *
	 * @param fitxategia , gure xml fitxategia
	 */
	public static void fitxategiaBete(File fitxategia) {
		try {

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();

			Element rootElement = doc.createElement("Paises");
			doc.appendChild(rootElement);

			for (int i = 0; i < Kodea.length; i++) {
				Element paisElement = doc.createElement("Pais");

				Element kodeaElement = doc.createElement("Kodea");
				kodeaElement.appendChild(doc.createTextNode(Kodea[i]));
				paisElement.appendChild(kodeaElement);

				Element estatuaElement = doc.createElement("Estatua");
				estatuaElement.appendChild(doc.createTextNode(Estatua[i]));
				paisElement.appendChild(estatuaElement);

				Element biziEsperantzaElement = doc.createElement("BiziEsperantza");
				biziEsperantzaElement.appendChild(doc.createTextNode(String.valueOf(BiziEsperantza[i])));
				paisElement.appendChild(biziEsperantzaElement);

				Element dataSortuElement = doc.createElement("DataSortu");
				dataSortuElement.appendChild(doc.createTextNode(DataSortu[i].format(dateFormatter)));
				paisElement.appendChild(dataSortuElement);

				Element poblazioaElement = doc.createElement("Poblazioa");
				poblazioaElement.appendChild(doc.createTextNode(String.valueOf(Poblazioa[i])));
				paisElement.appendChild(poblazioaElement);

				Element kapitalaElement = doc.createElement("Kapitala");
				kapitalaElement.appendChild(doc.createTextNode(Kapitala[i]));
				paisElement.appendChild(kapitalaElement);

				rootElement.appendChild(paisElement);
			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(fitxategia);

			transformer.transform(source, result);

			System.out.println("Fitxategia XML formatuan bete da.");
		} catch (Exception e) {
			System.out.println("Errorea XML fitxategia sortzean: " + e.getMessage());
		}
	}

	/**
	 * Irakurri eta erakutsi XML.
	 *
	 * @param fitxategia gure xml fitxategia
	 */
	public static void irakurriEtaErakutsiXML(File fitxategia) {
		try {

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fitxategia);
			doc.getDocumentElement().normalize();

			System.out.println("Erroa: " + doc.getDocumentElement().getNodeName());

			NodeList paises = doc.getElementsByTagName("Pais");

			for (int i = 0; i < paises.getLength(); i++) {
				Node paisNode = paises.item(i);

				if (paisNode.getNodeType() == Node.ELEMENT_NODE) {
					Element paisElement = (Element) paisNode;

					String kodea = paisElement.getElementsByTagName("Kodea").item(0).getTextContent();
					String estatua = paisElement.getElementsByTagName("Estatua").item(0).getTextContent();
					String biziEsperantza = paisElement.getElementsByTagName("BiziEsperantza").item(0).getTextContent();
					String dataSortu = paisElement.getElementsByTagName("DataSortu").item(0).getTextContent();
					String poblazioa = paisElement.getElementsByTagName("Poblazioa").item(0).getTextContent();
					String kapitala = paisElement.getElementsByTagName("Kapitala").item(0).getTextContent();

					System.out.println("Pais:");
					System.out.println("  Kodea: " + kodea);
					System.out.println("  Estatua: " + estatua);
					System.out.println("  Bizi Esperantza: " + biziEsperantza);
					System.out.println("  Data Sortu: " + dataSortu);
					System.out.println("  Poblazioa: " + poblazioa);
					System.out.println("  Kapitala: " + kapitala);
					System.out.println("---------------------------");
				}
			}
		} catch (Exception e) {
			System.out.println("Errorea XML fitxategia irakurtzean: " + e.getMessage());
		}

	}

	/**
	 * Bilatu kodearen arabera.
	 *
	 * @param fitxategia  gure xml fitxategia
	 * @param kodeaBilatu , bilatu nahi dugun hiriaren kodea
	 */
	public static void bilatuKodearenArabera(File fitxategia, String kodeaBilatu) {
		try {

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fitxategia);
			doc.getDocumentElement().normalize();

			NodeList paises = doc.getElementsByTagName("Pais");
			boolean aurkituta = false;

			for (int i = 0; i < paises.getLength(); i++) {
				Node paisNode = paises.item(i);

				if (paisNode.getNodeType() == Node.ELEMENT_NODE) {
					Element paisElement = (Element) paisNode;

					String kodea = paisElement.getElementsByTagName("Kodea").item(0).getTextContent();

					if (kodea.equals(kodeaBilatu)) {
						aurkituta = true;

						System.out.println("Aurkitu da Hiria:");
						System.out.println("  Kodea: " + kodea);
						System.out.println(
								"  Estatua: " + paisElement.getElementsByTagName("Estatua").item(0).getTextContent());
						System.out.println("  Bizi Esperantza: "
								+ paisElement.getElementsByTagName("BiziEsperantza").item(0).getTextContent());
						System.out.println("  Data Sortu: "
								+ paisElement.getElementsByTagName("DataSortu").item(0).getTextContent());
						System.out.println("  Poblazioa: "
								+ paisElement.getElementsByTagName("Poblazioa").item(0).getTextContent());
						System.out.println(
								"  Kapitala: " + paisElement.getElementsByTagName("Kapitala").item(0).getTextContent());
						System.out.println("---------------------------");
						break;
					}
				}
			}

			if (!aurkituta) {
				System.out.println("Ez da aurkitu kodearekin lotutako herrialderik: " + kodeaBilatu);
			}
		} catch (Exception e) {
			System.out.println("Errorea XML fitxategia irakurtzean: " + e.getMessage());
		}
	}

	/**
	 * Bilatu hitzarekin.
	 *
	 * @param fitxategia  gure xml fitxategia
	 * @param hitzaBilatu , bilatu nahi dugun hiriaren hitz gakoren bat
	 */
	public static void bilatuHitzarekin(File fitxategia, String hitzaBilatu) {
		try {

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fitxategia);
			doc.getDocumentElement().normalize();

			NodeList paisList = doc.getElementsByTagName("Pais");
			int matchCount = 0;

			System.out.println("Bilaketa hitzarekin \"" + hitzaBilatu + "\":");

			for (int i = 0; i < paisList.getLength(); i++) {
				Node paisNode = paisList.item(i);

				if (paisNode.getNodeType() == Node.ELEMENT_NODE) {
					Element paisElement = (Element) paisNode;

					StringBuilder paisData = new StringBuilder();
					paisData.append(paisElement.getElementsByTagName("Kodea").item(0).getTextContent()).append(" ");
					paisData.append(paisElement.getElementsByTagName("Estatua").item(0).getTextContent()).append(" ");
					paisData.append(paisElement.getElementsByTagName("Kapitala").item(0).getTextContent()).append(" ");

					if (paisData.toString().toLowerCase().contains(hitzaBilatu.toLowerCase())) {
						matchCount++;
						System.out.println("\n[" + matchCount + "] Erregistroa aurkituta:");
						inprimatuErregistroa(paisElement);
					}
				}
			}

			if (matchCount == 0) {
				System.out.println("Ez da aurkitu erregistro bat ere hitzarekin \"" + hitzaBilatu + "\".");
			}

		} catch (Exception e) {
			System.out.println("Errorea bilaketa egitean: " + e.getMessage());
		}
	}

	/**
	 * Inprimatu erregistroa.
	 *
	 * @param paisElement Zein Hiri
	 */
	private static void inprimatuErregistroa(Element paisElement) {
		System.out.println("Kodea: " + paisElement.getElementsByTagName("Kodea").item(0).getTextContent());
		System.out.println("Estatua: " + paisElement.getElementsByTagName("Estatua").item(0).getTextContent());
		System.out.println(
				"Bizi Esperantza: " + paisElement.getElementsByTagName("BiziEsperantza").item(0).getTextContent());
		System.out.println("Data Sortu: " + paisElement.getElementsByTagName("DataSortu").item(0).getTextContent());
		System.out.println("Poblazioa: " + paisElement.getElementsByTagName("Poblazioa").item(0).getTextContent());
		System.out.println("Kapitala: " + paisElement.getElementsByTagName("Kapitala").item(0).getTextContent());
	}

	/**
	 * Ezabatu kodearekin.
	 *
	 * @param fitxategia   , gure fitxategia
	 * @param kodeaEzabatu Ezabatzeko nahi dugun Hiriaren Kodea
	 */
	public static void ezabatuKodearekin(File fitxategia, String kodeaEzabatu) {
		try {

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fitxategia);
			doc.getDocumentElement().normalize();

			NodeList paisList = doc.getElementsByTagName("Pais");
			boolean nodoEliminado = false;

			for (int i = 0; i < paisList.getLength(); i++) {
				Node paisNode = paisList.item(i);

				if (paisNode.getNodeType() == Node.ELEMENT_NODE) {
					Element paisElement = (Element) paisNode;

					String kodea = paisElement.getElementsByTagName("Kodea").item(0).getTextContent();
					if (kodea.equals(kodeaEzabatu)) {
						paisNode.getParentNode().removeChild(paisNode);
						nodoEliminado = true;
						System.out.println("Erregistroa kodearekin \"" + kodeaEzabatu + "\" ezabatu da.");
						break;
					}
				}
			}

			if (!nodoEliminado) {
				System.out.println("Ez da aurkitu kodearekin \"" + kodeaEzabatu + "\" duen erregistroa.");
			} else {

				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(fitxategia);
				transformer.transform(source, result);
			}

		} catch (Exception e) {
			System.out.println("Errorea nodoa ezabatzean: " + e.getMessage());
		}
	}

}
