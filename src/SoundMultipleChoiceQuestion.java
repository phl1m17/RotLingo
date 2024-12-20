// Author: Shams C. and Phil M.
// Purpose: blueprint for Sound multiple choice questions

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
public class SoundMultipleChoiceQuestion extends Question implements ActionListener{
    // Multiple choice buttons
    JButton[] buttons = new JButton[4];

    // available options of strings
    String[] options;

    // index of answer
    int answer;

    // a jbutton to play the audio
    JButton audioButton;
    // required Audio player objects
    AudioInputStream audioInputStream; 
    Clip clip;

    // Sound Multiple Choice Type Question
    public SoundMultipleChoiceQuestion(Panel panel, GameScreen gameScreen, String[] options, int answer, String soundPath) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        this.panel = panel;
        this.gameScreen = gameScreen;
        this.options = options;
        this.answer = answer;

        // declaring audioinputstream and clip
        audioInputStream = AudioSystem.getAudioInputStream(new File(soundPath));
        clip = AudioSystem.getClip();  
        clip.open(audioInputStream);

        // type of question
        type = "Sound Multiple Choice";

        // setting up the buttons
        for(int i = 0; i<buttons.length;i++){
            buttons[i] = new JButton(options[i]);
            buttons[i].setBounds((i<2?((i+1)):(i<4?(i-1):(i-3)))*195-172, (i<2?70:(i<4?150:390))+350, 165, 60);
            buttons[i].setBackground(panel.getDuoNavyBlue());
            buttons[i].setForeground(Color.white);
            buttons[i].setFont(panel.getFont().deriveFont(15f));
            buttons[i].setOpaque(true);
            buttons[i].setBorder(BorderFactory.createLineBorder(panel.getDuoNavyBlue(), 4));
            buttons[i].addActionListener(this);
        }

        // setting up the question label
        question = new JLabel();
        question.setBounds(20, 20, 360, 50);
        question.setFont(panel.getFont().deriveFont(20f));
        question.setForeground(Color.white);
        
        // setting up the play audio button
        audioButton = new JButton("play");
        audioButton.setBounds(panel.screenWidth/2-120/2, panel.screenHeight/2-80, 120, 80);
        audioButton.setFont(panel.getFont().deriveFont(30f));
        audioButton.setForeground(panel.getDuoNavyBlue());
        audioButton.addActionListener(this);
        audioButton.setBackground(panel.getDuoBlue());
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // check which button is pressed
        // check if the index of that button is equal to the answer index
        for(int i = 0; i<4; i++){
            if(e.getSource() == buttons[i]){
                buttons[answer].setBackground(panel.getDuoGreen());
                buttons[answer].setBorder(BorderFactory.createLineBorder(panel.getDuoGreen()));
                if(!options[i].equals(options[answer])){   
                    buttons[i].setBackground(panel.getDuoRed());
                    buttons[i].setBorder(BorderFactory.createLineBorder(panel.getDuoRed()));
                    gameScreen.continueButton.setBackground(panel.getDuoRed());
                }
                else{
                    gameScreen.continueButton.setBackground(panel.getDuoGreen());
                    gameScreen.score++;
                }
                for(int j = 0; j<4; j++){
                    buttons[j].setEnabled(false);
                }
                gameScreen.continueButton.setEnabled(true);
                break;
            }
        }
        // check if the audio button is pressed, if so start the clip at 0 seconds
        if(e.getSource() == audioButton){
            clip.setMicrosecondPosition(0);
            clip.start();
        }
    }
    // Add all componenets
    @Override
    public void addComponents(){
        for(JButton b : buttons){
            panel.add(b);
        }
        panel.add(question);
        panel.add(audioButton);
    }
}
