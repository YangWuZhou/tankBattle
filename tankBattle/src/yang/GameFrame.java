package yang;

import yang.entity.Tank;
import yang.type.TankType;
import yang.utils.Constant;

import javax.swing.*;

/**
 * 游戏框架类
 */
public class GameFrame extends JFrame {
    private Tank player1 = new Tank("img/play/p1tank_U.gif", TankType.PLAY1);

    public GameFrame() {
        paint();
    }

    private void paint() {
        add(GamePanel.loadGameInterface(player1));
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
                    Thread.sleep(45);
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
        addKeyListener(new GameMonitor(player1));
    }
}
