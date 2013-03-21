package view;

import model.Spel;
import model.elementen.Element;
import view.viewelements.LandschapPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpelGUI extends JFrame implements View {

    JFrame frame;
    private JMenuBar jMenuBar;
    private JMenu menu;
    private JMenu helpMenu;
    private JMenuItem info;
    private JMenuItem start;
    private JMenuItem help;
    private JMenuItem opslaan;
    private JMenuItem hervatten;
    private Spel spel;
    private JTextField hoek;
    private JTextField snelheid;
    private JButton schiet;
    private JPanel invoerPanel;
    private JLabel hoekLabelTekst;
    private JLabel snelheidLabel;
    private Icon windIcon;
    private JLabel windLabel;
    private Icon spelerIcon;
    private JLabel spelerLabel;
    private Icon hoekIcon;
    private JLabel hoekLabel;
    private Icon snelheidIcon;
    private JLabel snelheidLabelInfo;
    private Icon scoreIcon;
    private JLabel scoreLabel;
    private JPanel infoPanel;
    private JPanel contentHolder;
    private LandschapPanel landschapsPanel;
    private Icon infoAfbeelding;
    private JPanel beginAchtergrond;
    



    public SpelGUI(Spel spel){
        this.spel = spel;
        maakComponenten();
        maakLayout();
        achtergrondToevoegen();
        maakZichtbaar();
    }


    public void maakZichtbaar() {
        frame.setResizable(false);
        frame.setTitle("Angry Tanks");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(802, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void achtergrondToevoegen() {
        try{
        BufferedImage myPicture = ImageIO.read(new File("assets/images/AngryTanksBackgroundInvis.png"));
        JLabel picLabel = new JLabel(new ImageIcon( myPicture ));
        beginAchtergrond.add(picLabel);
        } catch (IOException e){
          e.printStackTrace();
        }
        frame.add(beginAchtergrond);
    }

    public void removeBackground(){
          frame.remove(beginAchtergrond);
    }

    public void voegPanelsToe(){
        removeBackground();
        frame.add(contentHolder, BorderLayout.CENTER);
        showInvoerEnInfo();

    }

    private void maakComponenten() {
        frame = new JFrame();
        beginAchtergrond = new JPanel();

        //menu
        jMenuBar = new JMenuBar();
        menu = new JMenu("Bestand");
        start = new JMenuItem("Nieuw spel");
        help = new JMenuItem("Spelregels");
        opslaan = new JMenuItem("Opslaan");
        hervatten = new JMenuItem("Hervatten");
        helpMenu = new JMenu("Help");
        info = new JMenuItem("Info");
        if(!spel.fileExist()){
          hervattenStatus(false);
        }

        //Landschapgedeelte

        //Invoergedeelte
        invoerPanel = new JPanel();
        hoek = new JTextField();
        snelheid = new JTextField();
        schiet = new JButton("Schiet");
        snelheidLabel = new JLabel("Snelheid:");
        hoekLabelTekst = new JLabel("Hoek:");

        //Infogedeelte
        infoPanel = new JPanel();
        windIcon = new ImageIcon("assets/images/Wind.png");
        windLabel = new JLabel("");
        spelerIcon = new ImageIcon("assets/images/user.png");
        spelerLabel = new JLabel("");
        hoekIcon = new ImageIcon("assets/images/Angle.png");
        hoekLabel = new JLabel("");
        snelheidIcon = new ImageIcon("assets/images/Speed.png");
        snelheidLabelInfo = new JLabel("");
        scoreIcon = new ImageIcon("assets/images/score.png");
        scoreLabel = new JLabel("");

        //Landschap
        contentHolder = new JPanel();
        landschapsPanel = new LandschapPanel();

    }

    public void maakLayout() {


        menu.add(start);
        menu.add(opslaan);
        menu.add(hervatten);
        helpMenu.add(help);
        helpMenu.add(info);
        jMenuBar.add(menu);
        jMenuBar.add(helpMenu);
        frame.setJMenuBar(jMenuBar);
        opslaanStatus(false);



        //Invoer
        schiet.setPreferredSize(new Dimension(100, 30));
        snelheid.setPreferredSize(new Dimension(50, 30));
        hoek.setPreferredSize(new Dimension(50, 30));
        invoerPanel.setBackground(Color.lightGray);
        invoerPanel.setLayout(new FlowLayout());

        invoerPanel.add(hoekLabelTekst);
        invoerPanel.add(hoek);
        invoerPanel.add(snelheidLabel);
        invoerPanel.add(snelheid);
        invoerPanel.add(schiet);


        //Info
        infoPanel.setBackground(Color.lightGray);
        infoPanel.setLayout(new GridLayout(1, 5));
        windLabel.setIcon(windIcon);
        infoPanel.add(windLabel);
        spelerLabel.setIcon(spelerIcon);
        infoPanel.add(spelerLabel);
        hoekLabel.setIcon(hoekIcon);
        infoPanel.add(hoekLabel);
        snelheidLabelInfo.setIcon(snelheidIcon);
        infoPanel.add(snelheidLabelInfo);
        scoreLabel.setIcon(scoreIcon);
        infoPanel.add(scoreLabel);

        //Landschap

        contentHolder.setLayout(new GridLayout(1, 1));
        contentHolder.add(landschapsPanel);

    }

    public void infoGedeelte() {
        String hoek = String.format("%.0f", spel.getSpeler().getHoekVorig());
        windLabel.setText(String.valueOf(spel.getW()));
        spelerLabel.setText(spel.getHuidigeSpeler());
        hoekLabel.setText(hoek);
        snelheidLabelInfo.setText(String.valueOf(spel.getSpeler().getSnelheidVorig()));
        scoreLabel.setText(String.valueOf(spel.getSpeler().getScore()));
    }

    public String naamIngaveSpeler1() {
        return JOptionPane.showInputDialog("Speler 1 geef je naam:");
    }

    public void opslaanStatus(boolean status) {
        opslaan.setEnabled(status);
    }

    public void hervattenStatus(boolean status){
        hervatten.setEnabled(status);
    }

    public String naamIngaveSpeler2() {
        return JOptionPane.showInputDialog("Speler 2 geef je naam:");
    }

    public String hoekIngeven() {
        return hoek.getText();
    }

    public String snelheidIngeven() {
        return snelheid.getText();
    }

    public void laatLandschapZien(Element[][] landschapArray) {
        landschapsPanel.setLandschap(landschapArray);
        landschapsPanel.repaint();
        //
    }

    public void winnaarTonen() {
    }

    public void setStartListener(ActionListener startListener) {
        start.addActionListener(startListener);
    }

    public void setHelpListener(ActionListener helpListener) {
        help.addActionListener(helpListener);
    }

    public void setSchotListener(ActionListener schotListener) {
        schiet.addActionListener(schotListener);
    }

    public void setOpslaanLitener(ActionListener opslaanListener) {
        opslaan.addActionListener(opslaanListener);
    }

    public void setHervatListener(ActionListener hervatListener) {
        hervatten.addActionListener(hervatListener);
    }

    public void setInfoListener(ActionListener infoListener) {
        info.addActionListener(infoListener);
    }


    public void emptySchotFields() {
        hoek.setText("");
        snelheid.setText("");
    }

    public void activeerSchotInput(boolean actief) {
        hoek.setEditable(actief);
        snelheid.setEditable(actief);
        schiet.setEnabled(actief);
    }

    public void showSpelRegels(String regels) {
        JOptionPane.showMessageDialog(null, regels, "Spelregels", JOptionPane.INFORMATION_MESSAGE);
    }


    public void informatieBoodschap(String informatie) {
        JOptionPane.showMessageDialog(null, informatie, "Informatie", JOptionPane.INFORMATION_MESSAGE);
    }

    public void errorBoodschap(String error) {
        JOptionPane.showMessageDialog(null, error, "Let op!", JOptionPane.WARNING_MESSAGE);
    }

    public void showInfo(String str){
       infoAfbeelding = new ImageIcon(str);
       JOptionPane.showMessageDialog(null,"","Info",JOptionPane.INFORMATION_MESSAGE,infoAfbeelding );
    }

    public void showInvoerEnInfo() {
        frame.add(infoPanel, BorderLayout.NORTH);
        frame.add(invoerPanel, BorderLayout.SOUTH);

    }

}
