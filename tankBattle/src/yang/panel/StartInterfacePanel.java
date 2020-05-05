package yang.panel;

import yang.entity.Selector;
import yang.monitor.GameSelectMonitor;
import yang.utils.FilePrefix;
import yang.utils.GameUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

/**
 * 游戏开始面板
 */

public class StartInterfacePanel extends JPanel {
    private Selector selector = new Selector(FilePrefix.ELEMENT_IMG +"selector.png");
    private JPanel panel;
    private CardLayout card;
    private KeyListener listener;
    // 是否已有监听器
    private boolean hasListener = false;

    public StartInterfacePanel(JPanel panel, CardLayout card) {
        this.panel = panel;
        this.card = card;
        listener = new GameSelectMonitor(selector);
        panel.setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (!selector.isRemoved()) {
            g.drawImage(GameUtil.getImage(FilePrefix.INTERFACE_IMG + "startPage.png"), 0, 0, null);
            selector.paintSelf(g);
            if (!hasListener) {
                panel.addKeyListener(new GameSelectMonitor(selector));
                hasListener = true;
            }
        } else if (Selector.Option.CONSTRUCTION.equals(selector.getOption())) {
            panel.removeKeyListener(listener);
            hasListener = false;
        } else {
            panel.removeKeyListener(listener);
            hasListener = false;
            card.show(panel, "selectLevelInterface");
        }
    }
}
