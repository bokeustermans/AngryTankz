package view;

import controller.*;
import model.*;
import model.elementen.Element;

import java.awt.event.ActionListener;
import java.util.*;

public class SpelView extends ConsoleView {

    private Spel spel;
    private Scanner scanner;


    public SpelView(Spel spel) {
        this.spel = spel;
        scanner = new Scanner(System.in);
    }



    public void infoGedeelte() {
        Speler s1 = spel.getSpeler(true);
        Speler s2 = spel.getSpeler(false);
        System.out.println("=====================================================================================================");
        System.out.printf("Wind: %d | Beurt: %s (%c)", spel.getW(), spel.getHuidigeSpeler(), spel.getBeurt());
        System.out.println();
        System.out.println("=====================================================================================================");
        System.out.printf("Speler: %s | Hoek: %.0f | Snelheid: %d  | Score: %d", s1.getNaam(), s1.getHoekVorig(), s1.getSnelheidVorig(), s1.getScore());
        System.out.println();
        System.out.printf("Speler: %s | Hoek: %.0f | Snelheid: %d | Score: %d", s2.getNaam(), s2.getHoekVorig(), s2.getSnelheidVorig(), s2.getScore());
        System.out.println();
        System.out.println("=====================================================================================================");
    }

    public String naamIngaveSpeler1() {
        System.out.println();
        System.out.println("Speler 1, geef je naam in (tank A)");
        return (scanner.next());
    }

    public String naamIngaveSpeler2() {
        System.out.println("Speler 2, geef je naam in (tank B)");
        return (scanner.next());
    }

    public String hoekIngeven() {
        boolean herhaling = true;
        double hoek = 0;
        do {
            try {
                System.out.println();
                System.out.println(spel.getHuidigeSpeler() + ", geef een hoek in");
                hoek = scanner.nextDouble();
                herhaling = false;
            } catch (InputMismatchException fout) {
                scanner.nextLine();
                System.out.println("Geef een geldige hoek op.");
            }
        } while (herhaling);
        return "" + hoek;
    }

    public String snelheidIngeven() {
        boolean herhaling = true;
        int snelheid = 0;
        do {
            try {
                System.out.println();
                System.out.println(spel.getHuidigeSpeler() + ", geef een snelheid in");
                snelheid = scanner.nextInt();
                herhaling = false;
            } catch (InputMismatchException fout) {
                scanner.nextLine();
                System.out.println("Geef een geldige snelheid op.");
            }
        } while (herhaling);
        return "" + snelheid;
    }

    public void laatLandschapZien(Element[][] landschapArray) {
        for (int i = (43) - 1; i >= 0; i--) {
            for (int j = 0; j < 100; j++) {
                if (landschapArray[i][j].teken() == '0') {
                    System.out.print(" ");
                } else {
                    System.out.print(landschapArray[i][j].teken());
                }
            }
            System.out.println();
        }
    }

    public void winnaarTonen() {
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.printf("Gefeliciteerd %s, jij wint!", spel.getHuidigeSpeler());
        System.out.println();
        System.out.println("----------------------------------------------------------------------------------------------------");
    }


}