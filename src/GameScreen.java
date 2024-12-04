import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public final class GameScreen{
    int questionCount = 0;

    int levels;

    Question[] questions = new Question[8];

    public GameScreen(Panel panel) {
        // String[] opts = {"Cool Points", "Older Male", "Not Lying", "Charisma"};
        // try {
        //     questions[questionCount] = new SoundMultipleChoiceQuestion(panel, this, opts, 3, "src\\Sounds\\Rizz.wav");
        // } catch (UnsupportedAudioFileException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // } catch (IOException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // } catch (LineUnavailableException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }

        // String[] opts = {"reality", "vibe level", "Tommy", "standard"};
        // questions[questionCount] = new MultipleChoiceQuestion(this, panel, "Unc", opts, 2);
        
        String[][] opts = {{"Aura", "Unc", "No Cap", "Rizz", "Sigma"},{"Cool Points", "Older Male", "Not Lying", "Charisma", "Cool Person"}};
        questions[questionCount] = new MatchingQuestion(panel, this, opts);
    }
    public void addComponents(){
        questions[questionCount].setQuestion("Question " + (questionCount+1));
        questions[questionCount].addComponents();
    }
}