import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public final class GameScreen{
    int questionCount = 0;

    int levels;

    Question[] questions = new Question[8];

    public GameScreen(Panel panel) {
        String[] opts = {"Cool Points", "Older Male", "Not Lying", "Charisma"};
        try {
            questions[questionCount] = new SoundMultipleChoiceQuestion(panel, this, opts, 3, "src\\Sounds\\Rizz.wav");
        } catch (UnsupportedAudioFileException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void addComponents(){
        questions[questionCount].setQuestion("Question " + (questionCount+1));
        questions[questionCount].addComponents();
    }
}