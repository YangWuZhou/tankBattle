package yang;

import yang.entity.Tank;
import yang.map.GameMap;
import yang.utils.GameUtil;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private Tank[] tanks;

    private GamePanel(Tank... tanks) {
        this.tanks = tanks;
    }

    public static GamePanel initGameInterface(Tank... tanks) {
        return new GamePanel(tanks);
    }

    @Override
    protected void paintComponent(Graphics g) {
        paintBackground(g);
        paintTank(g);
        paintWall(g);
    }

    // 加载背景图
    private void paintBackground(Graphics g) {
        g.drawImage(GameUtil.getImage("img/background/bg1.gif"), 0, 0, null);
        g.drawImage(GameUtil.getImage("img/background/bg2.gif"),
                GameUtil.FRAME_BEGIN_X, GameUtil.FRAME_BEGIN_Y, null);
    }

    // 加载坦克
    private void paintTank(Graphics g) {
        for (int i = 0; i < tanks.length; i++) {
            tanks[i].paintSelf(g);
        }
    }

    // 加载障碍物
    private void paintWall(Graphics g) {
        GameMap.paintMap(g);
    }
}
