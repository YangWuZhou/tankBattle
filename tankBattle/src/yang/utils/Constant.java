package yang.utils;

public class Constant {
    // 程序所在屏幕位置
    public static final int LOCATION_X = 400;
    public static final int LOCATION_Y = 100;

    public static final int FRAME_WIDTH = 616; // 程序宽度
    public static final int FRAME_HEIGHT = 563; // 程序高度
    public static final int MOVING_PIXEL = 1;  // 每次移动的像素


    public static final int SPACING = 16; // 一个格子的宽度
    public static final int GAME_FRAME_WIDTH = SPACING * 26; // 游戏界面宽度
    public static final int GAME_FRAME_HEIGHT = SPACING * 26; // 游戏界面高度
    public static final int FRAME_BEGIN_X = (FRAME_WIDTH - GAME_FRAME_WIDTH) / 2 - 10;  // 游戏界面起始横坐标
    public static final int FRAME_BEGIN_Y = (FRAME_HEIGHT - GAME_FRAME_HEIGHT) / 2 - 20; // 游戏界面起始纵坐标

    public static final int SELECTOR_X = 150;  // 选择坦克的横坐标
    public static final int SELECTOR_Y = 291;  // 选择坦克的纵坐标
    public static final int SEPARATION_DISTANCE = 38;  // 两选项之间的距离

    public static final int NUMBER_X = 360;  // 选关时，数字的横坐标
    public static final int NUMBER_Y = 243;  // 选关时，数字的纵坐标
    public static final int MAX_LEVEL = 3;  // 最大关卡

    public static final int PLAYER1_LIEF_X = 556;
    public static final int PLAYER1_LIEF_Y = 323;
    public static final int CURRENT_LEVEL_X = PLAYER1_LIEF_X;
    public static final int CURRENT_LEVEL_Y = 428;

    public static final int MAX_POSITION = 25; // 坐标最大位置




    public static final String IMAGE_SUFFIX = ".gif"; // 图片后缀
}
