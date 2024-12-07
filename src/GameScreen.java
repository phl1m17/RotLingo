
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Color;

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
        level1();
    }
    public void level1(){
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
            if(questionCount>=questions.size()-1){
                panel.levelScreen.levelsFinished[panel.getGamePhase()-2] = true;
                panel.changePhase(1);
            }
            else{
                questionCount++;
                panel.removeAll();
                addComponents();
            }
        }
    }
}