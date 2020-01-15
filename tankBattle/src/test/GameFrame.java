package test;

import yang.utils.GameUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameFrame extends JFrame {
//    private Image wall = new ImageIcon(this.getClass().getResource("/img/water.gif")).getImage();
    private Image tank = GameUtil.getImage("img/play/p1tank_U.gif");
    private Image background = new ImageIcon(this.getClass().getResource("/img/bg.gif")).getImage();
    private static final int GAME_WIDTH = 700;
    private static final int GAME_HEIGHT = 650;

    private static final int LOCATION_X = 300;
    private static final int LOCATION_Y = 200;

    private static int tankX = GAME_WIDTH / 2;
    private static int tankY = GAME_HEIGHT / 2;

    @Override
    public void paint(Graphics g) {
        g.drawImage(background, 0, 0, null);
//        g.drawImage(wall, GAME_WIDTH / 2, tankY, null);
        g.drawImage(tank, tankX, tankY, null);
    }

    class KeyMonitor extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            String keyWord = KeyEvent.getKeyText(e.getKeyCode());

            switch (keyWord) {
                case "W": tankY--;
                    // 上移
                    break;
                case "S": tankY++;
                    // 下移
                    break;
                case "A": tankX -= 15;
                    // 左移
                    break;
                case "D": tankX += 15;
                    // 右移

                    break;
                case "J":
                    // 发射子弹
                    break;
                default:
            }
        }
    }

    class PaintThread extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    repaint();
                    Thread.sleep(45);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void initFrame() {
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocation(LOCATION_X, LOCATION_Y);
        new PaintThread().start();
        addKeyListener(new KeyMonitor());
    }

    public static void main(String[] args) {
        GameFrame frame = new GameFrame();
        frame.initFrame();
    }
}
