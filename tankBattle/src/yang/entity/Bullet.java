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
        // 计算子弹实际位置，并画在屏幕上
//        drawBullet(g);
        movingBullet();
        // 子弹是否还存在(是否碰到障碍物)
        if (!isAlive) {
            // 如果碰到土堆，消灭
            destroyObstacle();
//            drawBlast(g);
        }
    }

    // 消灭障碍物
    private void destroyObstacle() {
        // 碰到地图边缘
        if (x < 0 || y < 0 || x > Constant.MAX_POSITION || y > Constant.MAX_POSITION) {
            return;
        }

        int row = y;
        int line = x;
        switch (direction) {
            case UP:
                if (GameMap.map[row][line] == 4 || GameMap.map[row][line] == 5) {
                    GameMap.map[row][line] = 0;
                }
                if (GameMap.map[row][line+1] == 4 || GameMap.map[row][line+1] == 5) {
                    GameMap.map[row][line+1] = 0;
                }
                break;
            case DOWN:
                if (GameMap.map[row][line] == 4 || GameMap.map[row][line] == 5) {
                    GameMap.map[row][line] = 0;
                }
                if (GameMap.map[row][line-1] == 4 || GameMap.map[row][line-1] == 5) {
                    GameMap.map[row][line-1] = 0;
                }
                break;
            case LEFT:
                if (GameMap.map[row][line] == 4 || GameMap.map[row][line] == 5) {
                    GameMap.map[row][line] = 0;
                }
                if (GameMap.map[row-1][line] == 4 || GameMap.map[row-1][line] == 5) {
                    GameMap.map[row-1][line] = 0;
                }
                break;
            case RIGHT:
                if (GameMap.map[row][line] == 4 || GameMap.map[row][line] == 5) {
                    GameMap.map[row][line] = 0;
                }
                if (GameMap.map[row+1][line] == 4 || GameMap.map[row+1][line] == 5) {
                    GameMap.map[row+1][line] = 0;
                }
                break;
            default:
        }
    }

    /*// 子弹爆炸
    private void drawBlast(Graphics g) {
        Image blastImg;
        int practicalX = x * Constant.MOVING_PIXELS + Constant.FRAME_BEGIN_X;
        int practicalY = y * Constant.MOVING_PIXELS + Constant.FRAME_BEGIN_Y;
        for (int i = 1; i < 4; i++) {
            blastImg = GameUtil.getImage("img/blast/blast" + i + ".gif");
            g.drawImage(blastImg, practicalX, practicalY, null);
        }
    }

    // 画子弹
    private void drawBullet(Graphics g) {
        int bulletWidth = this.gameImg.getWidth(null);
        int bulletHeight = this.gameImg.getHeight(null);
        int practicalX = x * Constant.MOVING_PIXELS + Constant.FRAME_BEGIN_X;
        int practicalY = y * Constant.MOVING_PIXELS + Constant.FRAME_BEGIN_Y;
        switch (direction) {
            case UP:
                practicalX += Constant.MOVING_PIXELS - bulletWidth / 2;
                break;
            case DOWN:
                practicalX -= bulletWidth / 2;
                practicalY += Constant.MOVING_PIXELS - bulletHeight;
                break;
            case LEFT:
                practicalY -= bulletHeight / 2;
                break;
            case RIGHT:
                practicalX += Constant.MOVING_PIXELS - bulletWidth;
                practicalY += Constant.MOVING_PIXELS - bulletHeight / 2;
                break;
            default:
        }
        g.drawImage(this.gameImg, practicalX, practicalY, null);
    }*/

    // 子弹移动
    private void movingBullet() {
        // 根据方位改变坐标，表示子弹的移动
        switch (direction) {
            case UP:
                if (!hasObstacleAtUpwardDirection()) {
                    isAlive = false;
                }
                y--;
                break;
            case DOWN:
                if (!hasObstacleAtDownwardDirection()) {
                    isAlive = false;
                }
                y++;
                break;
            case LEFT:
                if (!hasObstacleAtLeftwardDirection()) {
                    isAlive = false;
                }
                x--;
                break;
            case RIGHT:
                if (!hasObstacleAtRightwardDirection()) {
                    isAlive = false;
                }
                x++;
                break;
            default:
        }
    }

    private boolean hasObstacleAtUpwardDirection() {
        int row = y;
        int line = x;
        return row > 0 && GameMap.map[row-1][line] == 0 && GameMap.map[row-1][line+1] == 0;
    }

    private boolean hasObstacleAtDownwardDirection() {
        int row = y;
        int line = x;
        return row < Constant.MAX_POSITION &&
                GameMap.map[row+1][line] == 0 && GameMap.map[row+1][line-1] == 0;
    }

    private boolean hasObstacleAtLeftwardDirection() {
        int row = y;
        int line = x;
        return line > 0 &&
                GameMap.map[row][line-1] == 0 && GameMap.map[row-1][line-1] == 0;
    }

    private boolean hasObstacleAtRightwardDirection() {
        int row = y;
        int line = x;
        return line < Constant.MAX_POSITION &&
                GameMap.map[row][line+1] == 0 && GameMap.map[row+1][line+1] == 0;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
