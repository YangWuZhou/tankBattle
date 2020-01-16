package yang.entity;

import yang.map.GameMap;
import yang.type.Direction;
import yang.utils.Constant;
import yang.utils.GameUtil;

import java.awt.*;

/**
 * 子弹类
 */
public class Bullet extends GameObject {
    private boolean isAlive;

    public Bullet(Image image, int x, int y, Direction direction) {
        super(image, x, y);
        this.direction = direction;
        this.isAlive = true;
    }

    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
        // 根据方位改变坐标，表示子弹的移动
        switch (direction) {
            case UP:
                if (!hasObstacleAtUpwardDirection()) {
                    isAlive = false;
                }
                y -= Constant.MOVING_PIXELS / 3;
                break;
            case DOWN:
                if (!hasObstacleAtDownwardDirection()) {
                    isAlive = false;
                }
                y += Constant.MOVING_PIXELS / 3;
                break;
            case LEFT:
                if (!hasObstacleAtLeftwardDirection()) {
                    isAlive = false;
                }
                x -= Constant.MOVING_PIXELS / 3;
                break;
            case RIGHT:
                if (!hasObstacleAtRightwardDirection()) {
                    isAlive = false;
                }
                x += Constant.MOVING_PIXELS / 3;
                break;
            default:
        }
        if (!isAlive) {
            Image blastImg;
            for (int i = 1; i < 4; i++) {
                blastImg = GameUtil.getImage("img/blast/blast" + i + ".gif");
                g.drawImage(blastImg, x, y, null);
            }
        }
    }

    private boolean hasObstacleAtUpwardDirection() {
        int row = getRow();
        int line = getLine();
        return y > Constant.FRAME_BEGIN_Y && GameMap.map1[row][line] == 0 && GameMap.map1[row][line+1] == 0;
    }

    private boolean hasObstacleAtDownwardDirection() {
        int row = getRow();
        int line = getLine();
        int height = this.gameImg.getHeight(null);
        return y < (Constant.FRAME_END_Y - height) &&
                GameMap.map1[row][line] == 0 && GameMap.map1[row][line+1] == 0;
    }

    private boolean hasObstacleAtLeftwardDirection() {
        int row = getRow();
        int line = getLine();
        return x > Constant.FRAME_BEGIN_X &&
                GameMap.map1[row][line] == 0 && GameMap.map1[row+1][line] == 0;
    }

    private boolean hasObstacleAtRightwardDirection() {
        int row = getRow();
        int line = getLine();
        int width = this.gameImg.getWidth(null);
        return x < (Constant.FRAME_END_X - width) &&
                GameMap.map1[row][line] == 0 && GameMap.map1[row+1][line] == 0;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
