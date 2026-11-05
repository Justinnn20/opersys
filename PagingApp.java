import javax.swing.*;
import java.awt.BorderLayout;

/** Paging: splits logical addresses into page number and offset, then maps them to physical addresses. */
public class PagingApp extends JPanel {
    private final JTextField pageSizeField = UiKit.field("1024", 10);
    private final JTextField addressField = UiKit.field("0, 3500, 2100, 7000, 4096", 30);
    private final JTextField tableField = UiKit.field("5, 9, -1, 7, 1, 3, 0, 8", 30);
    private final JTextArea output = UiKit.outputArea();

    public PagingApp() {
        super(new BorderLayout());
        JButton translate = UiKit.button("Translate Addresses");
        translate.addActionListener(e -> translate());
        JPanel form = UiKit.form(
                "Page size (bytes)", pageSizeField,
                "Logical addresses", addressField,
                "Page table (frame per page, -1 = not in memory)", tableField);
        add(UiKit.page(form, UiKit.actionRow(translate), output), BorderLayout.CENTER);
    }

    private void translate() {
        try {
            int pageSize = Integer.parseInt(pageSizeField.getText().trim());
            if (pageSize <= 0) throw new IllegalArgumentException("Page size must be greater than 0.");
            int[] addresses = UiKit.ints(addressField.getText());
            int[] table = UiKit.ints(tableField.getText());

            StringBuilder sb = new StringBuilder();
            sb.append("Page table:\n");
            for (int i = 0; i < table.length; i++) {
                sb.append(String.format("  Page %d -> %s%n", i, table[i] < 0 ? "not in memory" : "frame " + table[i]));
            }
            sb.append("\nTranslations:\n");
            for (int addr : addresses) {
                sb.append("  Logical ").append(addr).append(": ");
                if (addr < 0) {
                    sb.append("INVALID address\n");
                    continue;
                }
                int page = addr / pageSize;
                int offset = addr % pageSize;
                sb.append("page ").append(page).append(", offset ").append(offset).append(" -> ");
                if (page >= table.length) {
                    sb.append("TRAP: page number out of range\n");
                } else if (table[page] < 0) {
                    sb.append("PAGE FAULT: load the page from disk first\n");
                } else {
                    int frame = table[page];
                    sb.append("frame ").append(frame)
                            .append(" -> physical address ").append(frame * pageSize + offset).append("\n");
                }
            }
            UiKit.show(output, sb.toString());
        } catch (RuntimeException ex) {
            UiKit.error(this, ex.getMessage());
        }
    }
}