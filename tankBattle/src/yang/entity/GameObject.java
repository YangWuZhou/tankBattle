package yang.entity;

import yang.utils.GameUtil;

import java.awt.*;

public class GameObject {
    protected Image gameImg;
    protected int x;
    protected int y;

    public GameObject(String filePath, int x, int y) {
        this.gameImg = GameUtil.getImage(filePath);
        this.x = x * GameUtil.MOVING_PIXELS + GameUtil.FRAME_BEGIN_X;
        this.y = y * GameUtil.MOVING_PIXELS + GameUtil.FRAME_BEGIN_Y;
    }

    public void paintSelf(Graphics g) {
        g.drawImage(gameImg, x, y, null);
    }

    public int getRow() {
        return (y - GameUtil.FRAME_BEGIN_Y) / GameUtil.MOVING_PIXELS;
    }

    public int getLine() {
        return (x - GameUtil.FRAME_BEGIN_X) / GameUtil.MOVING_PIXELS;
    }
}
