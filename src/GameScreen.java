
import javax.swing.JButton;

public class GameScreen {
    Panel panel;
    JButton answerButton;
    public GameScreen(Panel panel) {
        this.panel = panel;
        answerButton = new JButton();
        answerButton.setBounds(20,20,200,50);
    }
    public void addComponents(int level){
        question1(level);
        panel.add(answerButton);
    }
    public void question1(int level){
        switch (level) {
            case 0:
                answerButton.setText("Question1 lesson 1");
                break;
            case 1:
                answerButton.setText("Question1 lesson 2");
                break;
            default:
                throw new AssertionError();
        }
    }
    public void question2(){
        answerButton.setText("Question2");
    }
    public void question3(){
        answerButton.setText("Question3");
    }
}
