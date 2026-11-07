import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FeedbackForm extends JFrame {

    public FeedbackForm() {
        setTitle("Feedback Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Main Container with padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 2, 20, 0));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Left Column
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridBagLayout());
        addLeftComponents(leftPanel);

        // Right Column
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        addRightComponents(rightPanel);

        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);

        add(new JScrollPane(mainPanel));
        pack();
        setLocationRelativeTo(null);
    }

    private void addLeftComponents(JPanel panel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Header
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(new JLabel("<html><font size='5'>Tell me about my site! thanks...</font></html>"), gbc);

        // Name
        gbc.gridy++; gbc.gridwidth = 1;
        panel.add(new JLabel("*Name:"), gbc);
        gbc.gridx = 1;
        panel.add(new JTextField(20), gbc);

        // Gender
        gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("Gender:"), gbc);
        gbc.gridx = 1;
        panel.add(new JComboBox<>(new String[]{"F", "M"}), gbc);

        // Birthdate & Age
        gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("*Birthdate:"), gbc);
        JPanel bdayPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        bdayPanel.add(new JTextField("mm-dd-yyyy", 10));
        bdayPanel.add(new JLabel("  Age: "));
        bdayPanel.add(new JTextField(3));
        gbc.gridx = 1;
        panel.add(bdayPanel, gbc);

        // Country
        gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("Country:"), gbc);
        gbc.gridx = 1;
        panel.add(new JComboBox<>(new String[]{"-select one-"}), gbc);

        // Email
        gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("*Email Address:"), gbc);
        gbc.gridx = 1;
        panel.add(new JTextField(20), gbc);

        // Required Label
        gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 2;
        JLabel reqLabel = new JLabel("*this field is required.");
        reqLabel.setForeground(Color.RED);
        panel.add(reqLabel, gbc);

        // Q1
        gbc.gridy++;
        panel.add(new JLabel("<html><b>How do you find my web page?</b><br>1. How do you compare my web site to the others?</html>"), gbc);
        
        String[] options = {"Much Better", "About the same", "Much Worse", "Somewhat Better", "Somewhat Worse"};
        ButtonGroup bg1 = new ButtonGroup();
        gbc.gridy++;
        for (String opt : options) {
            JRadioButton rb = new JRadioButton(opt);
            bg1.add(rb);
            panel.add(rb, gbc);
            gbc.gridy++;
        }
    }

    private void addRightComponents(JPanel panel) {
        // Q2
        panel.add(new JLabel("2. Did you enjoy the Gallery?"));
        panel.add(createRadioGroup(new String[]{"Yes", "No"}));

        // Q3 - Table/Likert
        panel.add(new JLabel("3. How easy did you find it to navigate and find information?"));
        String[] columns = {"", "Very Easy", "Easy", "Average", "Hard", "Very Hard"};
        Object[][] data = {
            {"To navigate the site:", "", "", "", "", ""},
            {"To find needed information:", "", "", "", "", ""}
        };
        JTable table = new JTable(data, columns);
        panel.add(new JScrollPane(table) {
            @Override public Dimension getPreferredSize() { return new Dimension(400, 70); }
        });

        // Q4
        panel.add(new JLabel("4. Overall, how would you rate the graphics and look of the site?"));
        panel.add(createRadioGroup(new String[]{"Excellent", "Good", "Poor", "Very Good", "Fair"}));

        // Q5
        panel.add(new JLabel("5. How likely would you be to return to my site?"));
        panel.add(createRadioGroup(new String[]{"Definitely", "Not Sure", "Definitely Not", "Probably"}));

        // Q6 & 7 Text Areas
        panel.add(new JLabel("6. What did you like about the site?"));
        panel.add(new JScrollPane(new JTextArea(3, 30)));
        panel.add(new JLabel("7. What did you dislike about the site?"));
        panel.add(new JScrollPane(new JTextArea(3, 30)));

        // Contact Info
        panel.add(new JLabel("8. Your contact information. (optional)"));
        JPanel contactPanel = new JPanel(new GridLayout(2, 2));
        contactPanel.add(new JLabel("Email Address:"));
        contactPanel.add(new JTextField());
        contactPanel.add(new JLabel("Mobile Number:"));
        JPanel lastLine = new JPanel(new BorderLayout());
        lastLine.add(new JTextField(), BorderLayout.CENTER);
        lastLine.add(new JButton("Submit"), BorderLayout.EAST);
        contactPanel.add(lastLine);
        panel.add(contactPanel);
    }

    private JPanel createRadioGroup(String[] opts) {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        ButtonGroup bg = new ButtonGroup();
        for (String o : opts) {
            JRadioButton rb = new JRadioButton(o);
            bg.add(rb);
            p.add(rb);
        }
        return p;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FeedbackForm().setVisible(true));
    }
}
