package yang.entity;

import yang.map.GameMap;
import yang.type.Direction;
import yang.type.TankType;
import yang.utils.Constant;
import yang.utils.GameUtil;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 坦克类
 */
public class Tank extends GameObject {
    private String tankName;
    private List<Bullet> bullets;

    public Tank(String filePath, TankType tankType) {
        super(filePath, 0, 0);
        String[] strArray = filePath.split("_");
        this.tankName = strArray[0];
        direction = GameUtil.judgeDirection(strArray[1]);
        initLocation(tankType);
        bullets = new ArrayList<>(3);
    }

    // 初始化坦克的位置
    private void initLocation(TankType tankType) {
        switch (tankType) {
            case PLAY1:
                this.x = 4 * 2 * Constant.MOVING_PIXELS + Constant.FRAME_BEGIN_X;
                this.y = Constant.FRAME_END_Y - gameImg.getHeight(null);
                break;
            case PLAY2:
                this.x = 9 * 2 * Constant.MOVING_PIXELS + Constant.FRAME_BEGIN_X;
                this.y = Constant.FRAME_END_Y - gameImg.getHeight(null);
                break;
            case ENEMY:
                break;
            default:
        }
    }

    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
        paintBullet(g);
    }

    private void paintBullet(Graphics g) {
        Iterator<Bullet> bulletIterator = bullets.iterator();
        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();
            if (bullet.isAlive()) {
                bullet.paintSelf(g);
            } else {
                bulletIterator.remove();
            }
        }
    }

    /**
     * 坦克向上行动
     */
    public void up() {
        if (!Direction.UP.equals(direction)) {
            String filePath = tankName + "_U" + Constant.IMAGE_SUFFIX;
            gameImg = GameUtil.getImage(filePath);
            direction = Direction.UP;
        } else {
            // 判断是否碰到了障碍物
            if (hasObstacleAtUpwardDirection()) {
                y -= Constant.MOVING_PIXELS;
            }
        }
    }

    private boolean hasObstacleAtUpwardDirection() {
        int row = getRow();
        int line = getLine();
        return y > Constant.FRAME_BEGIN_Y && GameMap.map1[row-1][line] == 0 && GameMap.map1[row-1][line+1] == 0;
    }

    /**
     * 坦克向下行动
     */
    public void down() {
        if (!Direction.DOWN.equals(direction)) {
            String filePath = tankName + "_D" + Constant.IMAGE_SUFFIX;
            gameImg = GameUtil.getImage(filePath);
            direction = Direction.DOWN;
        } else {
            if (hasObstacleAtDownwardDirection()) {
                y += Constant.MOVING_PIXELS;
            }
        }
    }

    private boolean hasObstacleAtDownwardDirection() {
        int row = getRow();
        int line = getLine();
        int height = this.gameImg.getHeight(null);
        return y < (Constant.FRAME_END_Y - height) &&
                GameMap.map1[row+1][line] == 0 && GameMap.map1[row+1][line-1] == 0;
    }

    /**
     * 坦克向左行动
     */
    public void left() {
        if (!Direction.LEFT.equals(direction)) {
            String filePath = tankName + "_L" + Constant.IMAGE_SUFFIX;
            gameImg = GameUtil.getImage(filePath);
            direction = Direction.LEFT;
        } else {
            if (hasObstacleAtLeftwardDirection()) {
                x -= Constant.MOVING_PIXELS;
            }
        }
    }

    private boolean hasObstacleAtLeftwardDirection() {
        int row = getRow();
        int line = getLine();
        return x > Constant.FRAME_BEGIN_X &&
                GameMap.map1[row][line-1] == 0 && GameMap.map1[row-1][line-1] == 0;
    }

    /**
     * 坦克向右行动
     */
    public void right() {
        if (!Direction.RIGHT.equals(direction)) {
            String filePath = tankName + "_R" + Constant.IMAGE_SUFFIX;
            gameImg = GameUtil.getImage(filePath);
            direction = Direction.RIGHT;
        } else {
            if (hasObstacleAtRightwardDirection()) {
                x += Constant.MOVING_PIXELS;
            }
        }
    }

    private boolean hasObstacleAtRightwardDirection() {
        int row = getRow();
        int line = getLine();
        int width = this.gameImg.getWidth(null);
        return x < (Constant.FRAME_END_X - width) &&
                GameMap.map1[row][line+1] == 0 && GameMap.map1[row+1][line+1] == 0;
    }

    /**
     * 坦克攻击
     */
    public void attack() {
        Bullet bullet = loadBulletLocation();
        bullets.add(bullet);
    }

    private Bullet loadBulletLocation() {
        Image bulletImg = GameUtil.getImage("img/bullet/tankBullet.gif");
        int bulletX = this.x;
        int bulletY = this.y;
        int tankWidth = this.gameImg.getWidth(null);
        int tankHeight = this.gameImg.getHeight(null);
        int bulletWidth = bulletImg.getWidth(null);
        int bulletHeight = bulletImg.getHeight(null);
        switch (direction) {
            case UP:
                bulletX += tankWidth / 2 - bulletWidth / 2;
                break;
            case DOWN:
                bulletX += tankWidth / 2 - bulletWidth / 2;
                bulletY += 2 * Constant.MOVING_PIXELS;
                break;
            case LEFT:
                bulletY += tankHeight / 2 - bulletHeight / 2;
                break;
            case RIGHT:
                bulletX += 2 * Constant.MOVING_PIXELS;
                bulletY += tankHeight / 2 - bulletHeight / 2;
                break;
            default:
        }
        return new Bullet(bulletImg, bulletX, bulletY, this.direction);
    }
}
