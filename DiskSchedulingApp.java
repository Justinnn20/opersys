import javax.swing.*;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Disk scheduling: FCFS, SSTF, SCAN, C-SCAN and LOOK with head-movement totals. */
public class DiskSchedulingApp extends JPanel {
    private final JTextField requestField = UiKit.field("98, 183, 37, 122, 14, 124, 65, 67", 30);
    private final JTextField headField = UiKit.field("53", 6);
    private final JTextField maxField = UiKit.field("199", 6);
    private final JComboBox<String> dirBox = new JComboBox<>(new String[]{"Up", "Down"});
    private final JComboBox<String> algoBox = new JComboBox<>(new String[]{"FCFS", "SSTF", "SCAN", "C-SCAN", "LOOK"});
    private final JTextArea output = UiKit.outputArea();

    public DiskSchedulingApp() {
        super(new BorderLayout());
        JButton run = UiKit.button("Schedule");
        run.addActionListener(e -> schedule());
        JPanel form = UiKit.form(
                "Request queue (cylinders)", requestField,
                "Initial head position", headField,
                "Max cylinder number", maxField,
                "Head direction", dirBox,
                "Algorithm", algoBox);
        add(UiKit.page(form, UiKit.actionRow(run), output), BorderLayout.CENTER);
    }

    private void schedule() {
        try {
            int[] requests = UiKit.ints(requestField.getText());
            int head = Integer.parseInt(headField.getText().trim());
            int maxCyl = Integer.parseInt(maxField.getText().trim());
            boolean up = dirBox.getSelectedItem().equals("Up");
            String algo = (String) algoBox.getSelectedItem();

            for (int r : requests) {
                if (r < 0 || r > maxCyl) throw new IllegalArgumentException("Request " + r + " is outside 0.." + maxCyl);
            }
            if (head < 0 || head > maxCyl) throw new IllegalArgumentException("Head must be between 0 and " + maxCyl);

            List<Integer> seq = order(requests, head, maxCyl, up, algo);

            StringBuilder sb = new StringBuilder("Algorithm: " + algo + "\n\n");
            int cur = head;
            int total = 0;
            for (int s : seq) {
                int moved = Math.abs(s - cur);
                total += moved;
                sb.append(String.format("  %3d -> %3d   (%d cylinders)%n", cur, s, moved));
                cur = s;
            }
            sb.append("\nService order: ").append(head);
            for (int s : seq) sb.append(" -> ").append(s);
            sb.append("\n\nTotal head movement: ").append(total).append(" cylinders\n");
            if (algo.equals("C-SCAN")) {
                sb.append("Note: the return jump from the end of the disk to the start is counted.\n");
            }
            UiKit.show(output, sb.toString());
        } catch (RuntimeException ex) {
            UiKit.error(this, ex.getMessage());
        }
    }

    private static List<Integer> order(int[] requests, int head, int maxCyl, boolean up, String algo) {
        List<Integer> upper = new ArrayList<>();   // requests at or above the head
        List<Integer> lower = new ArrayList<>();   // requests below the head
        for (int r : requests) {
            if (r >= head) upper.add(r);
            else lower.add(r);
        }
        Collections.sort(upper);
        Collections.sort(lower);
        List<Integer> upperDesc = new ArrayList<>(upper);
        Collections.reverse(upperDesc);
        List<Integer> lowerDesc = new ArrayList<>(lower);
        Collections.reverse(lowerDesc);

        List<Integer> seq = new ArrayList<>();
        if (algo.equals("FCFS")) {
            for (int r : requests) seq.add(r);
        } else if (algo.equals("SSTF")) {
            List<Integer> remaining = new ArrayList<>();
            for (int r : requests) remaining.add(r);
            int cur = head;
            while (!remaining.isEmpty()) {
                int best = remaining.get(0);
                for (int r : remaining) {
                    if (Math.abs(r - cur) < Math.abs(best - cur)) best = r;
                }
                seq.add(best);
                remaining.remove(Integer.valueOf(best));
                cur = best;
            }
        } else if (algo.equals("SCAN")) {
            if (up) {
                seq.addAll(upper); seq.add(maxCyl); seq.addAll(lowerDesc); seq.add(0);
            } else {
                seq.addAll(lowerDesc); seq.add(0); seq.addAll(upper); seq.add(maxCyl);
            }
        } else if (algo.equals("C-SCAN")) {
            if (up) {
                seq.addAll(upper); seq.add(maxCyl); seq.add(0); seq.addAll(lower);
            } else {
                seq.addAll(lowerDesc); seq.add(0); seq.add(maxCyl); seq.addAll(upperDesc);
            }
        } else { // LOOK: like SCAN but reverses at the last request instead of the disk edge
            if (up) {
                seq.addAll(upper); seq.addAll(lowerDesc);
            } else {
                seq.addAll(lowerDesc); seq.addAll(upper);
            }
        }
        return seq;
    }
}