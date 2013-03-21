package model;


import model.elementen.*;

import java.io.*;
import java.util.Arrays;

public class Spel {

    private char Beurt;
    Speler speler1;
    Speler speler2;
    Landschap landschap;
    private int v0, w, y0;
    private double a;
    private boolean isGeraakt;
    private Tank tankA;
    private Tank tankB;
    SchotElement schotElement;
    LuchtElement luchtElement;
    private ReadXMLFile readXMLFile;
    private File landschapFile;
    private File gegevensFile;

    public boolean getIsGeraakt() {
        return isGeraakt;
    }

    public void setGeraakt(boolean geraakt) {
        isGeraakt = geraakt;
    }

    public void setBeurt(char beurt) {
        Beurt = beurt;
    }

    public void reset() {
        speler1.resetGegevens();
        speler2.resetGegevens();
    }

    public Spel() {
        landschapFile = new File("assets/savedFiles/saved.txt");
        gegevensFile = new File("spelInfo.xml");
        newGame();
    }

    private void newGame() {
        speler1 = new Speler();
        speler2 = new Speler();
        landschap = new Landschap();
        schotElement = new SchotElement();
        luchtElement = new LuchtElement();
        isGeraakt = false;
        Beurt = 'A';
        readXMLFile = new ReadXMLFile();
    }

    public int getW() {
        return w;
    }

    public void verWijderbaan() {
        landschap.verwijderBaan();
    }

    public boolean isGeraakt() {
        return isGeraakt;
    }

    public void maakElementenArray() {
        landschap.maakElementenArray(false);
    }

    public Element[][] getLandschap() {
        return landschap.getLandschapArray();
    }

    public void setV0(int v0) {
        this.v0 = v0;

        if (getBeurt() == 'A') {
            speler1.setSnelheidVorig(v0);
        } else {
            speler2.setSnelheidVorig(v0);
        }
    }

    public void setW(int w) {
        this.w = w;
    }

    public void setY0(int y0) {
        this.y0 = y0;
    }

    public void setA(double a) {
        this.a = a;

        if (getBeurt() == 'A') {
            speler1.setHoekVorig(a);
        } else {
            speler2.setHoekVorig(a);
        }
    }

    public void changeBeurt() {
        if (getBeurt() == 'A') {
            Beurt = 'B';
        } else {
            Beurt = 'A';
        }
    }

    public boolean fileExist() {

        return landschapFile.exists() && gegevensFile.exists();
    }

    public void tekenBaan() {
        tankA = new Tank(landschap.getTankAPositieKol(), landschap.getTankAPositieRij());
        tankB = new Tank(landschap.getTankBPositieKol(), landschap.getTankBPositieRij());
        //Aanmaken array waarin we y-waarden in zullen opslaan
        int[] schotResultaat = new int[100];
        Arrays.fill(schotResultaat, 0);
        // voor elk schot oude waarden uit de array halen

        int x = 0;
        int waarde = 0;

        //Array vullen met y-waarden

        //Als a schiet
        if (getBeurt() == 'A') {
            x = tankA.getX();
            this.y0 = tankA.getY();
            for (int i = 1; i < 100; i++) {
                waarde = (int) Schot.ySchot(i, v0, a, w, y0);
                schotResultaat[i] = waarde;
            }
            //Als b schiet
        }
        if (getBeurt() == 'B') {
            x = tankB.getX();
            this.y0 = tankB.getY();
            for (int j = x; j > 0; j--) {
                waarde = (int) Schot.ySchot(j, v0, a, w, y0);
                schotResultaat[j] = waarde;
            }
        }

        if (getBeurt() == 'A') {
            for (int aSchotResultaat : schotResultaat) {
                if (aSchotResultaat > 0) {
                    //Hier gaan we de mogelijkheden af van welk char er zich bevindt in een bepaalde  coördinaat  + winnaar bepalen (indien een tank werd geraakt)
                    // Als het lucht is
                    if (landschap.inhoud(x, aSchotResultaat) == '0') {
                        landschap.veranderElement(x, aSchotResultaat, schotElement);
                    }
                    //Als het grond is verander in lucht + stop schot

                    if (landschap.inhoud(x, aSchotResultaat) == 'B' || landschap.inhoud(x, (aSchotResultaat + 1)) == 'B' || landschap.inhoud((x - 1), (aSchotResultaat)) == 'B' || landschap.inhoud((x - 1), (aSchotResultaat + 1)) == 'B') {
                        isGeraakt = true;
                        break;
                    }


                    if (landschap.inhoud(x, aSchotResultaat) == 'x') {

                        boolean doen = true;

                        if (landschap.inhoud(x - 1, aSchotResultaat) == luchtElement.teken() || landschap.inhoud(x - 1, aSchotResultaat) == schotElement.teken()) {
                            landschap.veranderElement(x, aSchotResultaat, luchtElement);
                            doen = false;
                        }

                        if (doen) {

                            if (landschap.inhoud(x, aSchotResultaat + 1) == 'x') {
                                landschap.veranderElement(x, aSchotResultaat + 1, luchtElement);

                                if(landschap.inhoud(x-1,aSchotResultaat+1) == schotElement.teken() || landschap.inhoud(x-1,aSchotResultaat+1) == schotElement.teken() ){

                                } else{
                                    landschap.veranderElement(x, aSchotResultaat + 2, luchtElement);
                                }


                            } else {
                                landschap.veranderElement(x, aSchotResultaat, luchtElement);
                            }
                        }

                        speler1.verhoogScore();
                        break;

                    }
                    x++;
                }
            }

        }


        if (getBeurt() == 'B') {
            for (int aSchotResultaat : schotResultaat) {
                if (aSchotResultaat > 0) {
                    //Hier gaan we de mogelijkheden af van welk char er zich bevindt in een bepaalde  coördinaat  + winnaar bepalen (indien een tank werd geraakt)
                    // Als het lucht is
                    if (landschap.inhoud(x, aSchotResultaat) == '0') {
                        landschap.veranderElement(x, aSchotResultaat, schotElement);
                    }
                    //Als het grond is verander in lucht + stop schot

                    if (landschap.inhoud(x, aSchotResultaat) == 'A' || landschap.inhoud(x, (aSchotResultaat + 1)) == 'A' || landschap.inhoud((x + 1), (aSchotResultaat)) == 'A' || landschap.inhoud((x + 1), (aSchotResultaat + 1)) == 'A') {
                        isGeraakt = true;
                        break;
                    }

                    if (landschap.inhoud(x, aSchotResultaat) == 'x') {

                        boolean doen = true;

                        if (landschap.inhoud(x + 1, aSchotResultaat) == luchtElement.teken() || landschap.inhoud(x + 1, aSchotResultaat) == schotElement.teken()) {
                            landschap.veranderElement(x, aSchotResultaat, luchtElement);
                            doen = false;
                        }

                        if (doen) {
                            if (landschap.inhoud(x, aSchotResultaat + 1) == 'x') {
                                landschap.veranderElement(x, aSchotResultaat + 1, luchtElement);

                                if(landschap.inhoud(x+1,aSchotResultaat+1) == schotElement.teken() || landschap.inhoud(x+1,aSchotResultaat+1) == schotElement.teken() ){

                                } else{
                                    landschap.veranderElement(x, aSchotResultaat + 2, luchtElement);
                                }

                            } else {
                                landschap.veranderElement(x, aSchotResultaat, luchtElement);

                            }
                        }

                        speler2.verhoogScore();
                        break;
                    }
                    x--;
                }
            }

        }

    }


    public void vulOp(int k, int l) {
        for (int vul = 0; vul < l; vul++) {
            landschap.veranderElement(k, vul, schotElement);
        }
    }


    public String getHuidigeSpeler() {
        String huidigeSpeler;
        if (getBeurt() == 'A') {
            huidigeSpeler = speler1.getNaam();
        } else {
            huidigeSpeler = speler2.getNaam();
        }
        return huidigeSpeler;
    }

    public char getBeurt() {
        return Beurt;
    }

    public Speler getSpeler1() {
        return speler1;
    }

    public Speler getSpeler2() {
        return speler2;
    }

    public void setSpelerNaam(String naam, boolean isA) {
        if (isA) {
            speler1.setNaam(naam);
        } else {
            speler2.setNaam(naam);
        }
    }

    public Speler getSpeler(boolean isA) {
        if (isA) {
            return speler1;
        } else {
            return speler2;
        }
    }

    public Speler getSpeler() {
        if (Beurt == 'A') {
            return speler1;
        } else {
            return speler2;
        }

    }

    public void setExplosie() {
        if (getBeurt() == 'A') {
            landschap.veranderElement(landschap.getTankBPositieKol(), landschap.getTankBPositieRij(), new ExplosieLinksElement());
            landschap.veranderElement(landschap.getTankBPositieKol() + 1, landschap.getTankBPositieRij(), new ExplosieRechtsElement());
        } else {
            landschap.veranderElement(landschap.getTankAPositieKol(), landschap.getTankAPositieRij(), new ExplosieRechtsElement());
            landschap.veranderElement(landschap.getTankAPositieKol() - 1, landschap.getTankAPositieRij(), new ExplosieLinksElement());
        }
    }

    public void oplsaan() {

        WriteXMLFile writeXMLFile = new WriteXMLFile(speler1, speler2, getHuidigeSpeler(), getW());
        writeXMLFile.opslaan();


        PrintStream ps;
        try {
            ps = new PrintStream(new FileOutputStream("assets/savedFiles/saved.txt"));
            for (int row = 42; row > -1; row--) {
                for (int col = 0; col < 100; col++) {
                    char s = getLandschap()[row][col].teken();
                    ps.print(s);
                }
                ps.println();
            }
            ps.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }


    public String getSpelregels() {

        File file = new File("assets/regels/Spelregels.txt");
        StringBuilder contents = new StringBuilder();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;

            while ((text = reader.readLine()) != null) {
                contents.append(text).append(System.getProperty("line.separator"));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return contents.toString();

    }

    public void getSaved() {

        landschap.maakElementenArray(true);

        readXMLFile.hervatVerwerking();
        speler1.setHoekVorig(Double.parseDouble(readXMLFile.getSpeler1Hoek()));
        speler2.setHoekVorig(Double.parseDouble(readXMLFile.getSpeler2Hoek()));
        speler1.setNaam(readXMLFile.getNaamSpeler1());
        speler2.setNaam(readXMLFile.getNaamSpeler2());
        speler1.setSnelheidVorig(Integer.parseInt(readXMLFile.getSpeler1Snelheid()));
        speler2.setSnelheidVorig(Integer.parseInt(readXMLFile.getSpeler2Snelheid()));
        setW(Integer.parseInt(readXMLFile.getWind()));
        if (speler1.getNaam().equals(readXMLFile.getSpelerAanBeurt())) {
            setBeurt('A');
        } else {
            setBeurt('B');
        }
        speler1.setScore(Integer.parseInt(readXMLFile.getSpeler1Score()));
        speler2.setScore(Integer.parseInt(readXMLFile.getSpeler2Score()));
    }

    public void resetBaan() {
        verWijderbaan();
        tekenBaan();
    }
}
