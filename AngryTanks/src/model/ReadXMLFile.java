package model;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;


public class ReadXMLFile {
    
    private String wind;
    private String naamSpeler1;
    private String naamSpeler2;
    private String spelerAanBeurt;
    private String speler1Hoek;
    private String speler2Hoek;
    private String speler1Snelheid;
    private String speler2Snelheid;
    private String speler1Score;
    private String speler2Score;


    public String getNaamSpeler1() {
        return naamSpeler1;
    }

    public void setNaamSpeler1(String naamSpeler1) {
        this.naamSpeler1 = naamSpeler1;
    }

    public String getNaamSpeler2() {
        return naamSpeler2;
    }

    public void setNaamSpeler2(String naamSpeler2) {
        this.naamSpeler2 = naamSpeler2;
    }

    public String getSpeler1Hoek() {
        return speler1Hoek;
    }

    public void setSpeler1Hoek(String speler1Hoek) {
        this.speler1Hoek = speler1Hoek;
    }

    public String getSpeler1Score() {
        return speler1Score;
    }

    public void setSpeler1Score(String speler1Score) {
        this.speler1Score = speler1Score;
    }

    public String getSpeler1Snelheid() {
        return speler1Snelheid;
    }

    public void setSpeler1Snelheid(String speler1Snelheid) {
        this.speler1Snelheid = speler1Snelheid;
    }

    public String getSpeler2Hoek() {
        return speler2Hoek;
    }

    public void setSpeler2Hoek(String speler2Hoek) {
        this.speler2Hoek = speler2Hoek;
    }

    public String getSpeler2Score() {
        return speler2Score;
    }

    public void setSpeler2Score(String speler2Score) {
        this.speler2Score = speler2Score;
    }

    public String getSpeler2Snelheid() {
        return speler2Snelheid;
    }

    public void setSpeler2Snelheid(String speler2Snelheid) {
        this.speler2Snelheid = speler2Snelheid;
    }

    public String getSpelerAanBeurt() {
        return spelerAanBeurt;
    }

    public void setSpelerAanBeurt(String spelerAanBeurt) {
        this.spelerAanBeurt = spelerAanBeurt;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public void hervatVerwerking() {

            try {
                File fXmlFile = new File("assets/savedFiles/spelInfo.xml");
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(fXmlFile);
                doc.getDocumentElement().normalize();
                NodeList nList = doc.getElementsByTagName("gegevens");

                for (int temp = 0; temp < nList.getLength(); temp++) {

                    Node nNode = nList.item(temp);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                        Element eElement = (Element) nNode;


                        setNaamSpeler1(getTagValue("naamSpeler1",eElement));
                        setNaamSpeler2(getTagValue("naamSpeler2",eElement));
                        setSpelerAanBeurt(getTagValue("spelerAanBeurt",eElement));
                        setSpeler1Hoek(getTagValue("speler1Hoek",eElement));
                        setSpeler2Hoek(getTagValue("speler2Hoek",eElement));
                        setSpeler1Snelheid(getTagValue("speler1snelheid",eElement));
                        setSpeler2Snelheid(getTagValue("speler2snelheid",eElement));
                        setSpeler1Score(getTagValue("speler1Score",eElement));
                        setSpeler2Score(getTagValue("speler2Score",eElement));
                        setWind(getTagValue("wind",eElement));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        public String getTagValue(String sTag, org.w3c.dom.Element eElement) {
            NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();

            Node nValue = (Node) nlList.item(0);

            return nValue.getNodeValue();
        }

}
