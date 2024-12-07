import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

public class SentenceMakingQuestion extends Question implements ItemListener, ActionListener{
    JToggleButton[][] buttons = new JToggleButton[2][4];

    String[][] options;
    String answer;

    JLabel word;
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
        
        this.word = new JLabel(word, SwingConstants.CENTER);
        this.word.setBounds(panel.screenWidth/2-360/2, panel.screenHeight/2-150, 360, 50);
        this.word.setFont(panel.getFont().deriveFont(20f));
        this.word.setForeground(Color.white);

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
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==checkButton){
            System.out.println(formedWord.getText());
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
                this.score = true;
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
        panel.add(word);
        panel.add(formedWord);
        panel.add(question);
        panel.add(checkButton);
    }
}
