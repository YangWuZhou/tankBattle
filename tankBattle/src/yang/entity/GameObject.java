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
        g.drawImage(gameImg, x, y, null);
    }

    public int getRow() {
        int row = (y - Constant.FRAME_BEGIN_Y) / Constant.MOVING_PIXELS;
        if (Direction.DOWN.equals(direction) || Direction.LEFT.equals(direction)) {
            row++;
        }
        return row;
    }

    public int getLine() {
        int line = (x - Constant.FRAME_BEGIN_X) / Constant.MOVING_PIXELS;
        if (Direction.DOWN.equals(direction) || Direction.RIGHT.equals(direction)) {
            line++;
        }
        return line;
    }
}
