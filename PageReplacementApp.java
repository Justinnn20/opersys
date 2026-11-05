import javax.swing.*;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

/** Page replacement: FIFO, LRU and Optimal (OPT) with a step-by-step table. */
public class PageReplacementApp extends JPanel {
    private final JTextField refField = UiKit.field("7 0 1 2 0 3 0 4 2 3 0 3 2", 30);
    private final JTextField framesField = UiKit.field("3", 6);
    private final JComboBox<String> algoBox = new JComboBox<>(new String[]{"FIFO", "LRU", "OPT"});
    private final JTextArea output = UiKit.outputArea();

    public PageReplacementApp() {
        super(new BorderLayout());
        JButton run = UiKit.button("Simulate");
        run.addActionListener(e -> simulate());
        JPanel form = UiKit.form(
                "Reference string", refField,
                "Number of frames", framesField,
                "Algorithm", algoBox);
        add(UiKit.page(form, UiKit.actionRow(run), output), BorderLayout.CENTER);
    }

    private void simulate() {
        try {
            int[] refs = UiKit.ints(refField.getText());
            int capacity = Integer.parseInt(framesField.getText().trim());
            if (capacity <= 0) throw new IllegalArgumentException("Frames must be greater than 0.");
            String algo = (String) algoBox.getSelectedItem();
            UiKit.show(output, run(refs, capacity, algo));
        } catch (RuntimeException ex) {
            UiKit.error(this, ex.getMessage());
        }
    }

    private static String run(int[] refs, int capacity, String algo) {
        List<Integer> frames = new ArrayList<>();
        int hits = 0;
        int faults = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("Algorithm: ").append(algo).append(", frames: ").append(capacity).append("\n\n");
        sb.append(String.format("%-6s| %-24s| %s%n", "Ref", "Frames after step", "Result"));
        sb.append("--------------------------------------------------\n");

        for (int i = 0; i < refs.length; i++) {
            int page = refs[i];
            String result;
            if (frames.contains(page)) {
                hits++;
                result = "HIT";
                if (algo.equals("LRU")) {          // move to most-recently-used end
                    frames.remove(Integer.valueOf(page));
                    frames.add(page);
                }
            } else {
                faults++;
                if (frames.size() == capacity) {
                    int idx = algo.equals("OPT") ? optimalVictim(frames, refs, i) : 0;
                    int victim = frames.remove(idx);
                    result = "FAULT, replaced " + victim;
                } else {
                    result = "FAULT";
                }
                frames.add(page);
            }
            sb.append(String.format("%-6d| %-24s| %s%n", page, frames, result));
        }

        sb.append("\nPage faults: ").append(faults).append("\n");
        sb.append("Page hits:   ").append(hits).append("\n");
        sb.append(String.format("Hit ratio:  %.2f%%%n", hits * 100.0 / refs.length));
        return sb.toString();
    }

    /** OPT: evict the page whose next use is farthest away (or never used again). */
    private static int optimalVictim(List<Integer> frames, int[] refs, int now) {
        int victim = 0;
        int farthest = -1;
        for (int idx = 0; idx < frames.size(); idx++) {
            int page = frames.get(idx);
            int nextUse = Integer.MAX_VALUE;
            for (int j = now + 1; j < refs.length; j++) {
                if (refs[j] == page) {
                    nextUse = j;
                    break;
                }
            }
            if (nextUse > farthest) {
                farthest = nextUse;
                victim = idx;
            }
        }
        return victim;
    }
}