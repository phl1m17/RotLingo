import javax.swing.JLabel;

// Parent Class of all the Question Types
public class Question {
    // Initializing all the variables

    JLabel question;
    Panel panel;
    GameScreen gameScreen;

    String type;
    public void setQuestion(String question){
        this.question.setText(question);
    }
    public void addComponents(){
    }
}
