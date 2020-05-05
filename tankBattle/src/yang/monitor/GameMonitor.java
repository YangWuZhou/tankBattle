package yang.monitor;

import yang.entity.Tank;
import yang.type.Direction;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

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
        List<Direction> movingDirection = tank.getMovingDirection();
        Direction currentDirection = null;
        if (!movingDirection.isEmpty()) {
            currentDirection = movingDirection.get(movingDirection.size()-1);
        }
        switch (keyWord) {
            case "W":
                if (!Direction.UP.equals(currentDirection)) {
                    movingDirection.add(Direction.UP);
                }
                break;
            case "S":
                if (!Direction.DOWN.equals(currentDirection)) {
                    movingDirection.add(Direction.DOWN);
                }
                break;
            case "A":
                if (!Direction.LEFT.equals(currentDirection)) {
                    movingDirection.add(Direction.LEFT);
                }
                break;
            case "D":
                if (!Direction.RIGHT.equals(currentDirection)) {
                    movingDirection.add(Direction.RIGHT);
                }
                break;
            case "J": tank.attack();
                break;
            default:
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        String keyWord = KeyEvent.getKeyText(e.getKeyCode());
        List<Direction> movingDirection = tank.getMovingDirection();
        Direction removedDirection = null;
        switch (keyWord) {
            case "W":
                removedDirection = Direction.UP;
                break;
            case "S":
                removedDirection = Direction.DOWN;
                break;
            case "A":
                removedDirection = Direction.LEFT;
                break;
            case "D":
                removedDirection = Direction.RIGHT;
                break;
            default:
        }
        int removedIndex = movingDirection.lastIndexOf(removedDirection);
        if (removedIndex != -1) {
            movingDirection.remove(removedIndex);
        }
    }
}
