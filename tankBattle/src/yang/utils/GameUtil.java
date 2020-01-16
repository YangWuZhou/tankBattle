package yang.utils;

import yang.type.Direction;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class GameUtil {
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
