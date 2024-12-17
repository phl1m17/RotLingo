import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

public class SentenceMakingQuestion extends Question implements ItemListener, ActionListener{
    JToggleButton[][] buttons = new JToggleButton[2][4];

    String[][] options;
    String answer;

    ArrayList<JLabel> word = new ArrayList<>();
    JLabel formedWord;
    
    boolean[][] added = {{false,false,false,false},{false,false,false,false}};

    JButton checkButton;

    //Multiple Choice Type Question
    public SentenceMakingQuestion(Panel panel, GameScreen gameScreen, String word, String[][] options, String answer) {
        this.panel = panel;
        this.gameScreen = gameScreen;
        this.options = options;
        this.answer = answer;

        type = "form sentence";

        for(int i = 0; i<buttons.length;i++){
            for(int j = 0; j<buttons[i].length; j++){
                buttons[i][j] = new JToggleButton(options[i][j]);
                buttons[i][j].setBounds(8+j*100, 400+i*60, 85, 50);
                buttons[i][j].setBackground(panel.getDuoNavyBlue());
                buttons[i][j].setForeground(Color.white);
                buttons[i][j].setFont(panel.getFont().deriveFont(12f));
                buttons[i][j].setOpaque(true);
                buttons[i][j].setBorder(BorderFactory.createLineBorder(panel.getDuoNavyBlue(), 4));
                buttons[i][j].addItemListener(this);
            }
        }

        question = new JLabel();
        question.setBounds(20, 20, 360, 50);
        question.setFont(panel.getFont().deriveFont(20f));
        question.setForeground(Color.white);
        
        this.word.add(new JLabel(word, SwingConstants.CENTER));
        this.word.get(0).setBounds(panel.screenWidth/2-360/2, panel.screenHeight/2-150, 360, 50);
        this.word.get(0).setFont(panel.getFont().deriveFont(20f));
        this.word.get(0).setForeground(Color.white);

        splitLines(this.word.get(0), 20);

        formedWord = new JLabel("", SwingConstants.CENTER);
        formedWord.setBounds(panel.screenWidth/2-360/2, panel.screenHeight/2-50, 360, 50);
        formedWord.setFont(panel.getFont().deriveFont(12f));
        formedWord.setForeground(Color.white);

        checkButton = new JButton("Check");
        checkButton.setBounds(panel.screenWidth/2-100/2, gameScreen.continueButton.getY()-60, 100, 50);
        checkButton.setBackground(panel.getDuoNavyBlue());
        checkButton.setForeground(Color.white);
        checkButton.setFont(panel.getFont().deriveFont(30f));
        checkButton.setOpaque(true);
        checkButton.setBorder(BorderFactory.createLineBorder(panel.getDuoNavyBlue(), 4));
        checkButton.addActionListener(this);
    }
    public void splitLines(JLabel jl, int fontSize){
        //1.7 ratio to pixel size each char of the font takes up
        fontSize/=1.7;
        System.out.println(jl.getWidth());
        if(fontSize*jl.getText().length()>jl.getWidth()){
            int cutOff = jl.getText().substring(0, jl.getWidth()/fontSize).lastIndexOf(" ");
            String line1 = jl.getText().substring(0, cutOff);
            String line2 = jl.getText().substring(cutOff);
            jl.setText(line1);
            word.add(new JLabel(line2, SwingConstants.CENTER));
            word.get(word.size()-1).setBounds(jl.getX(), jl.getY()+fontSize+10, jl.getWidth(), jl.getHeight());
            word.get(word.size()-1).setFont(panel.getFont().deriveFont(20f));
            word.get(word.size()-1).setForeground(Color.white);
            splitLines(word.get(word.size()-1), fontSize);
        }
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==checkButton){
            gameScreen.continueButton.setEnabled(true);
            if(formedWord.getText().equalsIgnoreCase(" "+answer)){
                for(JToggleButton[] buttons1 : buttons){
                    for(JToggleButton b: buttons1){
                        b.setEnabled(false);
                        b.setSelected(false);
                        b.setBackground(panel.getDuoGreen());
                    }
                }
                gameScreen.continueButton.setBackground(panel.getDuoGreen());
                checkButton.setBackground(panel.getDuoGreen());
                checkButton.setEnabled(false);
                gameScreen.score++;
            }
            else{
                for(JToggleButton[] buttons1 : buttons){
                    for(JToggleButton b: buttons1){
                        b.setEnabled(false);
                        b.setSelected(false);
                        b.setBackground(panel.getDuoRed());
                    }
                }
                gameScreen.continueButton.setBackground(panel.getDuoRed());
                checkButton.setBackground(panel.getDuoRed());
                checkButton.setEnabled(false);
            }
        }
    }
    public void itemStateChanged(ItemEvent e) {
        for(int i = 0; i<buttons.length;i++){
            for(int j = 0; j<buttons[i].length; j++){
                if(buttons[i][j].isSelected()&&!added[i][j]){
                    formedWord.setText(formedWord.getText()+" "+buttons[i][j].getText());
                    added[i][j] = true;
                }
                else if(!buttons[i][j].isSelected()&&added[i][j]){
                    formedWord.setText(formedWord.getText().replace(" "+buttons[i][j].getText(), ""));
                    added[i][j] = false;
                }
            }
        }
    }
    public void addComponents(){
        for(JToggleButton[] buttons1 : buttons){
            for(JToggleButton b: buttons1){
                panel.add(b);
            }
        }
        for(JLabel i: word){
            panel.add(i);
        }
        panel.add(formedWord);
        panel.add(question);
        panel.add(checkButton);
    }
}
