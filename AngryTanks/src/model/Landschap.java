package model;

import model.elementen.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Landschap {

    private Element[][] landschapArray;
    private String landschapPath;  // geeft aan waar je landschap file (.txt) staat
    private static final int ROWS = 43;               //Constanten
    private static final int COLS = 100;
    TankAElement tankAElement = new TankAElement();
    TankBElement tankBElement = new TankBElement();


    public Element[][] getLandschapArray() {
        return landschapArray;
    }

    public String bepaalLandschap() {
        int aantal = new File("assets/landschappen").listFiles().length;
        Random random = new Random();
        int randomLandschap = 1 + random.nextInt(aantal);
        return "assets/landschappen/" + (randomLandschap) + ".txt"; //RANDOMLANDSCHAP TUSSEN ()!!!!
    }

    public void maakElementenArray(Boolean savedFile) {
        landschapArray = new Element[ROWS][COLS];
        if (savedFile) {
            landschapPath = "assets/savedFiles/saved.txt";
        } else {
            landschapPath = bepaalLandschap();
        }
        fillLandschap();
    }

    private void fillLandschap() {
        BufferedReader leesLandschap;
        try {
            leesLandschap = new BufferedReader(new FileReader(landschapPath)); //Het landschap bestand uitlezen
            String lijn = leesLandschap.readLine();

            while (lijn != null) {
                for (int i = (ROWS) - 1; i >= 0; i--) {
                    for (int j = 0; j < COLS; j++) {
                        determineElement(i, j, lijn.charAt(j));
                    }
                    lijn = leesLandschap.readLine();
                }
            }
            leesLandschap.close();

        } catch (IOException opvangScore) {
            System.exit(0);
        }
    }

    private void determineElement(int row, int col, char element) {
        switch (element) {
            case '0':
                landschapArray[row][col] = new LuchtElement();
                break;
            case 'x':
                landschapArray[row][col] = new LandschapElement();
                break;
            case 'A':
                landschapArray[row][col] = tankAElement;
                tankAElement.setX(row);
                tankAElement.setY(col);
                break;
            case '-':
                landschapArray[row][col] = new TankBMinElement();
                break;
            case '+':
                landschapArray[row][col] = new TankAPlusElement();
                break;
            case 'B':
                landschapArray[row][col] = tankBElement;
                tankBElement.setX(row);
                tankBElement.setY(col);
                break;
            case '°':
                landschapArray[row][col] = new SchotElement();
                break;

        }
    }


    public char inhoud(int x, int y) {
        if (x < 100 && x >= 0 && y >= 0 && y < 43) {
            return landschapArray[y][x].teken();
        }
        return 0;
    }

    public void veranderElement(int x, int y, Element element) {
        landschapArray[y][x] = element;
    }

    public int getTankAPositieRij() {
        return tankAElement.getX();
    }

    public int getTankAPositieKol() {
        return tankAElement.getY();
    }

    public int getTankBPositieRij() {
        return tankBElement.getX();
    }

    public int getTankBPositieKol() {
        return tankBElement.getY();
    }

    public void verwijderBaan() {
        for (int rij = 0; rij < ROWS; rij++) {
            for (int kol = 0; kol < COLS; kol++) {
                if (landschapArray[rij][kol].teken() == '°') { // Als een character van de array A (tank 1) is...

                    landschapArray[rij][kol] = new LuchtElement();
                }
            }
        }
    }
}
