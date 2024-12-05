import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public final class GameScreen{
    int questionCount = 0;

    int levels;

    Question[] questions = new Question[8];

    public GameScreen(Panel panel) {
        //String[] opts = {"Cool Points", "Older Male", "Not Lying", "Charisma"};
        //try {
        //    questions[questionCount] = new SoundMultipleChoiceQuestion(panel, this, opts, 3, "src/Sounds/Rizz.wav");
        //} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {}

        //String[] opts = {"reality", "vibe level", "Tommy", "standard"};
        //questions[questionCount] = new MultipleChoiceQuestion(panel, this, "Unc", opts, 2);

        //String[][] opts = {{"Aura", "Unc", "No Cap", "Rizz", "Sigma"},{"Cool Points", "Older Male", "Not Lying", "Charisma", "Cool Person"}};
        //questions[questionCount] = new MatchingQuestion(panel, this, opts);
        
        String[][] opts = {{"Brampton", "are", "people", "can"},{"insane", "crazy", "is", "shady"}};
        questions[questionCount] = new SentenceMakingQuestion(panel, this, "Brampton mandem be dodgy", opts, "Brampton people are shady");
    }
    public void addComponents(){
        questions[questionCount].setQuestion("Question " + (questionCount+1));
        questions[questionCount].addComponents();
    }
}