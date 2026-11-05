import javax.swing.*;
import java.awt.*;

/** Shared Windows 11-style look and input helpers used by every app. */
public final class UiKit {
    public static final Color ACCENT = new Color(0, 95, 184);
    public static final Color ACCENT_HOVER = new Color(0, 84, 166);
    public static final Color ACCENT_PRESSED = new Color(0, 70, 140);
    public static final Color SURFACE = new Color(243, 243, 243);
    public static final Color CARD = Color.WHITE;
    public static final Color TEXT = new Color(32, 32, 32);
    public static final Color TEXT_MUTED = new Color(96, 96, 96);
    public static final Color BORDER = new Color(225, 225, 225);
    public static final Font UI_FONT = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font UI_BOLD = new Font("Segoe UI Semibold", Font.PLAIN, 13);
    public static final Font MONO_FONT = new Font("Consolas", Font.PLAIN, 13);

    private UiKit() {
    }

    /** Rounded accent button. Changes color on hover and press. */
    public static JButton button(String text) {
        JButton b = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Color c = getModel().isPressed() ? ACCENT_PRESSED
                        : getModel().isRollover() ? ACCENT_HOVER : ACCENT;
                g2.setColor(c);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        b.setFont(UI_BOLD);
        b.setForeground(Color.WHITE);
        b.setContentAreaFilled(false);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
        b.setOpaque(false);
        b.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return b;
    }

    /** Input field. The hint is a gray example that disappears once you type. */
    public static JTextField field(String hint, int columns) {
        HintField f = new HintField(hint, columns);
        f.setFont(UI_FONT);
        f.setForeground(TEXT);
        f.setBackground(CARD);
        f.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180)),
                BorderFactory.createEmptyBorder(4, 8, 4, 8)));
        return f;
    }

    public static JLabel label(String text) {
        JLabel l = new JLabel(text);
        l.setFont(UI_FONT);
        l.setForeground(TEXT);
        return l;
    }

    public static JTextArea outputArea() {
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(MONO_FONT);
        area.setForeground(TEXT);
        area.setBackground(CARD);
        area.setMargin(new Insets(10, 12, 10, 12));
        return area;
    }

    /** Two-column form: label, component, label, component, ... */
    public static JPanel form(Object... pairs) {
        JPanel p = new JPanel(new GridLayout(0, 2, 12, 10));
        p.setBackground(CARD);
        for (int i = 0; i < pairs.length; i += 2) {
            p.add(label((String) pairs[i]));
            p.add((Component) pairs[i + 1]);
        }
        return p;
    }

    /** Standard app layout: input card on top, scrollable output below. */
    public static JPanel page(JComponent form, JComponent actions, JTextArea output) {
        JPanel card = new JPanel(new BorderLayout(0, 12));
        card.setBackground(CARD);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                BorderFactory.createEmptyBorder(14, 14, 14, 14)));
        card.add(form, BorderLayout.CENTER);
        card.add(actions, BorderLayout.SOUTH);

        JScrollPane scroll = new JScrollPane(output);
        scroll.setBorder(BorderFactory.createLineBorder(BORDER));

        JPanel p = new JPanel(new BorderLayout(0, 12));
        p.setBackground(SURFACE);
        p.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        p.add(card, BorderLayout.NORTH);
        p.add(scroll, BorderLayout.CENTER);
        return p;
    }

    /** Button row to pass as the actions argument of page(). */
    public static JPanel actionRow(JButton... buttons) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        row.setBackground(CARD);
        for (JButton b : buttons) row.add(b);
        return row;
    }

    public static void show(JTextArea area, String text) {
        area.setText(text);
        area.setCaretPosition(0);
    }

    public static void error(Component parent, String message) {
        String text = (message == null || message.contains("For input string"))
                ? "Mali ang format ng input. Tingnan ang halimbawa sa bawat field."
                : message;
        JOptionPane.showMessageDialog(parent, text, "Invalid input", JOptionPane.ERROR_MESSAGE);
    }

    /** "P1, P2, P3" -> {"P1","P2","P3"} */
    public static String[] names(String text) {
        requireText(text);
        return text.trim().split("\\s*,\\s*");
    }

    /** "1, 2 3" -> {1,2,3} */
    public static int[] ints(String text) {
        requireText(text);
        String[] parts = text.trim().split("[,\\s]+");
        int[] out = new int[parts.length];
        for (int i = 0; i < parts.length; i++) out[i] = Integer.parseInt(parts[i]);
        return out;
    }

    /** "1 2; 3 4" -> {{1,2},{3,4}} */
    public static int[][] matrix(String text) {
        requireText(text);
        String[] rows = text.trim().split("\\s*;\\s*");
        int[][] out = new int[rows.length][];
        for (int i = 0; i < rows.length; i++) out[i] = ints(rows[i]);
        return out;
    }

    private static void requireText(String text) {
        if (text == null || text.trim().isEmpty())
            throw new IllegalArgumentException("May field na walang laman.");
    }
}