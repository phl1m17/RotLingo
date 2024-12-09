
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public final class GameScreen implements ActionListener{
    int questionCount = 0;

    int levels;
    Panel panel;

    JButton continueButton;

    int score;
    JLabel scoreLabel;

    ArrayList<Question> questions = new ArrayList<>();

    public GameScreen(Panel panel) {
        this.panel = panel;
        continueButton = new JButton("Continue");
        continueButton.setBounds(10, panel.screenHeight-60, panel.screenWidth-20, 50);
        continueButton.setBackground(panel.getDuoNavyBlue());
        continueButton.setForeground(Color.white);
        continueButton.setFont(panel.getFont().deriveFont(30f));
        continueButton.setOpaque(true);
        continueButton.setBorder(BorderFactory.createLineBorder(panel.getDuoNavyBlue(), 4));
        continueButton.addActionListener(this); 

        scoreLabel = new JLabel("", SwingConstants.CENTER);
        scoreLabel.setBounds(20, 200, 360, 50);
        scoreLabel.setFont(panel.getFont().deriveFont(40f));
        scoreLabel.setForeground(Color.white);
    }
    public void levelStart(int level){
        switch(level){
            case 2:
                level1();
                break;
            case 3:
                level2();
                break;
        }
    }
    public void level1(){
        questions.clear();
        String[] opts = {"Cool Points", "Older Male", "Not Lying", "Charisma"};
        try {
            questions.add(new SoundMultipleChoiceQuestion(panel, this, opts, 3, "src/Sounds/Rizz.wav"));
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {}

        String[] opts1 = {"reality", "vibe level", "Tommy", "standard"};
        questions.add(new MultipleChoiceQuestion(panel, this, "Unc", opts1, 2));

        String[][] opts2 = {{"Aura", "Unc", "No Cap", "Rizz", "Sigma"},{"Cool Points", "Older Male", "Not Lying", "Charisma", "Cool Person"}};
        questions.add(new MatchingQuestion(panel, this, opts2));
        
        String[][] opts3 = {{"Brampton", "are", "people", "can"},{"insane", "crazy", "is", "shady"}};
        questions.add(new SentenceMakingQuestion(panel, this, "Brampton mandem be dodgy", opts3, "Brampton people are shady"));
        addComponents();
    }
    public void level2(){
        //Words taught: Gatekeep, Mandem, NPC Energy, dodgy
        questions.clear();
        String[] opts1 = {"Older Male", "Restrict Access", "people(Male)", "Charisma"}; // Gatekeep
        String[] opts2 = {"shady", "Not Lying", "Restrict Access", "people(Male)"}; // Mandem
        String[] opts3 = {"Generic Behaviour", "Cool points", "Cool person", "Older Male"}; // NPC energy
        String[] opts4 = {"shady", "crazy", "insane", "standard"}; // Dodgy
        
        String[] opts5 = {"Restrict Access", "shady", "Generic Behaviour", "crazy"}; // Gatekeep
        String[] opts6 = {"Older Male", "Restrict Access", "people(Male)", "Cool points"}; // Mandem
        String[] opts7 = {"insane", "Not Lying", "Cool person", "Generic Behaviour"}; // NPC energy
        String[] opts8 = {"Charisma", "standard", "shady", "Restrict Access"}; // Dodgy

        String[][] opts9 = {{"Aura", "Unc", "No Cap", "Rizz", "Sigma"},{"Cool Points", "Older Male", "Not Lying", "Charisma", "Cool Person"}};
        String[][] opts10 = {{"Brampton", "are", "people", "can"},{"insane", "crazy", "is", "shady"}};
        
        questions.add(new MultipleChoiceQuestion(panel, this, "Gatekeep", opts1, 1));
        questions.add(new MultipleChoiceQuestion(panel, this, "Mandem", opts2, 3));
        questions.add(new MultipleChoiceQuestion(panel, this, "NPC Energy", opts3, 0));
        questions.add(new MultipleChoiceQuestion(panel, this, "Dodgy", opts4, 0));

        try {
            questions.add(new SoundMultipleChoiceQuestion(panel, this, opts5, 0, "src/Sounds/Gatekeep.wav"));
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {}
        try {
            questions.add(new SoundMultipleChoiceQuestion(panel, this, opts6, 2, "src/Sounds/Mandem.wav"));
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {}
        try {
            questions.add(new SoundMultipleChoiceQuestion(panel, this, opts7, 3, "src/Sounds/NPC Energy.wav"));
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {}
        try {
            questions.add(new SoundMultipleChoiceQuestion(panel, this, opts8, 2, "src/Sounds/Dodgy.wav"));
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {}


        //questions.add(new SentenceMakingQuestion(panel, this, "Brampton mandem be dodgy", opts3, "Brampton people are shady"));
        //questions.add(new MatchingQuestion(panel, this, opts2));        
        addComponents();
    }
    public void addComponents(){
        questions.get(questionCount).setQuestion("Question " + (questionCount+1) + " (" + questions.get(questionCount).type + ")");
        questions.get(questionCount).addComponents();
        continueButton.setEnabled(false);
        panel.add(continueButton);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==continueButton){
            continueButton.setBackground(panel.getDuoNavyBlue());
            if(questionCount==questions.size()){
                panel.levelScreen.levelsFinished[panel.getGamePhase()-2] = true;
                panel.changePhase(1);
                questionCount = 0;
                score = 0;
            }
            else if(questionCount==questions.size()-1){
                panel.removeAll();
                panel.add(continueButton);
                scoreLabel.setText("Marks: " + String.format("%.2f", (((double)score/questions.size())*100)) + "%");
                panel.add(scoreLabel);
                questionCount++;
            }
            else{
                questionCount++;
                panel.removeAll();
                addComponents();
            }
        }
    }
}