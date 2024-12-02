
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;

public final class GameScreen implements ActionListener{
    Panel panel;
    JButton[]buttons = new JButton[4];
    JLabel question;
    ArrayList<String>questions;
    int questionCount = 0;
    public GameScreen(Panel panel) {
        this.panel = panel;

        question = new JLabel();
        question.setBounds(20, 20, 360, 50);
        question.setFont(panel.getFont().deriveFont(20f));
        question.setForeground(Color.white);

        questions = new ArrayList<>(Arrays.asList("question 1","question 2","question 3","question 4"));
        questions(questionCount);
        
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
    }
    public void addComponents(){
        for(JButton b : buttons){
            panel.add(b);
        }
        panel.add(question);
    }
    public void questions(int level){
        question.setText(questions.get(level));
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==buttons[0]){
            questionCount++;
            questions(questionCount);
        }
        else{
            question.setText("Incorrect");
        }
    }
}