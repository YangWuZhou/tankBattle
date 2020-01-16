package yang.utils;

public class Constant {
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

    public static final String IMAGE_SUFFIX = ".gif"; // 图片后缀
}
