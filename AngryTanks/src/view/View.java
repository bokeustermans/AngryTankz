package view;

import controller.*;
import model.elementen.Element;

import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;

public interface View {

    public void infoGedeelte();
    public String naamIngaveSpeler1();
    public String naamIngaveSpeler2();
    public String hoekIngeven();
    public String snelheidIngeven();
    public void laatLandschapZien(Element[][] landschapArray);
    public void showSpelRegels(String regels);
    public void setStartListener(ActionListener startListener);
    public void setHelpListener(ActionListener helpListener);
    public void setSchotListener(ActionListener schotListener);
    public void setOpslaanLitener(ActionListener opslaanLitener) ;
    public void emptySchotFields();
    public void activeerSchotInput(boolean actief);
    public void errorBoodschap (String error);
    public void showInvoerEnInfo();
    public void voegPanelsToe();
    public void informatieBoodschap(String informatie);
    public void opslaanStatus(boolean status);
    public void setHervatListener(ActionListener hervatListener);
    public void setInfoListener(ActionListener infoListener);
    public void hervattenStatus(boolean status);
    public void winnaarTonen();
    public void showInfo(String str);
}
