package test;

import yang.utils.GameUtil;

import javax.swing.*;
import java.awt.*;

public class Background extends JFrame {

    class ImgComponent extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            Image image = GameUtil.getImage("img/background/bg1.gif");
            g.drawImage(image, 0, 0, null);
        }
    }

    public Background() {
        add(new ImgComponent());
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Background frame = new Background();
            frame.setVisible(true);
            frame.setSize(1000, 1000);
        });
    }
}
