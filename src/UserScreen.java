import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class UserScreen implements ActionListener{
    // Initializing a Panel object
    Panel panel;

    // Initializing JTextField and JButton
    private final JTextField userName;
    private final JButton userButton;

    // Initializing a JLabel
    JLabel pageTitle;    

    // Constructor
    public UserScreen(Panel panel){
        // declaring class panel to panel paramater
        this.panel = panel;
        
        // Declaring the JTextfield, its location, font and size
        userName = new JTextField();
        userName.setBounds(100,300,200,50);
        userName.setFont(panel.getFont().deriveFont(16f));

        // Declaring the button, its location, font, size, color, and adding actionListener
        userButton = new JButton("Enter Username");
        userButton.setBounds(100,400,200,50);
        userButton.setBackground(panel.getDuoGreen());
        userButton.setForeground(Color.white);
        userButton.setOpaque(true);
        userButton.setBorderPainted(false);
        userButton.setFont(panel.getFont().deriveFont(18f));
        userButton.addActionListener(this);

        // Declaring the JLabel, its locaiton, font, size, and color
        pageTitle = new JLabel("RotLingo", SwingConstants.CENTER);
        pageTitle.setBounds(20, 150, 360, 100);
        pageTitle.setFont(panel.getFont().deriveFont(60f));
        pageTitle.setForeground(Color.white);
    }
    // adding all the components to the panel
    public void addComponents(){
        panel.add(userButton);
        panel.add(userName);
        panel.add(pageTitle);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // checks if the textfield is empty and the button is pressed
        // it sets the textfield to be enter a username then it also 
        // checks if the textfield is enter a username
        if(e.getSource() == userButton && (userName.getText().isEmpty() || userName.getText().equals("Enter a Username"))){
            userName.setText("Enter a Username");
        }
        // else if there is something in the textfield which isnt enter a 
        // username it sets the entered username as the lowercase version of 
        // the entered username
        // it then calls the username saver class in panel to add the entered username to the list
        // once the button is pressed the gamephase changes to the next screen
        else if (e.getSource() == userButton) {
            String enteredUsername = userName.getText().toLowerCase();
            System.out.println("Entered username: " + enteredUsername);
            panel.usernameSaver.addUsername(enteredUsername);
            panel.changePhase(1);
        }
    }
}
