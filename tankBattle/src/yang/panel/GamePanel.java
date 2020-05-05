package yang.panel;

import yang.entity.Tank;
import yang.map.GameMap;
import yang.utils.Constant;
import yang.utils.GameUtil;

import javax.swing.*;
import java.awt.*;

/**
 * 游戏元素加载的组件
 */
public class GamePanel extends JPanel {
    private Tank[] tanks;

    private GamePanel(Tank... tanks) {
        this.tanks = tanks;
    }

    // 加载界面
    public static GamePanel loadGameInterface(Tank... tanks) {
        return new GamePanel(tanks);
    }

    @Override
    protected void paintComponent(Graphics g) {
        paintBackground(g);
//        paintTank(g);
//        paintWall(g);
    }

    // 加载背景图
    private void paintBackground(Graphics g) {
        g.drawImage(GameUtil.getImage("img/background/bg1.gif"), 0, 0, null);
        g.drawImage(GameUtil.getImage("img/background/bg2.gif"),
                Constant.FRAME_BEGIN_X, Constant.FRAME_BEGIN_Y, null);
    }

    // 加载坦克
    private void paintTank(Graphics g) {
        for (Tank tank : tanks) {
            tank.paintSelf(g);
        }
    }

    // 加载障碍物
    private void paintWall(Graphics g) {
        GameMap.paintMap(g);
    }
}
