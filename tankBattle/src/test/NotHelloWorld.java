package test;

import javax.swing.*;
import java.awt.*;

public class NotHelloWorld {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new NotHelloWorldFrame();
            frame.setTitle("Not Hello World");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

class NotHelloWorldFrame extends JFrame {
    public NotHelloWorldFrame() {
        add(new NotHelloWorldComponent());
        pack();
    }
}

/**
 * 展示消息的组件
 */
class NotHelloWorldComponent extends JComponent {
    public static final int MESSAGE_X = 75;
    public static final int MESSAGE_Y = 100;

    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    @Override
    protected void paintComponent(Graphics g) {
//        g.drawString("Not Hello World", MESSAGE_X, MESSAGE_Y);
        g.drawLine(0, 0, 100, 100);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}
