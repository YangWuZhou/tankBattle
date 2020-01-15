package yang.utils;

import yang.type.Direction;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class GameUtil {
    // 界面所在屏幕位置
    public static final int LOCATION_X = 100;
    public static final int LOCATION_Y = 20;

    public static final int MOVING_PIXELS = 30; // 每次移动的像素
    public static final int FRAME_WIDTH = 1300; // 程序宽度
    public static final int FRAME_HEIGHT = 900; // 程序高度

    public static final int REAL_FRAME_WIDTH = 2 * MOVING_PIXELS * 13; // 游戏界面宽度
    public static final int REAL_FRAME_HEIGHT = 2 * MOVING_PIXELS * 13; // 游戏界面高度

    public static final int FRAME_BEGIN_X = (FRAME_WIDTH - REAL_FRAME_WIDTH) / 2;  // 界面起始横坐标
    public static final int FRAME_END_X = FRAME_BEGIN_X + REAL_FRAME_WIDTH; // 界面终止横坐标
    public static final int FRAME_BEGIN_Y = (FRAME_HEIGHT - REAL_FRAME_HEIGHT) / 2; // 界面起始纵坐标
    public static final int FRAME_END_Y = FRAME_BEGIN_Y + REAL_FRAME_HEIGHT; // 界面终止纵坐标

    public static final String IMAGE_SUFFIX = ".gif";

    public static Image getImage(String filePath) {
        URL url = GameUtil.class.getClassLoader().getResource(filePath);
        assert url != null;
        return new ImageIcon(url).getImage();

    }

    public static Direction judgeDirection(String arg) {
        if (arg.contains("U")) {
            return Direction.UP;
        } else if (arg.contains("D")) {
            return Direction.DOWN;
        } else if (arg.contains("L")) {
            return Direction.LEFT;
        } else {
            return Direction.RIGHT;
        }
    }
}
