
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public final class GameScreen implements ActionListener{
    int questionCount = 0;
    //Question Classes
    Question1 q1;

    ArrayList<String>question1Type;
    public GameScreen(Panel panel) {
        question1Type = new ArrayList<>(Arrays.asList("Question 1", "Question2"));
        q1 = new Question1(this, panel);
    }
    public void addComponents(){
        q1.setQuestion(question1Type.get(questionCount));
        q1.addComponents();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == q1.buttons[0]){
            System.out.println("a");
            questionCount++;
            q1.setQuestion(question1Type.get(questionCount));
        }
    }
}