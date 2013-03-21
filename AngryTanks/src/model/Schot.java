package model;

public class Schot {


    private static final double G = 9.81; // Gravitatieconstante

    //Formule die we kregen uit de pdf (opdracht) gebruikmakend van Java functies (Math.*)
    public static double ySchot(int x, int v0, double a, int w, int y0) {
        //forumle is in delen makkelijker te verstaan
        double hoek = Math.toRadians(a);
        double onderstedeel1 = ((v0 * Math.cos(hoek)) - (w/50));
        double deel1 = ((-G / 2) / Math.pow(onderstedeel1, 2)) * (Math.pow(x, 2));
        double deel2 = ((x * Math.sin(hoek)) / (Math.cos(hoek) - (w / v0))) + y0;
        return deel1 + deel2;
    }
}









