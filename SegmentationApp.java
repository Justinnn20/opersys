import javax.swing.*;
import java.awt.BorderLayout;

/** Segmentation: translates (segment, offset) pairs using a base/limit segment table. */
public class SegmentationApp extends JPanel {
    private final JTextField tableField = UiKit.field("1400,1000; 6300,400; 4300,400; 3200,1100; 4700,1000", 34);
    private final JTextField addressField = UiKit.field("2,53; 0,1222; 3,1000; 1,10; 4,300", 34);
    private final JTextArea output = UiKit.outputArea();

    public SegmentationApp() {
        super(new BorderLayout());
        JButton translate = UiKit.button("Translate");
        translate.addActionListener(e -> translate());
        JPanel form = UiKit.form(
                "Segment table (base,limit; ...)", tableField,
                "Logical addresses (segment,offset; ...)", addressField);
        add(UiKit.page(form, UiKit.actionRow(translate), output), BorderLayout.CENTER);
    }

    private void translate() {
        try {
            int[][] table = UiKit.matrix(tableField.getText());
            int[][] logical = UiKit.matrix(addressField.getText());

            StringBuilder sb = new StringBuilder("Segment table:\n");
            for (int s = 0; s < table.length; s++) {
                if (table[s].length != 2) throw new IllegalArgumentException("Each segment needs base,limit.");
                sb.append(String.format("  Segment %d: base %d, limit %d%n", s, table[s][0], table[s][1]));
            }

            sb.append("\nTranslations:\n");
            for (int[] la : logical) {
                if (la.length != 2) throw new IllegalArgumentException("Each address needs segment,offset.");
                int seg = la[0];
                int offset = la[1];
                sb.append("  (segment ").append(seg).append(", offset ").append(offset).append(") -> ");
                if (seg < 0 || seg >= table.length) {
                    sb.append("TRAP: invalid segment number\n");
                } else if (offset < 0 || offset >= table[seg][1]) {
                    sb.append("TRAP: offset exceeds limit ").append(table[seg][1]).append("\n");
                } else {
                    sb.append("physical address ").append(table[seg][0] + offset).append("\n");
                }
            }
            UiKit.show(output, sb.toString());
        } catch (RuntimeException ex) {
            UiKit.error(this, ex.getMessage());
        }
    }
}