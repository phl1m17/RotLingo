import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class UsernameSaver {
    private static final String FILE_NAME = "usernames.txt";
    private final List<String> usernames;

    public UsernameSaver() {
        usernames = loadUsernames();
    }
    private List<String> loadUsernames() {
        List<String> names = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    names.add(line.trim());
                }
            } catch (IOException e) {}
        }
        return names;
    }
    private void saveUsernames() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (String name : usernames) {
                writer.println(name);
            }
        } catch (IOException e) {}
    }

    public void addUsername(String username) {
        if (!usernames.contains(username)) {
            usernames.add(username);
            saveUsernames();
        }
    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Username Saver");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 150);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JTextField textField = new JTextField(20);
        JButton saveButton = new JButton("Save Username");

        JLabel label = new JLabel("Enter your username:");

        saveButton.addActionListener(new SaveButtonListener(this, textField, frame));

        panel.add(label, BorderLayout.NORTH);
        panel.add(textField, BorderLayout.CENTER);
        panel.add(saveButton, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        UsernameSaver app = new UsernameSaver();
        SwingUtilities.invokeLater(() -> {
            app.createAndShowGUI();
        });
    }
}

class SaveButtonListener implements ActionListener {
    private final UsernameSaver app;
    private final JTextField textField;
    private final JFrame frame;

    public SaveButtonListener(UsernameSaver app, JTextField textField, JFrame frame) {
        this.app = app;
        this.textField = textField;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = textField.getText().trim();
        if (!username.isEmpty()) {
            app.addUsername(username);
            JOptionPane.showMessageDialog(frame, "Username saved!");
        } else {
            JOptionPane.showMessageDialog(frame, "Username cannot be empty.");
        }
        textField.setText("");
    }
}
