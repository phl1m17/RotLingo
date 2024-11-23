import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserScreen implements ActionListener{
    Panel panel;

    private final JTextField userName;
    private final JButton userButton;

    public UserScreen(Panel panel){
        this.panel = panel;
        
        userName = new JTextField();
        userName.setBounds(100,300,200,50);

        userButton = new JButton("Enter Username");
        userButton.setBounds(100,400,200,50);
    }
    public void removeComponents(){
        // userButton.setVisible(false);
        // userName.setVisible(false);
        userButton.removeActionListener(this);
    }
    public void addComponents(){
        panel.add(userButton);
        panel.add(userName);
        userButton.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == userButton) {
            String enteredUsername = userName.getText().toLowerCase();
            System.out.println("Entered username: " + enteredUsername);
            panel.usernameSaver.addUsername(enteredUsername);
            panel.changePhase(1);
        }
    }
}
