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
    int[] ab = {-1,-1};

    public MatchingQuestion(Panel panel, GameScreen gameScreen, String[][] answers){
        this.panel = panel;
        this.gameScreen = gameScreen;
        this.answers = answers;
        this.options = new String[answers.length][];
        for(int i = 0; i<answers.length; i++){
            this.options[i] = answers[i].clone();
        }
        shuffle(this.options);

        for(int i = 0; i<buttons.length;i++){
            for(int j = 0; j<buttons[i].length; j++){
                buttons[i][j] = new JToggleButton(options[i][j]);
                buttons[i][j].setBounds(15+i*190, 120+j*80, 180, 60);
                buttons[i][j].setBackground(panel.getDuoNavyBlue());
                buttons[i][j].setForeground(Color.white);
                buttons[i][j].setFont(panel.getFont().deriveFont(25f));
                buttons[i][j].setOpaque(true);
                buttons[i][j].setBorder(BorderFactory.createLineBorder(panel.getDuoNavyBlue(), 4));
                buttons[i][j].setForeground(Color.white);
                buttons[i][j].addItemListener(this);
            }
        }
        question = new JLabel();
        question.setBounds(20, 20, 360, 50);
        question.setFont(panel.getFont().deriveFont(20f));
        question.setForeground(Color.white);
    }
    public static void shuffle(String[][] arr){
        for(int i = 0; i<arr.length; i++){
            for(int j = 0; j<20; j++){
                int ran = (int)(Math.random()*arr[i].length);
                String temp = arr[i][0];
                arr[i][0] = arr[i][ran];
                arr[i][ran] = temp;
            }
        }
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
                    ab[i] = j;
                    if(ab[0]>-1&&ab[1]>-1){
                        check(ab[0],ab[1]);
                    }
                }
                else if(wasSelected[i][j]==true){
                    for(int k = 0; k<buttons[i].length; k++){
                        if(k!=j){
                            buttons[i][k].setEnabled(true);
                        }
                    } 
                    wasSelected[i][j] =false;
                    ab[i] = -1;
                }
            }
        }
    }
    public void check(int a, int b){
        for(int i = 0; i<answers[0].length; i++){
            System.out.println(answers[0][i] + ", " + answers[1][i]);
            if(options[0][a].equals(answers[0][i])){
                if(answers[1][i].equals(options[1][b])){
                    buttons[0][a].setBackground(panel.getDuoGreen());
                    buttons[1][b].setBackground(panel.getDuoGreen());
                    buttons[0][a].setSelected(false);
                    buttons[1][b].setSelected(false);  
                    return;
                }
            }
        }
        buttons[0][a].setBackground(panel.getDuoRed());
        buttons[1][b].setBackground(panel.getDuoRed());
        buttons[0][a].setSelected(false);
        buttons[1][b].setSelected(false);
        long pastTime = System.nanoTime();
        long currentTime = System.nanoTime();   
        while(currentTime-pastTime<900000000){
            currentTime = System.nanoTime();
            buttons[0][a].setBackground(panel.getDuoRed());
        } 
        trys--;
    }
}
