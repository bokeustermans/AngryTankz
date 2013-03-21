package model;


public class Speler {
    private String naam;
    private double hoekVorig;
    private int snelheidVorig;
    private int score;


    public Speler() {
        this.naam = "default";
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public double getHoekVorig() {
        return hoekVorig;
    }

    public void setHoekVorig(double hoekVorig) {
        this.hoekVorig = hoekVorig;
    }

    public int getSnelheidVorig() {
        return snelheidVorig;
    }

    public void setSnelheidVorig(int snelheidVorig) {
        this.snelheidVorig = snelheidVorig;
    }

    public void resetGegevens(){
        hoekVorig =0;
        snelheidVorig = 0;
        score = 0;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void verhoogScore(){
        this.score++;
    }
}

