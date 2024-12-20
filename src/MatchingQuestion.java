// Author: Shams C. and Phil M.
// Purpose: blueprint for matching questions

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.Timer;

public class MatchingQuestion extends Question implements ItemListener{
    // 2 by 5 grid of toggle buttons
    JToggleButton[][] buttons = new JToggleButton[2][5];

    // 2 by 5 grid of options, the answers, and boolean to see if the button is pressed
    String[][] options = new String[2][5];
    String[][] answers = new String[2][5];
    
    // is the button is done with then the corresponding index here is be turned to true
    boolean[][] buttonDone = {{false,false,false,false,false},{false,false,false,false,false}};
    
    // limited to max of 3 attemps
    int trys = 3;

    // index of button selected
    int[] buttonSelectedIndex = {-1,-1};

    public MatchingQuestion(Panel panel, GameScreen gameScreen, String[][] answers){
        // Declaring variables
        this.panel = panel;
        this.gameScreen = gameScreen;
        this.answers = answers;
        this.options = new String[answers.length][];

        // creating a copy of the answer array
        for(int i = 0; i<answers.length; i++){
            this.options[i] = answers[i].clone();
        }
        // shuffle the options list
        shuffle(this.options);

        // matching type question
        type = "Match";

        // setting up the buttons
        for(int i = 0; i<buttons.length;i++){
            for(int j = 0; j<buttons[i].length; j++){
                buttons[i][j] = new JToggleButton(options[i][j]);
                buttons[i][j].setBounds(15+i*190, 120+j*80, 180, 60);
                buttons[i][j].setBackground(panel.getDuoNavyBlue());
                buttons[i][j].setForeground(Color.white);
                buttons[i][j].setFont(panel.getFont().deriveFont(15f));
                buttons[i][j].setOpaque(true);
                buttons[i][j].setBorder(BorderFactory.createLineBorder(panel.getDuoNavyBlue(), 4));
                buttons[i][j].setForeground(Color.white);
                buttons[i][j].addItemListener(this);
            }
        }
        // setting up the label
        question = new JLabel();
        question.setBounds(20, 20, 360, 50);
        question.setFont(panel.getFont().deriveFont(20f));
        question.setForeground(Color.white);
    }
    // shuffle method
    public static void shuffle(String[][] arr){
        for (String[] arr1 : arr) {
            for (int j = 0; j<5; j++) {
                // create random index
                int ran1 = (int) (Math.random() * arr1.length);
                int ran2 = (int) (Math.random() * arr1.length);
                // temp string placement
                String temp = arr1[ran2];

                // swap
                arr1[ran2] = arr1[ran1];
                arr1[ran1] = temp;
            }
        }
    }
    // add all components
    @Override
    public void addComponents(){
        for (JToggleButton[] button1 : buttons) {
            for (JToggleButton button : button1) {
                panel.add(button);
            }
        }
        panel.add(question);
    }
    @Override
    public void itemStateChanged(ItemEvent e) {
        // check which buttons are clicked
        for(int i = 0; i<buttons.length;i++){
            for(int j = 0; j<buttons[i].length; j++){
                if(buttons[i][j].isSelected()){
                    // disabling row button selected in
                    for(int k = 0; k<buttons[i].length; k++){
                        if(k!=j){
                            buttons[i][k].setEnabled(false);
                        }
                    } 

                    // setting button selected
                    buttonSelectedIndex[i] = j;

                    // checking if button on each row has been selected
                    if(buttonSelectedIndex[0]>-1&&buttonSelectedIndex[1]>-1){
                        check(buttonSelectedIndex[0],buttonSelectedIndex[1]);
                    }
                }
                else if(buttonSelectedIndex[i]==j){ // button has been unselected
                    // buttons in the corresponding column are re-enabled
                    for(int k = 0; k<buttons[i].length; k++){
                        if(k!=j&&!buttonDone[i][k]){
                            buttons[i][k].setEnabled(true);
                        }
                    } 
                    // button index is none = -1
                    buttonSelectedIndex[i] = -1;
                }
            }
        }
    }
    public void check(int a, int b){ // options[0][a] is selected and options[1][b] is selected
        // iterating through the answers
        for(int i = 0; i<answers[0].length; i++){
            // finds row in column 0 where options and answers match
            if(options[0][a].equals(answers[0][i])){
                // checks if the answers is equal to the players answer
                if(options[1][b].equals(answers[1][i])){
                    // buttons are turned green and turned off
                    buttons[0][a].setBackground(panel.getDuoGreen());
                    buttons[1][b].setBackground(panel.getDuoGreen());
                    buttons[0][a].setSelected(false);
                    buttons[1][b].setSelected(false);
                    // no buttons selected anymore so it is set to -1
                    buttonSelectedIndex[0]=-1;
                    buttonSelectedIndex[1]=-1;
                    // button no longer need to be functional
                    buttonDone[0][a] = true;
                    buttonDone[1][b] = true;
                    buttons[0][a].setEnabled(false);
                    buttons[1][b].setEnabled(false);
                    // checks if there are any functional buttons left
                    for(boolean button: buttonDone[0]){
                        if(!button){
                            // if so question continues
                            return;
                        }
                    }
                    // starts end game procedure for a win
                    for (JToggleButton[] button1 : buttons) {
                        for (JToggleButton button : button1) {
                            button.setBackground(panel.getDuoGreen());
                            button.setEnabled(false);
                        }
                    }
                    gameScreen.continueButton.setBackground(panel.getDuoGreen());
                    // score is increased
                    gameScreen.score++;
                    // lets you continue
                    gameScreen.continueButton.setEnabled(true);
                    return;
                }
            }
        }
        // options never matched answers, selected buttons were wrong
        buttons[0][a].setBackground(panel.getDuoRed());
        buttons[1][b].setBackground(panel.getDuoRed());
        buttons[0][a].setSelected(false);
        buttons[1][b].setSelected(false);
            
        buttons[0][a].setBackground(panel.getDuoRed());
        buttons[1][b].setBackground(panel.getDuoRed());
        
        // Since gameThread.sleep didnt work we had to use Timer class from javax.swing
        Timer timer = new Timer(1000, new ActionListener(){ // Timer requires a int delay and an action performed interface
            // needed to make an anonymous class since we need the actionperfomed method to access a and b
            @Override
            public void actionPerformed(ActionEvent e) {
                if(trys>0){
                    buttons[0][a].setBackground(panel.getDuoNavyBlue());
                    buttons[1][b].setBackground(panel.getDuoNavyBlue());
                }
            }
            
        });
        timer.setRepeats(false);
        timer.start();
        trys--;

        // if # of trys has been finished then starts end game procedure
        if(trys<=0){
            for (JToggleButton[] button1 : buttons) {
                for (JToggleButton button : button1) {
                    button.setBackground(panel.getDuoRed());
                    button.setEnabled(false);
                }
            }
            gameScreen.continueButton.setBackground(panel.getDuoRed());
            gameScreen.continueButton.setEnabled(true);
        }
    }
}
// https://stackoverflow.com/questions/30008010/using-an-anonymous-listener-object-inside-the-timer-class/30008108#30008108