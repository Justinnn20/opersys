import javax.swing.*;
import java.awt.BorderLayout;
import java.util.Arrays;
import java.util.List;

/** Deadlock: Banker's Algorithm safety check and resource request. */
public class DeadlockApp extends JPanel {
    private final JTextField availableField = UiKit.field("3 3 2", 22);
    private final JTextField maxField = UiKit.field("7 5 3; 3 2 2; 9 0 2; 2 2 2; 4 3 3", 30);
    private final JTextField allocField = UiKit.field("0 1 0; 2 0 0; 3 0 2; 2 1 1; 0 0 2", 30);
    private final JTextField reqProcessField = UiKit.field("1", 5);
    private final JTextField reqVectorField = UiKit.field("1 0 2", 14);
    private final JTextArea output = UiKit.outputArea();

    public DeadlockApp() {
        super(new BorderLayout());
        JButton check = UiKit.button("Check Safety");
        check.addActionListener(e -> checkSafety());
        JButton request = UiKit.button("Request Resources");
        request.addActionListener(e -> requestResources());

        JPanel form = UiKit.form(
                "Available (R1 R2 ...)", availableField,
                "Max (rows separated by ;)", maxField,
                "Allocation (rows separated by ;)", allocField,
                "Request: process number", reqProcessField,
                "Request: resource vector", reqVectorField);
        add(UiKit.page(form, UiKit.actionRow(check, request), output), BorderLayout.CENTER);
    }

    private BankersAlgorithm load() {
        return new BankersAlgorithm(
                UiKit.ints(availableField.getText()),
                UiKit.matrix(maxField.getText()),
                UiKit.matrix(allocField.getText()));
    }

    private void checkSafety() {
        try {
            BankersAlgorithm bank = load();
            StringBuilder sb = new StringBuilder(describe(bank));
            List<Integer> seq = bank.findSafeSequence();
            if (seq.isEmpty()) {
                sb.append("\nSTATE: UNSAFE - the system may enter deadlock.\n");
            } else {
                sb.append("\nSTATE: SAFE\nSafe sequence: ").append(formatSeq(seq)).append("\n");
            }
            UiKit.show(output, sb.toString());
        } catch (RuntimeException ex) {
            UiKit.error(this, ex.getMessage());
        }
    }

    private void requestResources() {
        try {
            BankersAlgorithm bank = load();
            int p = Integer.parseInt(reqProcessField.getText().trim());
            int[] req = UiKit.ints(reqVectorField.getText());

            StringBuilder sb = new StringBuilder();
            sb.append("Request from P").append(p).append(": ").append(Arrays.toString(req)).append("\n");
            if (bank.request(p, req)) {
                sb.append("GRANTED - the system is still safe.\n");
            } else {
                sb.append("DENIED - not enough available resources, or granting it would be unsafe. P")
                        .append(p).append(" must wait.\n");
            }
            sb.append("\n").append(describe(bank));
            List<Integer> seq = bank.findSafeSequence();
            sb.append(seq.isEmpty() ? "STATE: UNSAFE\n"
                    : "STATE: SAFE\nSafe sequence: " + formatSeq(seq) + "\n");
            UiKit.show(output, sb.toString());
        } catch (RuntimeException ex) {
            UiKit.error(this, ex.getMessage());
        }
    }

    private static String describe(BankersAlgorithm bank) {
        StringBuilder sb = new StringBuilder();
        sb.append("Available: ").append(Arrays.toString(bank.getAvailable())).append("\n\nNeed matrix:\n");
        for (int p = 0; p < bank.getProcessCount(); p++) {
            sb.append(String.format("P%d: %s%n", p, Arrays.toString(bank.getNeed(p))));
        }
        return sb.toString();
    }

    private static String formatSeq(List<Integer> seq) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < seq.size(); i++) {
            if (i > 0) sb.append(" -> ");
            sb.append("P").append(seq.get(i));
        }
        return sb.toString();
    }
}