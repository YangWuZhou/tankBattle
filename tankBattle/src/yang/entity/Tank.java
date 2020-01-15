package yang.entity;

import yang.map.GameMap;
import yang.utils.GameUtil;
import yang.type.Direction;
import yang.type.TankType;

/**
 * 坦克类
 */
public class Tank extends GameObject {
    private String tankName;
    private Direction direction;

    public Tank(String filePath, TankType tankType) {
        super(filePath, 0, 0);
        String[] strArray = filePath.split("_");
        this.tankName = strArray[0];
        direction = GameUtil.judgeDirection(strArray[1]);
        initLocation(tankType);
    }

    // 初始化坦克的位置
    private void initLocation(TankType tankType) {
        switch (tankType) {
            case PLAY1:
                this.x += 4 * 2 * GameUtil.MOVING_PIXELS;
                this.y = GameUtil.FRAME_END_Y - gameImg.getHeight(null);
                break;
            case PLAY2:
                this.x += 9 * 2 * GameUtil.MOVING_PIXELS;
                this.y = GameUtil.FRAME_END_Y - gameImg.getHeight(null);
                break;
            case ENEMY:
                break;
            default:
        }
    }

    /**
     * 坦克向上行动
     */
    public void up() {
        if (!Direction.UP.equals(direction)) {
            String filePath = tankName + "_U" + GameUtil.IMAGE_SUFFIX;
            gameImg = GameUtil.getImage(filePath);
            direction = Direction.UP;
        } else {
            // 判断是否碰到了障碍物
            if (hasObstacleAtUpwardDirection()) {
                y -= GameUtil.MOVING_PIXELS;
            }
        }
    }

    private boolean hasObstacleAtUpwardDirection() {
        int row = getRow();
        int line = getLine();
        return y > GameUtil.FRAME_BEGIN_Y && GameMap.map1[row-1][line] == 0 && GameMap.map1[row-1][line+1] == 0;
    }

    /**
     * 坦克向下行动
     */
    public void down() {
        if (!Direction.DOWN.equals(direction)) {
            String filePath = tankName + "_D" + GameUtil.IMAGE_SUFFIX;
            gameImg = GameUtil.getImage(filePath);
            direction = Direction.DOWN;
        } else {
            if (hasObstacleAtDownwardDirection()) {
                y += GameUtil.MOVING_PIXELS;
            }
        }
    }

    private boolean hasObstacleAtDownwardDirection() {
        int row = getRow() + 1;
        int line = getLine() + 1;
        return y < (GameUtil.FRAME_END_Y - 2 * GameUtil.MOVING_PIXELS) &&
                GameMap.map1[row+1][line] == 0 && GameMap.map1[row+1][line-1] == 0;
    }

    /**
     * 坦克向左行动
     */
    public void left() {
        if (!Direction.LEFT.equals(direction)) {
            String filePath = tankName + "_L" + GameUtil.IMAGE_SUFFIX;
            gameImg = GameUtil.getImage(filePath);
            direction = Direction.LEFT;
        } else {
            if (hasObstacleAtLeftwardDirection()) {
                x -= GameUtil.MOVING_PIXELS;
            }
        }
    }

    private boolean hasObstacleAtLeftwardDirection() {
        int row = getRow() + 1;
        int line = getLine();
        return x > GameUtil.FRAME_BEGIN_X &&
                GameMap.map1[row][line-1] == 0 && GameMap.map1[row-1][line-1] == 0;
    }

    /**
     * 坦克向右行动
     */
    public void right() {
        if (!Direction.RIGHT.equals(direction)) {
            String filePath = tankName + "_R" + GameUtil.IMAGE_SUFFIX;
            gameImg = GameUtil.getImage(filePath);
            direction = Direction.RIGHT;
        } else {
            if (hasObstacleAtRightwardDirection()) {
                x += GameUtil.MOVING_PIXELS;
            }
        }
    }

    private boolean hasObstacleAtRightwardDirection() {
        int row = getRow();
        int line = getLine() + 1;
        return x < (GameUtil.FRAME_END_X - 2 * GameUtil.MOVING_PIXELS) &&
                GameMap.map1[row][line+1] == 0 && GameMap.map1[row+1][line+1] == 0;
    }

    /**
     * 坦克攻击
     */
    public void attack() {

    }

}
