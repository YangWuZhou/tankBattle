package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyEventTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new EventFrame();
            frame.setTitle("event");
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            ImageComponent tankComponent = new ImageComponent();
            frame.add(tankComponent);

            tankComponent.addKeyListener(new KeyListenerTest(tankComponent));


        });
    }
}

class EventFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 500;
    private static final int DEFAULT_HEIGHT = 400;

    public EventFrame() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}

class ImageComponent extends JComponent {
    @Override
    protected void paintComponent(Graphics g) {
        Image tank = new ImageIcon("../img/p1tank_U.gif").getImage();
        System.out.println(tank);
        g.drawImage(tank, 0, 0, null);
    }
}

class KeyListenerTest implements KeyListener {
    private JComponent component;

    public KeyListenerTest(JComponent component) {
        this.component = component;
    }

    // 发生击键事件时触发
    @Override
    public void keyTyped(KeyEvent e) {
    }

    // 按键被按下时触发
    @Override
    public void keyPressed(KeyEvent e) {
        String keyWord = KeyEvent.getKeyText(e.getKeyCode());
        System.out.println(keyWord);
        switch (keyWord) {
            case "W":
                // 上移
                break;
            case "S":
                // 下移
                break;
            case "A":
                // 左移
                break;
            case "D":
                // 右移
                break;
            case "J":
                // 发射子弹
                break;
            default:
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
