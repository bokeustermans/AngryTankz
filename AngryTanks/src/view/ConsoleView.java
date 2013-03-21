package view;

import model.elementen.Element;

import java.awt.event.ActionListener;

public abstract class ConsoleView implements View {
    public void voegPanelsToe(){
    }

    public void infoGedeelte() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public String naamIngaveSpeler1() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public String naamIngaveSpeler2() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public String hoekIngeven() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public String snelheidIngeven() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void laatLandschapZien(Element[][] landschapArray) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void showSpelRegels(String regels) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setStartListener(ActionListener startListener) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setHelpListener(ActionListener helpListener) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setSchotListener(ActionListener schotListener) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setOpslaanLitener(ActionListener opslaanLitener) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void emptySchotFields() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void activeerSchotInput(boolean actief) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void errorBoodschap(String error) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void showInvoerEnInfo() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void informatieBoodschap(String informatie) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void opslaanStatus(boolean status) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setHervatListener(ActionListener hervatListener) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setInfoListener(ActionListener infoListener) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void hervattenStatus(boolean status) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void winnaarTonen() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void showInfo(String str) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
