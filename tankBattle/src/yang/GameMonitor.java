package yang;

import yang.entity.Tank;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 按键监听类
 */
public class GameMonitor implements KeyListener {
    private Tank tank;

    public GameMonitor(Tank tank) {
        this.tank = tank;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        // 获取按键
        String keyWord = KeyEvent.getKeyText(e.getKeyCode());
        switch (keyWord) {
            case "W": tank.up();
                break;
            case "S": tank.down();
                break;
            case "A": tank.left();
                break;
            case "D": tank.right();
                break;
            case "J": tank.attack();
                break;
            default:
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
