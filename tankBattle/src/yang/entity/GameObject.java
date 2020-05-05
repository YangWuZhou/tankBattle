package yang.entity;

import yang.type.Direction;
import yang.utils.Constant;
import yang.utils.GameUtil;

import java.awt.*;

public class GameObject {
    protected Image gameImg;
    protected int x;
    protected int y;
    protected Direction direction;

    public GameObject() {
    }

    public GameObject(String filePath, int x, int y) {
        this(GameUtil.getImage(filePath), x, y);

    }

    public GameObject(Image image, int x, int y) {
        this.gameImg = image;
        this.x = x;
        this.y = y;
    }

    public void paintSelf(Graphics g) {

    }

    // 获取行，即二维数组的第一个数字
    public int getRow() {
        int row = y;
        if (Direction.DOWN.equals(direction) || Direction.LEFT.equals(direction)) {
            row++;
        }
        return row;
    }

    // 获取列，即二维数组的第二个数字
    public int getLine() {
        int line = x;
        if (Direction.DOWN.equals(direction) || Direction.RIGHT.equals(direction)) {
            line++;
        }
        return line;
    }
}
