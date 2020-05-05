package yang.panel;

import yang.entity.Selector;
import yang.utils.Constant;
import yang.utils.FilePrefix;
import yang.utils.GameUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * 选关游戏面板
 */
public class SelectLevelInterfacePanel extends JPanel {
    private int level;
    private Selector selector;
    private JPanel panel;
    private CardLayout card;
    private KeyListener listener;
    // 是否已有监听器
    private boolean hasListener = false;

    public SelectLevelInterfacePanel(JPanel panel, CardLayout card) {
        level = 1;
        selector = new Selector();
        this.panel = panel;
        this.card = card;
        listener = new SelectLevelMonitor();
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (!selector.isRemoved()) {
            g.drawImage(GameUtil.getImage(FilePrefix.INTERFACE_IMG + "selectLevelPage.png"), 0, 0, null);
            g.drawImage(GameUtil.getImage(FilePrefix.NUMBER_IMG + "number_" + level + ".png"),
                    Constant.NUMBER_X, Constant.NUMBER_Y, null);
            if (!hasListener) {
                panel.addKeyListener(listener);
                hasListener = true;
            }
        } else {
            panel.removeKeyListener(listener);
            hasListener = false;
            card.show(panel, "gameInterface");
        }
    }

    private class SelectLevelMonitor implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e) {
            String keyWord = KeyEvent.getKeyText(e.getKeyCode());
            switch (keyWord) {
                case "W":
                    level = ++level > Constant.MAX_LEVEL ? 1 : level;
                    break;
                case "S":
                    level = --level < 1 ? Constant.MAX_LEVEL : level;
                    break;
                case "J":
                    selector.select();
                    break;
                default:
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {}
    }
}
