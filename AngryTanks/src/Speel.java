import controller.SpelController;
import model.Schot;
import model.Spel;
import view.SpelGUI;
import view.SpelView;
import view.View;

import javax.swing.*;

public class Speel {

    public static void main(String[] args) {
        SplashScreen splash = new SplashScreen(3000);
        splash.showSplashAndExit();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InstantiationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        Spel spel = new Spel();
        View view = new SpelGUI(spel);
        SpelController spelController = new SpelController(view,spel);
        spelController.Start();
    }


}


