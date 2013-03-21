package view.viewelements;

import model.elementen.Element;

import javax.swing.*;
import java.awt.*;

public class LandschapPanel extends JPanel {
    private Element[][] landschap;
    private Graphics g;
    private Toolkit toolkit;

    public LandschapPanel() {
        repaint();
        toolkit = Toolkit.getDefaultToolkit();
    }

    public void setLandschap(Element[][] landschap) {
        this.landschap = landschap;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.g = g;
        if (landschap != null) {
            for (int row = 0; row < 43; row++) {
                for (int col = 0; col < 100; col++) {
                    specificDrawing(row, col);
                }
            }
        }

    }

    public void specificDrawing(int row, int col) {
        //Icon img = new ImageIcon(getClass().getResource("assets/gui/" + landschap[row][col].getFileName() + ".png"));
        Image img = toolkit.getImage(("assets/images/" + landschap[row][col].getFileName() + ".png"));
        g.drawImage(img, (col * 8) + 2, getHeight() - (row * 8) - 10, this);
    }
}
