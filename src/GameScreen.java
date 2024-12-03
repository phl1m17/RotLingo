
import java.util.ArrayList;
import java.util.Arrays;

public final class GameScreen{
    int questionCount = 0;

    int levels;

    //Question Classes
    Question1 q1;

    ArrayList<String>question1Type;
    public GameScreen(Panel panel) {
        question1Type = new ArrayList<>(Arrays.asList("Question 1", "Question 2", "Question 3", "Question 4"));
        q1 = new Question1(this, panel);
    }
    public void addComponents(){
        q1.setQuestion(question1Type.get(questionCount));
        q1.addComponents();
    }
}