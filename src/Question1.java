import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Question1 implements  ActionListener{
    JButton[] buttons = new JButton[4];
    Panel panel;
    GameScreen gameScreen;
    JLabel question;

    //Multiple Choice Type Question
    public Question1(GameScreen gameScreen, Panel panel) {
        this.panel = panel;
        this.gameScreen = gameScreen;

        for(int i = 0; i<buttons.length;i++){
            buttons[i] = new JButton(String.valueOf((i+1)));
            buttons[i].setBounds((i<2?((i+1)):(i<4?(i-1):(i-3)))*195-172, (i<2?70:(i<4?150:390))+400, 165, 60);
            buttons[i].setBackground(new Color(111, 228, 21));
            buttons[i].setForeground(Color.white);
            buttons[i].setFont(panel.getFont().deriveFont(30f));
            buttons[i].setOpaque(true);
            buttons[i].setBorder(BorderFactory.createLineBorder(panel.getDuoGreen(), 4));
            buttons[i].setForeground(Color.white);
            buttons[i].addActionListener(this);
        }

        question = new JLabel();
        question.setBounds(20, 20, 360, 50);
        question.setFont(panel.getFont().deriveFont(20f));
        question.setForeground(Color.white);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == buttons[0]){
            gameScreen.questionCount++;
            gameScreen.q1.setQuestion(gameScreen.question1Type.get(gameScreen.questionCount));
        }
    }
    public void addComponents(){
        for(JButton b : buttons){
            panel.add(b);
        }
        panel.add(question);
    }
    public void setQuestion(String question){
        this.question.setText(question);
    }
}
