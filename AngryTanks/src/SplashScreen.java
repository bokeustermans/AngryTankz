import javax.swing.*;
import java.awt.*;

public class SplashScreen extends JWindow {
    private int duration;
    private Image img;

    public SplashScreen(int d) {
        duration = d;
    }


    public void showSplash() {

       //img = Toolkit.getDefaultToolkit().getImage("assets/gui/AngryTanksBackgroundInvis.png");
        Icon img = new ImageIcon("assets/images/AngryTanksBackgroundInvis.png");
        JPanel content = (JPanel) getContentPane();
        content.setBackground(Color.white);


        int width = 800;
        int height = 450;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - width) / 2;
        int y = (screen.height - height) / 2;
        setBounds(x, y, width, height);
        JLabel label = new JLabel(img);
        content.add(label, BorderLayout.CENTER);
        Color black = new Color(0,0,0);
        content.setBorder(BorderFactory.createLineBorder(black, 1));


        setVisible(true);


        try {
            Thread.sleep(duration);
        } catch (Exception e) {
        }

        setVisible(false);
    }

    public void showSplashAndExit() {
        showSplash();

    }

}
