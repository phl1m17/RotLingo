import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JToggleButton;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.Color;

public class MatchingQuestion extends Question implements ItemListener{
    JToggleButton[][] buttons = new JToggleButton[2][5];

    String[][] options = new String[2][5];
    String[][] answers = new String[2][5];
    Boolean[][] wasSelected = {{false, false, false, false, false}, {false, false, false, false, false}};
    int trys = 3;

    public MatchingQuestion(Panel panel, GameScreen gameScreen, String[][] answers){
        this.panel = panel;
        this.gameScreen = gameScreen;
        this.answers = answers;
        this.options = shuffle(answers);

        for(int i = 0; i<buttons.length;i++){
            for(int j = 0; j<buttons[i].length; j++){
                buttons[i][j] = new JToggleButton(options[i][j]);
                buttons[i][j].setBounds(15+i*190, 120+j*80, 180, 60);
                buttons[i][j].setBackground(new Color(24, 32, 39));
                buttons[i][j].setForeground(Color.white);
                buttons[i][j].setFont(panel.getFont().deriveFont(25f));
                buttons[i][j].setOpaque(true);
                buttons[i][j].setBorder(BorderFactory.createLineBorder(new Color(24, 32, 39), 4));
                buttons[i][j].setForeground(Color.white);
                buttons[i][j].addItemListener(this);
            }
        }
        question = new JLabel();
        question.setBounds(20, 20, 360, 50);
        question.setFont(panel.getFont().deriveFont(20f));
        question.setForeground(Color.white);
    }
    public static String[][] shuffle(String[][] ar){
        String[][] arr = ar;
        for(int i = 0; i<arr.length; i++){
            for(int j = 0; j<20; j++){
                int ran = (int)(Math.random()*arr[i].length);
                String temp = arr[i][0];
                arr[i][0] = arr[i][ran];
                arr[i][ran] = temp;
            }
        }
        return arr;
    }
    public void addComponents(){
        for(int i = 0; i<buttons.length;i++){
            for(int j = 0; j<buttons[i].length; j++){
                panel.add(buttons[i][j]);
            }
        }
        panel.add(question);
    }
    //Override
    public void itemStateChanged(ItemEvent e) {
        for(int i = 0; i<buttons.length;i++){
            for(int j = 0; j<buttons[i].length; j++){
                if(buttons[i][j].isSelected()){
                    for(int k = 0; k<buttons[i].length; k++){
                        if(k!=j){
                            buttons[i][k].setEnabled(false);
                        }
                    } 
                    wasSelected[i][j] = true;
                }
                else if(wasSelected[i][j]==true){
                    for(int k = 0; k<buttons[i].length; k++){
                        if(k!=j){
                            buttons[i][k].setEnabled(true);
                        }
                    } 
                    wasSelected[i][j] =false;
                }
            }
        }
    }
    public boolean check(int a, int b){
        for(int i = 0; i<options[0].length; i++){
            if(options[0][a].equals(answers[0][i])){
                if(answers[1][i].equals(options[1][b])){
                    buttons[0][a].setBackground(panel.getDuoGreen());
                }
            }
        }
        trys--;
    }
}
