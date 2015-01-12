package View.MainFrame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 * Panel mit einem Schließbutton mit Hovereffekt
 *
 * @author tobias
 */
public class TabButtonComponent extends JPanel {

    private final JTabbedPane tp;

    public TabButtonComponent(final JTabbedPane tp) {
        //unset default FlowLayout' gaps
        super(new FlowLayout(FlowLayout.LEFT, 0, 0));
        if (tp == null) {
            throw new NullPointerException();
        }
        this.tp = tp;
        setOpaque(false);

        JLabel tabLabel = new JLabel() {
            //Beschriftung des Panes wird geladen
            public String getText() {
                int i = tp.indexOfTabComponent(TabButtonComponent.this);
                if (i != -1) {
                    return tp.getTitleAt(i);
                }
                return null;
            }
        };

        add(tabLabel);
        tabLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        JButton tabButton = new TabButton();
        add(tabButton);
        setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
    }

    /**
     * Der eigentliche Button wir mit seinen Eigenschaften hier definiert
     */
    private class TabButton extends JButton implements ActionListener {

        public TabButton() {
            int size = 12;
            setPreferredSize(new Dimension(size, size));
            setUI(new BasicButtonUI());
            setContentAreaFilled(false);
            setFocusable(false);
            setBorder(BorderFactory.createEtchedBorder());
            setBorderPainted(false);
            addMouseListener(btMouseListener);
            setRolloverEnabled(true);
            //Close the proper tab by clicking the button
            addActionListener(this);
        }
        /**
         * schließoperation des Tabs
         * @param e Event
         */
        public void actionPerformed(ActionEvent e) {
            int i = tp.indexOfTabComponent(TabButtonComponent.this);
            if (i != -1) {
                tp.remove(i);
            }
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D drawer = (Graphics2D) g.create();
            if (getModel().isPressed()) {
                drawer.translate(1, 1);
            }
            drawer.setStroke(new BasicStroke(2));
            drawer.setColor(Color.BLACK);
            if (getModel().isRollover()) {
                drawer.setColor(Color.BLACK);
            }
            int x = 12;
            drawer.drawLine(x, x, getWidth() - x - 1, getHeight() - x - 1);
            drawer.drawLine(getWidth() - x - 1, x, x, getHeight() - x - 1);
            drawer.dispose();
        }
    }

    private final static MouseListener btMouseListener = new MouseAdapter() {
        // Bei gedrückter Maus wird der Rand des Buttons angezeigt
        public void mouseEntered(MouseEvent e) {
            Component component = e.getComponent();
            if (component instanceof AbstractButton) {
                AbstractButton button = (AbstractButton) component;
                button.setBorderPainted(true);
            }
        }

        // Effekt wird angezeigt

        public void mouseExited(MouseEvent e) {
            Component component = e.getComponent();
            if (component instanceof AbstractButton) {
                AbstractButton button = (AbstractButton) component;
                button.setBorderPainted(false);
            }
        }

    };

}
