package yang.entity;

import yang.utils.Constant;
import yang.utils.GameUtil;

import java.awt.*;

/**
 * 开始界面的选择坦克类
 */
public class Selector {
    private int x;
    private int y;
    private Image image;
    private Option option;
    // 是否被销毁，即进入选关界面
    private boolean isRemoved;


    public enum Option {
        ONE_PLAYER, TWO_PLAYERS, CONSTRUCTION
    }

    public Selector() {}

    public Selector(String imgPath) {
        x = Constant.SELECTOR_X;
        y = Constant.SELECTOR_Y;
        option = Option.ONE_PLAYER;
        isRemoved = false;
        this.image = GameUtil.getImage(imgPath);
    }

    public void paintSelf(Graphics g) {
        g.drawImage(image, x, y, null);
    }

    public void up() {
        if (option.equals(Option.ONE_PLAYER)) {
            y += 2 * Constant.SEPARATION_DISTANCE;
            option = Option.CONSTRUCTION;
        } else {
            y -= Constant.SEPARATION_DISTANCE;
            option = option.equals(Option.TWO_PLAYERS) ? Option.ONE_PLAYER : Option.TWO_PLAYERS;
        }


    }

    public void down() {
        if (option.equals(Option.CONSTRUCTION)) {
            y -= 2 * Constant.SEPARATION_DISTANCE;
            option = Option.ONE_PLAYER;
        } else {
            y += Constant.SEPARATION_DISTANCE;
            option = option.equals(Option.ONE_PLAYER) ? Option.TWO_PLAYERS : Option.CONSTRUCTION;
        }
    }

    public void select() {
        isRemoved = true;
    }

    public Option getOption() {
        return option;
    }

    public boolean isRemoved() {
        return isRemoved;
    }


}
