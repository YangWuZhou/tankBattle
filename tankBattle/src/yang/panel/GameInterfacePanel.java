package yang.panel;

import yang.entity.Tank;
import yang.map.GameMap;
import yang.monitor.GameMonitor;
import yang.type.TankType;
import yang.utils.Constant;
import yang.utils.FilePrefix;
import yang.utils.GameUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class GameInterfacePanel extends JPanel {
    // 这两个变量，用于切换画面
    private JPanel panel;  // 作用：添加监听器
    private CardLayout card;
    private KeyListener listener;
    // 是否已有监听器
    private boolean hasListener = false;

    private Tank player1 = new Tank(FilePrefix.PLAYER_IMG + "p1tank_U1.png", TankType.PLAY1);

    public GameInterfacePanel(JPanel panel, CardLayout card) {
        this.panel = panel;
        this.card = card;
        listener = new GameMonitor(player1);
    }

    @Override
    protected void paintComponent(Graphics g) {
        drawInterface(g);
        drawObstacle(g);
        drawTank(g);
        if (!hasListener) {
            panel.addKeyListener(listener);
            hasListener = true;
        }
    }

    // 画界面
    private void drawInterface(Graphics g) {
        g.drawImage(GameUtil.getImage(FilePrefix.INTERFACE_IMG + "gameBottomPage_1.png"),
                0, 0, null);
        g.drawImage(GameUtil.getImage(FilePrefix.INTERFACE_IMG + "gamePage.png"),
                Constant.FRAME_BEGIN_X, Constant.FRAME_BEGIN_Y, null);
        // 坦克生命
        g.drawImage(GameUtil.getImage(FilePrefix.NUMBER_IMG + "number_2.png"),
                Constant.PLAYER1_LIEF_X, Constant.PLAYER1_LIEF_Y, null);
        // 关卡
        g.drawImage(GameUtil.getImage(FilePrefix.NUMBER_IMG + "number_1.png"),
                Constant.CURRENT_LEVEL_X, Constant.CURRENT_LEVEL_Y, null);
    }

    // 画障碍物
    private void drawObstacle(Graphics g) {
        GameMap.paintMap(g);
    }

    private void drawTank(Graphics g) {
        player1.paintSelf(g);
    }
}
