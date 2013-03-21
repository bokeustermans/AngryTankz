package controller;

import model.RandomGenerator;
import model.Spel;
import view.SpelGUI;
import view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpelController {
    private View view;
    private Spel spel;
    private boolean draaienTegenhouden;

    public SpelController(View view, Spel spel) {
        this.view = view;
        this.spel = spel;
        draaienTegenhouden = false;
    }

    private void setListeners() {
        view.setStartListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                draaienTegenhouden = false;
                spel.setGeraakt(false);
                spel.reset();
                view.voegPanelsToe();
                view.activeerSchotInput(true);

                try {
                    String naamSpeler1 = view.naamIngaveSpeler1();
                    String naamSpeler2 = view.naamIngaveSpeler2();

                    if (!canelNaamIngave(naamSpeler1) || !canelNaamIngave(naamSpeler2)) {
                        throw new IllegalArgumentException("Een nieuw spel is succesvol geanuleerd");
                    } else {
                        if (!validNaam(naamSpeler1) || !validNaam(naamSpeler2)) {
                            throw new IllegalArgumentException("Geef een geldige naam in! (minimaal 1 character)");
                        }
                        spel.setSpelerNaam(naamSpeler1, true);
                        spel.setSpelerNaam(naamSpeler2, false);
                        spel.maakElementenArray(); //de Elementen array aamaken
                        //geef info over verder verloop
                        spel.setW(RandomGenerator.getRandomNumber());
                        view.infoGedeelte();
                        view.laatLandschapZien(spel.getLandschap());
                        view.opslaanStatus(true);
                    }
                } catch (IllegalArgumentException j) {
                    view.informatieBoodschap(j.getMessage());
                }

            }

            private boolean validNaam(String naam) {
                return !naam.isEmpty();
            }

            private boolean canelNaamIngave(String naam) {
                return naam != null;
            }


        });
        view.setHelpListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                view.showSpelRegels(spel.getSpelregels());
            }
        });
        view.setSchotListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


                view.activeerSchotInput(false);
                try {
                    String inputHoek = view.hoekIngeven();
                    String inputSnelheid = view.snelheidIngeven();


                    if (!validHoek(inputHoek) && !validSnelheid(inputSnelheid)) {
                        throw new IllegalArgumentException("Geef een geldige hoek en snelheid in!");
                    } else {

                        if (!validHoek(inputHoek)) {
                            throw new IllegalArgumentException("Geef een geldige hoek in! (Maximum 60 graden)");
                        } else {
                            Double a = Double.parseDouble(view.hoekIngeven());
                            spel.setA(a);
                        }
                        if (!validSnelheid(inputSnelheid)) {
                            throw new IllegalArgumentException("Geef een geldige snelheid in!");
                        } else {
                            Integer v0 = Integer.parseInt(view.snelheidIngeven());
                            spel.setV0(v0);
                        }
                        spel.resetBaan();
                        view.laatLandschapZien(spel.getLandschap());
                        if (spel.getIsGeraakt()) {
                            draaienTegenhouden = true;
                            spel.setExplosie();
                            view.activeerSchotInput(false);
                            view.opslaanStatus(false);
                            if (spel.getBeurt() == 'A') {
                                view.informatieBoodschap(spel.getSpeler1().getNaam() + " wint!");
                            } else {
                                view.informatieBoodschap(spel.getSpeler2().getNaam() + " wint!");
                            }
                        }
                        if (!draaienTegenhouden) {
                            spel.changeBeurt();
                            spel.setW(RandomGenerator.getRandomNumber());
                            view.infoGedeelte();
                        }

                    }


                } catch (NumberFormatException ex) {
                    view.errorBoodschap(ex.getMessage());

                } catch (IllegalArgumentException aex) {
                    view.errorBoodschap(aex.getMessage());
                }

                view.emptySchotFields();
                if (!spel.getIsGeraakt()) {
                    view.activeerSchotInput(true);
                }
            }

            private boolean validHoek(String input) {
                return input != null && !input.isEmpty() && !containsLetter(input) && Integer.parseInt(input) <= 60;
            }

            private boolean validSnelheid(String input) {
                return input != null && !input.isEmpty() && !containsLetter(input) && Integer.parseInt(input) >= 10;
            }


            public boolean containsLetter(String input) {
                for (int i = 0; i < input.length(); i++) {
                    if (Character.isLetter(input.charAt(i))) {
                        return true;
                    }
                }
                return false;
            }


        });
        view.setOpslaanLitener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                spel.oplsaan();
                view.informatieBoodschap("Succesvol opgeslagen.");
                view.hervattenStatus(true);
            }
        });
        view.setHervatListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                draaienTegenhouden = false;
                spel.setGeraakt(false);
                view.activeerSchotInput(true);
                spel.getSaved();
                view.showInvoerEnInfo();
                view.infoGedeelte();
                view.laatLandschapZien(spel.getLandschap());
                view.opslaanStatus(true);
            }
        });
        view.setInfoListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                view.showInfo("assets/images/AngryTanksBackgroundInvisKlein.png");
            }
        });
    }

    public void Start() {

        if (view instanceof SpelGUI) {
            setListeners();
            ((SpelGUI) view).maakZichtbaar();
        } else {
            //Stappen in het spel

            spel.setSpelerNaam(view.naamIngaveSpeler1(), true);
            spel.setSpelerNaam(view.naamIngaveSpeler2(), false);
            spel.maakElementenArray(); //de Elementen array aamaken


            while (!spel.isGeraakt()) {

                spel.setW(RandomGenerator.getRandomNumber());
                view.infoGedeelte();
                view.laatLandschapZien(spel.getLandschap());
                spel.setA(Double.parseDouble(view.hoekIngeven()));
                spel.setV0(Integer.parseInt(view.snelheidIngeven()));
                spel.verWijderbaan();
                spel.tekenBaan();
                spel.changeBeurt();
            }
            spel.changeBeurt();
            view.winnaarTonen();
        }
    }

}
                                        