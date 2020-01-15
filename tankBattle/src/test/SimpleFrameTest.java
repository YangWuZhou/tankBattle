package test;

import javax.swing.*;
import java.awt.*;

public class SimpleFrameTest {
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 300;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            SimpleFrame frame = new SimpleFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setUndecorated(true); // 关闭所有框架装饰
            frame.setVisible(true);
            frame.setLocation(FRAME_WIDTH, FRAME_HEIGHT); //窗口的位置
            frame.setTitle("坦克大战");
        });
    }
}

class SimpleFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 500;
    private static final int DEFAULT_HEIGHT = 400;

    public SimpleFrame() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    private void getScreenSize() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        System.out.println(screenSize.width);
        System.out.println(screenSize.height);
    }

    //设置框架图标
    private void setFrameImg() {
        Image image = new ImageIcon("").getImage();
        setIconImage(image);
    }
}
