import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/** A desktop shortcut: a colored tile with a badge and a label. Double-click to open. */
public class DesktopIcon extends JPanel {
    private final String badge;
    private final Color color;

    public DesktopIcon(String title, String badge, Color color, Runnable onOpen) {
        this.badge = badge;
        this.color = color;
        setLayout(new BorderLayout(0, 4));
        setOpaque(false);

        JLabel name = new JLabel(title, SwingConstants.CENTER);
        name.setForeground(Color.WHITE);
        name.setFont(UiKit.UI_FONT);
        add(name, BorderLayout.SOUTH);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) onOpen.run();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int size = 52;
        int x = (getWidth() - size) / 2;
        g2.setColor(color);
        g2.fillRoundRect(x, 4, size, size, 14, 14);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Segoe UI", Font.BOLD, 15));
        FontMetrics fm = g2.getFontMetrics();
        int textX = x + (size - fm.stringWidth(badge)) / 2;
        int textY = 4 + (size + fm.getAscent() - fm.getDescent()) / 2;
        g2.drawString(badge, textX, textY);
        g2.dispose();
    }
}