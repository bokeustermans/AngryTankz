package model;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class WriteXMLFile {

    private Speler speler1;
    private Speler speler2;
    private String huidigeSpeler;
    private int wind;


    public WriteXMLFile(Speler speler1, Speler speler2, String huidigeSpeler, int wind) {
        this.speler1 = speler1;
        this.speler2 = speler2;
        this.huidigeSpeler = huidigeSpeler;
        this.wind = wind;
    }

    public int getWind() {
        return wind;
    }

    public void opslaan() {

        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("saved");
            doc.appendChild(rootElement);

            // staff elements
            Element gegevens = doc.createElement("gegevens");
            rootElement.appendChild(gegevens);

            // speler 1 naam
            Element naamSpeler1 = doc.createElement("naamSpeler1");
            naamSpeler1.appendChild(doc.createTextNode(this.speler1.getNaam()));
            gegevens.appendChild(naamSpeler1);

            // speler 2 naam
            Element naamSpeler2 = doc.createElement("naamSpeler2");
            naamSpeler2.appendChild(doc.createTextNode(this.speler2.getNaam()));
            gegevens.appendChild(naamSpeler2);

            // speler aan beurt
            Element spelerAanBeurt = doc.createElement("spelerAanBeurt");
            spelerAanBeurt.appendChild(doc.createTextNode(huidigeSpeler));
            gegevens.appendChild(spelerAanBeurt);

            // Speler 1 hoek
            Element speler1hoek = doc.createElement("speler1Hoek");
            speler1hoek.appendChild(doc.createTextNode(String.valueOf(this.speler1.getHoekVorig())));
            gegevens.appendChild(speler1hoek);

            // Speler 1 snelhied
            Element speler1snelheid = doc.createElement("speler1snelheid");
            speler1snelheid.appendChild(doc.createTextNode(String.valueOf(this.speler1.getSnelheidVorig())));
            gegevens.appendChild(speler1snelheid);

            // Speler 1 score
            Element speler1Score = doc.createElement("speler1Score");
            speler1Score.appendChild(doc.createTextNode(String.valueOf(this.speler1.getScore())));
            gegevens.appendChild(speler1Score);

            // Speler 2 hoek
            Element speler2hoek = doc.createElement("speler2Hoek");
            speler2hoek.appendChild(doc.createTextNode(String.valueOf(this.speler2.getHoekVorig())));
            gegevens.appendChild(speler2hoek);

            // Speler 2 snelhied
            Element speler2snelheid = doc.createElement("speler2snelheid");
            speler2snelheid.appendChild(doc.createTextNode(String.valueOf(this.speler2.getSnelheidVorig())));
            gegevens.appendChild(speler2snelheid);

            // Speler 2 score
            Element speler2Score = doc.createElement("speler2Score");
            speler2Score.appendChild(doc.createTextNode(String.valueOf(this.speler2.getScore())));
            gegevens.appendChild(speler2Score);

            //wind
            Element wind = doc.createElement("wind");
            wind.appendChild(doc.createTextNode(String.valueOf(getWind())));
            gegevens.appendChild(wind);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("assets/savedFiles/spelInfo.xml"));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);


        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

}