import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.*;
import java.beans.PropertyVetoException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Supplier;

/** Main window: desktop with app icons, Start menu, taskbar and clock. */
public class VirtualDesktop extends JFrame {
    private final JDesktopPane desktop;
    private final JPanel taskbarTabs = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 4));
    private final JLabel clock = new JLabel();
    private final JPopupMenu startMenu = new JPopupMenu();
    private int iconCount = 0;
    private int windowOffset = 0;

    public VirtualDesktop() {
        super("VirtualOS");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200, 760);
        setLocationRelativeTo(null);

        desktop = new JDesktopPane() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setPaint(new GradientPaint(0, 0, new Color(18, 110, 190), 0, getHeight(), new Color(5, 35, 80)));
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        add(desktop, BorderLayout.CENTER);
        add(buildTaskbar(), BorderLayout.SOUTH);

        registerApp("CPU Scheduling", "CPU", new Color(0, 120, 215), CpuSchedulingApp::new);
        registerApp("Deadlock", "DL", new Color(200, 60, 60), DeadlockApp::new);
        registerApp("Paging", "PG", new Color(40, 150, 80), PagingApp::new);
        registerApp("Page Replace", "PR", new Color(140, 80, 200), PageReplacementApp::new);
        registerApp("Segmentation", "SEG", new Color(230, 140, 20), SegmentationApp::new);
        registerApp("Fragmentation", "FRG", new Color(0, 150, 150), FragmentationApp::new);
        registerApp("Disk Scheduling", "DSK", new Color(120, 90, 60), DiskSchedulingApp::new);
        registerApp("Multiprog", "MP", new Color(90, 90, 110), MultiprogrammingApp::new);

        new Timer(1000, e -> updateClock()).start();
        updateClock();
    }

    private void registerApp(String title, String badge, Color color, Supplier<JPanel> factory) {
        int column = iconCount / 6;
        int row = iconCount % 6;

        DesktopIcon icon = new DesktopIcon(title, badge, color, () -> openApp(title, factory.get()));
        icon.setBounds(20 + column * 100, 20 + row * 100, 90, 96);
        desktop.add(icon, JLayeredPane.DEFAULT_LAYER);

        JMenuItem item = new JMenuItem(title);
        item.addActionListener(e -> openApp(title, factory.get()));
        startMenu.add(item);
        iconCount++;
    }

    private JPanel buildTaskbar() {
        JButton start = new JButton("Start");
        start.setFont(UiKit.UI_FONT);
        start.setBackground(UiKit.ACCENT);
        start.setForeground(Color.WHITE);
        start.setFocusPainted(false);
        start.addActionListener(e -> startMenu.show(start, 0, -(startMenu.getComponentCount() * 26 + 8)));

        JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 4));
        left.setOpaque(false);
        left.add(start);

        taskbarTabs.setOpaque(false);

        clock.setForeground(Color.WHITE);
        clock.setFont(UiKit.UI_FONT);
        clock.setBorder(BorderFactory.createEmptyBorder(0, 14, 0, 14));

        JPanel bar = new JPanel(new BorderLayout());
        bar.setBackground(new Color(32, 32, 32));
        bar.setPreferredSize(new Dimension(0, 46));
        bar.add(left, BorderLayout.WEST);
        bar.add(taskbarTabs, BorderLayout.CENTER);
        bar.add(clock, BorderLayout.EAST);
        return bar;
    }

    /** Opens an app in its own window and adds a taskbar tab for it. */
    public void openApp(String title, JPanel content) {
        JInternalFrame win = new JInternalFrame(title, true, true, true, true);
        win.setContentPane(content);
        win.setSize(780, 540);
        win.setLocation(40 + windowOffset, 30 + windowOffset);
        windowOffset = (windowOffset + 30) % 240;
        desktop.add(win);
        win.setVisible(true);

        JButton tab = new JButton(title);
        tab.setFont(UiKit.UI_FONT);
        tab.addActionListener(e -> restore(win));
        taskbarTabs.add(tab);
        taskbarTabs.revalidate();
        taskbarTabs.repaint();

        win.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                taskbarTabs.remove(tab);
                taskbarTabs.revalidate();
                taskbarTabs.repaint();
            }
        });
        restore(win);
    }

    private void restore(JInternalFrame win) {
        try {
            win.setIcon(false);
            win.setSelected(true);
        } catch (PropertyVetoException ignored) {
        }
        win.toFront();
    }

    private void updateClock() {
        Date now = new Date();
        clock.setText("<html><center>" + new SimpleDateFormat("h:mm a").format(now)
                + "<br>" + new SimpleDateFormat("M/d/yyyy").format(now) + "</center></html>");
    }
}