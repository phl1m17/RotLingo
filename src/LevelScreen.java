import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class LevelScreen implements ActionListener {
    // Initializing a Panel object
    Panel panel;

    // Declaring a boolean array of all the levels and set 
    // them all to false since as a default the levels are not completed
    boolean[] levelsFinished = {false, false, false, false, false, false};

    // Initialize an array of buttons
    JButton[] levels = new JButton[6];

    // Initialize the JLabel for page title
    JLabel pageTitle;

    public LevelScreen(Panel panel) {
        // declaring class panel to panel paramater
        this.panel = panel;

        // setting up the buttons on the panel for each lesson
        for(int i = 0; i<levels.length; i++){
            levels[i] = new JButton(String.valueOf((i+1)));
            levels[i].setBounds((i<2?((i+1)):(i<4?(i-1):(i-3)))*150-70, (i<2?70:(i<4?230:390))+100, 100, 100);
            levels[i].setBackground(levelsFinished[i]?panel.getDuoGreen():panel.getDuoNavyBlue());
            levels[i].setForeground(Color.white);
            levels[i].setFont(panel.getFont().deriveFont(30f));
            levels[i].setOpaque(true);
            levels[i].setBorder(null);
            levels[i].setForeground(Color.white);
            levels[i].addActionListener(this);
        }
        // setting up the JLabel
        pageTitle = new JLabel("Levels", SwingConstants.CENTER);
        pageTitle.setBounds(20, 70, 360, 50);
        pageTitle.setFont(panel.getFont().deriveFont(40f));
        pageTitle.setForeground(Color.white);
    }

    public void updateLevel(){
        // create a temporary list and set it to be the same as the finished levels arraylist
        ArrayList<Integer> a = panel.usernameSaver.getLevelsFinished();
        // set the index of the finised level to be true since it has been completed
        for(int i = 0; i<a.size(); i++){
            levelsFinished[a.get(i)] = true;
        }
    }
    public void addComponents(){
        // call the update level if needed to update a finished level
        updateLevel();
        
        // a forloop to check if the level is finished if so change it to duogreen from duonavyblue
        for (int i = 0; i<levels.length; i++) {
            levels[i].setBackground(levelsFinished[i]?panel.getDuoGreen():panel.getDuoNavyBlue());
            panel.add(levels[i]);
            levels[i].setVisible(true);
        }
        panel.add(pageTitle);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // check which button is being pressed then call the changePhase in the panel class
        // add 2 to the button pressed since 0 and 1 are already taken (username screen, level screen)
        for(int i = 0;i<levels.length;i++){
            if(e.getSource() == levels[i]){
                panel.changePhase(i+2);
            }
        }
    }
}