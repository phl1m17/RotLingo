import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;

public class UserScreen implements ActionListener{
    Panel panel;

    private final JTextField userName;
    private final JButton userButton;

    public UserScreen(Panel panel){
        this.panel = panel;
        
        userName = new JTextField();
        userName.setBounds(100,300,200,50);
        userName.setFont(panel.getFont().deriveFont(16f));

        System.out.println(panel.getDuoGreen());

        userButton = new JButton("Enter Username");
        userButton.setBounds(100,400,200,50);
        userButton.setBackground(panel.getDuoGreen());
        userButton.setForeground(Color.white);
        userButton.setOpaque(true);
        userButton.setBorderPainted(false);
        userButton.setFont(panel.getFont().deriveFont(18f));
        userButton.addActionListener(this);
    }
    public void addComponents(){
        panel.add(userButton);
        panel.add(userName);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == userButton && (userName.getText().isEmpty() || userName.getText().equals("Enter a Username"))){
            userName.setText("Enter a Username");
        }
        else if (e.getSource() == userButton) {
            String enteredUsername = userName.getText().toLowerCase();
            System.out.println("Entered username: " + enteredUsername);
            panel.usernameSaver.addUsername(enteredUsername);
            panel.changePhase(1);
            if(panel.usernameSaver.getList().contains(userName.getText())){
                panel.gameScreen.levels = panel.levelSaver.level(userName.getText());
            }
        }
    }
}
