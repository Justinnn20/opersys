import javax.swing.*;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

/** Fragmentation: variable-partition allocation (First/Best/Worst Fit) and fragmentation measures. */
public class FragmentationApp extends JPanel {
    private final JTextField holesField = UiKit.field("100, 500, 200, 300, 600", 26);
    private final JTextField processField = UiKit.field("212, 417, 112, 426", 26);
    private final JTextField frameField = UiKit.field("100", 8);
    private final JComboBox<String> algoBox = new JComboBox<>(new String[]{"First Fit", "Best Fit", "Worst Fit"});
    private final JTextArea output = UiKit.outputArea();

    public FragmentationApp() {
        super(new BorderLayout());
        JButton run = UiKit.button("Allocate");
        run.addActionListener(e -> allocate());
        JPanel form = UiKit.form(
                "Free holes (KB)", holesField,
                "Process sizes (KB)", processField,
                "Frame size for paging check (KB)", frameField,
                "Algorithm", algoBox);
        add(UiKit.page(form, UiKit.actionRow(run), output), BorderLayout.CENTER);
    }

    private void allocate() {
        try {
            List<Integer> holes = new ArrayList<>();
            for (int h : UiKit.ints(holesField.getText())) holes.add(h);
            int[] procs = UiKit.ints(processField.getText());
            int frame = Integer.parseInt(frameField.getText().trim());
            if (frame <= 0) throw new IllegalArgumentException("Frame size must be greater than 0.");
            String algo = (String) algoBox.getSelectedItem();

            StringBuilder sb = new StringBuilder("Algorithm: " + algo + "\n\n");
            int failed = 0;
            int internal = 0;
            for (int i = 0; i < procs.length; i++) {
                int size = procs[i];
                String name = "P" + (i + 1);
                int idx = findHole(holes, size, algo);
                if (idx < 0) {
                    failed++;
                    sb.append(String.format("%s (%d KB): FAILED, no single hole is big enough%n", name, size));
                    continue;
                }
                int hole = holes.get(idx);
                int leftover = hole - size;
                if (leftover > 0) holes.set(idx, leftover);
                else holes.remove(idx);
                sb.append(String.format("%s (%d KB): placed in %d KB hole, leftover %d KB%n",
                        name, size, hole, leftover));

                int frames = (size + frame - 1) / frame;
                internal += frames * frame - size;
            }

            int totalFree = 0;
            int largest = 0;
            for (int h : holes) {
                totalFree += h;
                largest = Math.max(largest, h);
            }
            sb.append("\nRemaining free holes: ").append(holes).append("\n\n");
            sb.append("External fragmentation: ").append(totalFree).append(" KB free in ")
                    .append(holes.size()).append(" pieces (largest piece: ").append(largest).append(" KB)\n");
            sb.append("Failed allocations: ").append(failed).append("\n");
            sb.append("Internal fragmentation if paged with ").append(frame).append(" KB frames: ")
                    .append(internal).append(" KB wasted inside frames\n");
            UiKit.show(output, sb.toString());
        } catch (RuntimeException ex) {
            UiKit.error(this, ex.getMessage());
        }
    }

    /** Returns the index of the chosen hole, or -1 if none fits. */
    private static int findHole(List<Integer> holes, int size, String algo) {
        int chosen = -1;
        for (int i = 0; i < holes.size(); i++) {
            int h = holes.get(i);
            if (h < size) continue;
            if (chosen == -1) {
                chosen = i;
                if (algo.equals("First Fit")) return i;
                continue;
            }
            int current = holes.get(chosen);
            if (algo.equals("Best Fit") && h < current) chosen = i;
            if (algo.equals("Worst Fit") && h > current) chosen = i;
        }
        return chosen;
    }
}