import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MultipleChoiceQuestion extends Question implements ActionListener{
    JButton[] buttons = new JButton[4];

    String[] options;
    int answer;

    JLabel word;

    //Multiple Choice Type Question
    public MultipleChoiceQuestion(Panel panel, GameScreen gameScreen, String word, String[] options, int answer) {
        this.panel = panel;
        this.gameScreen = gameScreen;
        this.options = options;
        this.answer = answer;

        type = "Multiple Choice";

        for(int i = 0; i<buttons.length;i++){
            buttons[i] = new JButton(options[i]);
            buttons[i].setBounds((i<2?((i+1)):(i<4?(i-1):(i-3)))*195-172, (i<2?70:(i<4?150:390))+350, 165, 60);
            buttons[i].setBackground(panel.getDuoNavyBlue());
            buttons[i].setForeground(Color.white);
            buttons[i].setFont(panel.getFont().deriveFont(30f));
            buttons[i].setOpaque(true);
            buttons[i].setBorder(BorderFactory.createLineBorder(panel.getDuoNavyBlue(), 4));
            buttons[i].addActionListener(this);
        }

        question = new JLabel();
        question.setBounds(20, 20, 360, 50);
        question.setFont(panel.getFont().deriveFont(20f));
        question.setForeground(Color.white);
        this.word = new JLabel(word, SwingConstants.CENTER);
        this.word.setBounds(panel.screenWidth/2-360/2, panel.screenHeight/2-50, 360, 50);
        this.word.setFont(panel.getFont().deriveFont(50f));
        this.word.setForeground(Color.white);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i<4; i++){
            if(e.getSource() == buttons[i]){
                buttons[answer].setBackground(panel.getDuoGreen());
                buttons[answer].setBorder(BorderFactory.createLineBorder(panel.getDuoGreen()));
                if(!options[i].equals(options[answer])){   
                    buttons[i].setBackground(panel.getDuoRed());
                    buttons[i].setBorder(BorderFactory.createLineBorder(panel.getDuoRed()));
                    gameScreen.continueButton.setBackground(panel.getDuoRed());
                    this.score = true;
                }
                else{
                    gameScreen.continueButton.setBackground(panel.getDuoGreen());
                }
                for(int j = 0; j<4; j++){
                    buttons[j].setEnabled(false);
                }
                gameScreen.continueButton.setEnabled(true);
                break;
            }
        }
    }
    public void addComponents(){
        for(JButton b : buttons){
            panel.add(b);
        }
        panel.add(question);
        panel.add(word);
    }
}
