package yang.monitor;

import yang.entity.Selector;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 开始界面的选择监视器
 */
public class GameSelectMonitor implements KeyListener {
    private Selector selector;

    public GameSelectMonitor(Selector selector) {
        this.selector = selector;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // 获取按键
        String keyWord = KeyEvent.getKeyText(e.getKeyCode());
        switch (keyWord) {
            case "W":
                selector.up();
                break;
            case "S":
                selector.down();
                break;
            case "J":
                selector.select();
                break;
            default:
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
