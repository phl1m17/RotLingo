import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.Timer;

public class MatchingQuestion extends Question implements ItemListener, ActionListener{
    JToggleButton[][] buttons = new JToggleButton[2][5];

    JButton continueButton;

    String[][] options = new String[2][5];
    String[][] answers = new String[2][5];
    boolean[][] buttonDone = {{false,false,false,false,false},{false,false,false,false,false}};
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
        continueButton = new JButton("Continue");
        continueButton.setBounds(10, panel.screenHeight-60, panel.screenWidth-20, 50);
        continueButton.setBackground(panel.getDuoNavyBlue());
        continueButton.setForeground(Color.white);
        continueButton.setFont(panel.getFont().deriveFont(30f));
        continueButton.setOpaque(true);
        continueButton.setBorder(BorderFactory.createLineBorder(panel.getDuoNavyBlue(), 4));
        continueButton.addActionListener(this);
    }
    public static void shuffle(String[][] arr){
        for(int i = 0; i<arr.length; i++){
            for(int j = 0; j<5; j++){
                int ran1 = (int)(Math.random()*arr[i].length);
                int ran2 = (int)(Math.random()*arr[i].length);
                System.out.println(ran1);
                String temp = arr[i][ran2];
                arr[i][ran2] = arr[i][ran1];
                arr[i][ran1] = temp;
            }
        }
    }
    @Override
    public void addComponents(){
        for (JToggleButton[] button1 : buttons) {
            for (JToggleButton button : button1) {
                panel.add(button);
            }
        }
        panel.add(question);
        panel.add(continueButton);
    }
    @Override
    public void itemStateChanged(ItemEvent e) {
        for(int i = 0; i<buttons.length;i++){
            for(int j = 0; j<buttons[i].length; j++){
                if(buttons[i][j].isSelected()){
                    for(int k = 0; k<buttons[i].length; k++){
                        if(k!=j){
                            buttons[i][k].setEnabled(false);
                        }
                    } 
                    ab[i] = j;
                    if(ab[0]>-1&&ab[1]>-1){
                        check(ab[0],ab[1]);
                    }
                }
                else if(ab[i]==j){
                    for(int k = 0; k<buttons[i].length; k++){
                        if(k!=j&&!buttonDone[i][k]){
                            buttons[i][k].setEnabled(true);
                        }
                    } 
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
                    ab[0]=-1;
                    ab[1]=-1; 
                    buttonDone[0][a] = true;
                    buttonDone[1][b] = true;
                    buttons[0][a].setEnabled(false);
                    buttons[1][b].setEnabled(false);
                    for(boolean button: buttonDone[0]){
                        if(!button){
                            return;
                        }
                    }
                    for (JToggleButton[] button1 : buttons) {
                        for (JToggleButton button : button1) {
                            button.setBackground(panel.getDuoGreen());
                            button.setEnabled(false);
                        }
                    }
                    continueButton.setBackground(panel.getDuoGreen());
                    return;
                }
            }
        }
        buttons[0][a].setBackground(panel.getDuoRed());
        buttons[1][b].setBackground(panel.getDuoRed());
        buttons[0][a].setSelected(false);
        buttons[1][b].setSelected(false);
            
        buttons[0][a].setBackground(panel.getDuoRed());
        buttons[1][b].setBackground(panel.getDuoRed());
        
        Timer timer = new Timer(1000, new ActionListener(){
            // Anonymous Class
            @Override
            public void actionPerformed(ActionEvent e) {
                if(trys>=0){
                    buttons[0][a].setBackground(panel.getDuoNavyBlue());
                    buttons[1][b].setBackground(panel.getDuoNavyBlue());
                }
            }
            
        });
        timer.setRepeats(false);
        timer.start();
        trys--;
        if(trys<0){
            for (JToggleButton[] button1 : buttons) {
                for (JToggleButton button : button1) {
                    button.setBackground(panel.getDuoRed());
                    button.setEnabled(false);
                }
            }
            continueButton.setBackground(panel.getDuoRed());
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
