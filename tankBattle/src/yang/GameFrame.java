package yang;

import yang.panel.GameInterfacePanel;
import yang.panel.SelectLevelInterfacePanel;
import yang.panel.StartInterfacePanel;
import yang.utils.Constant;

import javax.swing.*;
import java.awt.*;

/**
 * 游戏框架类
 */
public class GameFrame extends JFrame {

    public GameFrame() {
        CardLayout card = new CardLayout();
        JPanel panel = new JPanel(card);
        panel.add(new StartInterfacePanel(panel, card), "startInterface");
        panel.add(new SelectLevelInterfacePanel(panel, card), "selectLevelInterface");
        panel.add(new GameInterfacePanel(panel, card), "gameInterface");
        add(panel);
    }

    /**
     * 界面重画线程
     */
    private class GameThread extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    repaint();
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void initFrame() {
        this.setSize(Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(Constant.LOCATION_X, Constant.LOCATION_Y);
        this.setVisible(true); // 可见
        new GameThread().start();
    }
}
