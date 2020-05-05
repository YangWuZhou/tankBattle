package yang.entity;

import yang.map.GameMap;
import yang.type.Direction;
import yang.type.TankType;
import yang.utils.Constant;
import yang.utils.GameUtil;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * 坦克类
 */
public class Tank {
    // 横坐标像素
    private int x;
    // 纵坐标像素
    private int y;
    // 在数组中的行
    private int row;
    // 在数组中的列
    private int line;
    private Image tankImg;
    private String tankName;
    private TankType tankType;
    private int step = 0;
    // 图片后面的数字
    private int[] imgNums = {1, 2};
    private int currentImgNum;
    // 方向移动顺序
    private List<Direction> movingDirection;

    public Tank(String filePath, TankType tankType) {
        initParam(filePath, tankType);
        initLocation(tankType);
    }

    // 初始化参数
    private void initParam(String filePath, TankType tankType) {
        this.x = Constant.FRAME_BEGIN_X;
        this.y = Constant.FRAME_BEGIN_Y;
        this.tankType = tankType;
        this.movingDirection = new LinkedList<>();
        tankImg = GameUtil.getImage(filePath);
        String[] strArray = filePath.split("_");
        this.tankName = strArray[0];
        currentImgNum = Integer.parseInt(strArray[1].split("")[1]);
    }

    // 初始化坦克的位置(游戏界面初始位置 + 坦克在地图上的位置)
    private void initLocation(TankType tankType) {
        switch (tankType) {
            case PLAY1:
                this.row = 24;
                this.line = 8;
                this.x += line * Constant.SPACING;
                this.y += row * Constant.SPACING;
                break;
            case PLAY2:
                this.x += 16 * Constant.SPACING;
                this.y += 24 * Constant.SPACING;
                break;
            case ENEMY:
                break;
            default:
        }
    }

    public void paintSelf(Graphics g) {
        if (!movingDirection.isEmpty()) {
            Direction currentDirection = movingDirection.get(movingDirection.size()-1);
            changeDirection(currentDirection);
            switch (currentDirection) {
                case UP:
                    this.up();
                    break;
                case DOWN:
                    this.down();
                    break;
                case LEFT:
                    this.left();
                    break;
                case RIGHT:
                    this.right();
                    break;
                default:
            }
        }
        g.drawImage(tankImg, x, y, null);
    }

    private void changeDirection(Direction currentDirection) {
        int index = currentImgNum == 1 ? 1 : 0;
        currentImgNum = imgNums[index];
        String tankImgPath = tankName + "_";
        switch (currentDirection) {
            case UP:
                tankImgPath += "U" + currentImgNum + ".png";
                break;
            case DOWN:
                tankImgPath += "D" + currentImgNum + ".png";
                break;
            case LEFT:
                tankImgPath += "L" + currentImgNum + ".png";
                break;
            case RIGHT:
                tankImgPath += "R" + currentImgNum + ".png";
                break;
            default:
        }
        tankImg = GameUtil.getImage(tankImgPath);
    }

    /**
     * 坦克向上行动
     */
    public void up() {
        if (hasNotObstacleAtUpwardDirection()) {
            y -= Constant.MOVING_PIXEL;
        }
    }

    // 向上移动中是否遇到障碍物
    private boolean hasNotObstacleAtUpwardDirection() {
        // 当前行的纵坐标的像素值
        int currentRowY = Constant.FRAME_BEGIN_Y + row * Constant.SPACING;
        // 当前坦克的实际纵坐标值
        int practicalY = this.y;

        if (currentRowY > practicalY) {
            row--;
        }

        boolean isOccupyThreeAtLine = isOccupyThreeAtLine();
        // 不超过边界
        if (this.y > Constant.FRAME_BEGIN_Y) {
            if (isOccupyThreeAtLine) {
                return (row > 0 && GameMap.map[row-1][line] == 0
                        && GameMap.map[row-1][line+1] == 0 && GameMap.map[row-1][line+2] == 0)
                        || currentRowY < practicalY;
            } else {
                // 如果数组前一行不为0，即前一行是障碍物，则需要
                return (row > 0 && GameMap.map[row - 1][line] == 0 && GameMap.map[row - 1][line + 1] == 0)
                        || currentRowY < practicalY;
            }
        }
        return false;
    }

    /**
     * 坦克向下行动
     */
    public void down() {
        if (hasNotObstacleAtDownwardDirection()) {
            y += Constant.MOVING_PIXEL;
        }
    }

    private boolean hasNotObstacleAtDownwardDirection() {
        int rowY = Constant.FRAME_BEGIN_Y + (row + 1) * Constant.SPACING;
        // 判断当前坦克在数组的哪行
        if (rowY <= this.y) {
            row++;
        }

        int practicalRow = this.row + 2;
        int currentRowY = Constant.FRAME_BEGIN_Y + practicalRow * Constant.SPACING;
        int practicalY = this.y + Constant.SPACING * 2;
        if (currentRowY < practicalY) {
            practicalRow++;
            currentRowY += Constant.SPACING;
        }

        boolean isOccupyThreeAtLine = isOccupyThreeAtLine();
        // 不超过边界
        if (practicalY < (Constant.FRAME_BEGIN_Y + Constant.GAME_FRAME_HEIGHT)) {
            if (isOccupyThreeAtLine) {
                return practicalRow <= 25 && GameMap.map[practicalRow][line] == 0
                        && GameMap.map[practicalRow][line+1] == 0 && GameMap.map[practicalRow][line+2] == 0
                        || currentRowY > practicalY;
            } else {
                return (practicalRow <= 25 && GameMap.map[practicalRow][line] == 0
                        && GameMap.map[practicalRow][line + 1] == 0) || currentRowY > practicalY;
            }
        }
        return false;
    }

    // 坦克在横坐标的位置上，是否占据了三个格子
    private boolean isOccupyThreeAtLine() {
        return (Constant.FRAME_BEGIN_X + line * Constant.SPACING) != this.x;
    }

    /**
     * 坦克向左行动
     */
    public void left() {
        if (hasNotObstacleAtLeftwardDirection()) {
            x -= Constant.MOVING_PIXEL;
        }
    }

    private boolean hasNotObstacleAtLeftwardDirection() {
        // 当前列的横坐标的像素值
        int currentLineX = Constant.FRAME_BEGIN_X + line * Constant.SPACING;
        // 当前坦克的实际横坐标值
        int practicalX = this.x;

        if (currentLineX > practicalX) {
            line--;
        }

        boolean isOccupyThreeAtRow = isOccupyThreeAtRow();
        // 不超过边界
        if (this.x > Constant.FRAME_BEGIN_X) {
            if (isOccupyThreeAtRow) {
                return (line > 0 && GameMap.map[row][line-1] == 0
                        && GameMap.map[row+1][line-1] == 0 && GameMap.map[row+2][line-1] == 0)
                        || currentLineX < practicalX;
            } else {
                // 如果数组前一行不为0，即前一行是障碍物，则需要
                return (line > 0 && GameMap.map[row][line-1] == 0 && GameMap.map[row+1][line-1] == 0)
                        || currentLineX < practicalX;
            }
        }
        return false;
    }

    /**
     * 坦克向右行动
     */
    public void right() {
        if (hasNotObstacleAtRightwardDirection()) {
            x += Constant.MOVING_PIXEL;
        }
    }

    private boolean hasNotObstacleAtRightwardDirection() {
        int lineX = Constant.FRAME_BEGIN_X + (line + 1) * Constant.SPACING;
        // 判断当前坦克在数组的哪行
        if (lineX <= this.x) {
            line++;
        }

        int practicalLine = this.line + 2;
        int currentLineX = Constant.FRAME_BEGIN_X + practicalLine * Constant.SPACING;
        int practicalX = this.x + Constant.SPACING * 2;
        if (currentLineX < practicalX) {
            practicalLine++;
            currentLineX += Constant.SPACING;
        }

        boolean isOccupyThreeAtRow = isOccupyThreeAtRow();
        // 不超过边界
        if (practicalX < (Constant.FRAME_BEGIN_X + Constant.GAME_FRAME_WIDTH)) {
            if (isOccupyThreeAtRow) {
                return practicalLine <= 25 && GameMap.map[row][practicalLine] == 0
                        && GameMap.map[row+1][practicalLine] == 0 && GameMap.map[row+2][practicalLine] == 0
                        || currentLineX > practicalX;
            } else {
                return practicalLine <= 25 && GameMap.map[row][practicalLine] == 0
                        && GameMap.map[row+1][practicalLine] == 0 || currentLineX > practicalX;
            }
        }
        return false;
    }

    private boolean isOccupyThreeAtRow() {
        return (Constant.FRAME_BEGIN_Y + row * Constant.SPACING) != this.y;
    }

    /**
     * 坦克攻击
     */
    public void attack() {

    }

    public List<Direction> getMovingDirection() {
        return movingDirection;
    }
}
