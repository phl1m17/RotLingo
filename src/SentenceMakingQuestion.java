import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

public class SentenceMakingQuestion extends Question implements ItemListener, ActionListener{
    // making a 2,4 grid of toggleable buttons
    JToggleButton[][] buttons = new JToggleButton[2][4];

    // 2d array of available options
    String[][] options;
    
    // String answer
    String answer;

    // formed Sentence
    ArrayList<JLabel> word = new ArrayList<>();

    // visible label of the formed sentence
    JLabel formedWord;
    
    // boolean value for each button to see if it has been added or not
    boolean[][] added = {{false,false,false,false},{false,false,false,false}};

    // a button to check if your answer is correct
    JButton checkButton;

    //Sentence Making Type Question
    public SentenceMakingQuestion(Panel panel, GameScreen gameScreen, String word, String[][] options, String answer) {
        // Declaring the variables
        this.panel = panel;
        this.gameScreen = gameScreen;
        this.options = options;
        this.answer = answer;

        type = "form sentence";

        // setting up the buttons
        for(int i = 0; i<buttons.length;i++){
            for(int j = 0; j<buttons[i].length; j++){
                buttons[i][j] = new JToggleButton(options[i][j]);
                buttons[i][j].setBounds(8+j*100, 400+i*60, 85, 50);
                buttons[i][j].setBackground(panel.getDuoNavyBlue());
                buttons[i][j].setForeground(Color.white);
                buttons[i][j].setFont(panel.getFont().deriveFont(12f));
                buttons[i][j].setOpaque(true);
                buttons[i][j].setBorder(BorderFactory.createLineBorder(panel.getDuoNavyBlue(), 4));
                buttons[i][j].addItemListener(this);
            }
        }
        
        // setting up the question label
        question = new JLabel();
        question.setBounds(20, 20, 360, 50);
        question.setFont(panel.getFont().deriveFont(20f));
        question.setForeground(Color.white);
        
        // setting up the formed sentence label
        this.word.add(new JLabel(word, SwingConstants.CENTER));
        this.word.get(0).setBounds(panel.screenWidth/2-360/2, panel.screenHeight/2-150, 360, 50);
        this.word.get(0).setFont(panel.getFont().deriveFont(20f));
        this.word.get(0).setForeground(Color.white);

        // checking the sentence is too long and split it into different lines
        splitLines(this.word.get(0), 20);

        // setting up the formed sentence
        formedWord = new JLabel("", SwingConstants.CENTER);
        formedWord.setBounds(panel.screenWidth/2-360/2, panel.screenHeight/2-50, 360, 50);
        formedWord.setFont(panel.getFont().deriveFont(12f));
        formedWord.setForeground(Color.white);

        // setting up check button
        checkButton = new JButton("Check");
        checkButton.setBounds(panel.screenWidth/2-100/2, gameScreen.continueButton.getY()-60, 100, 50);
        checkButton.setBackground(panel.getDuoNavyBlue());
        checkButton.setForeground(Color.white);
        checkButton.setFont(panel.getFont().deriveFont(30f));
        checkButton.setOpaque(true);
        checkButton.setBorder(BorderFactory.createLineBorder(panel.getDuoNavyBlue(), 4));
        checkButton.addActionListener(this);
    }
    public void splitLines(JLabel jl, int fontSize){
        //1.7 ratio to pixel size each char of the font takes up
        fontSize/=1.7;

        // checking if the sentence is too long and split it up into multiple jlabels
        // if the second one is still to big you split it up into another 2 labels using recursion
        if(fontSize*jl.getText().length()>jl.getWidth()){
            int cutOff = jl.getText().substring(0, jl.getWidth()/fontSize).lastIndexOf(" ");
            String line1 = jl.getText().substring(0, cutOff);
            String line2 = jl.getText().substring(cutOff);
            jl.setText(line1);
            word.add(new JLabel(line2, SwingConstants.CENTER));
            word.get(word.size()-1).setBounds(jl.getX(), jl.getY()+fontSize+10, jl.getWidth(), jl.getHeight());
            word.get(word.size()-1).setFont(panel.getFont().deriveFont(20f));
            word.get(word.size()-1).setForeground(Color.white);
            splitLines(word.get(word.size()-1), fontSize);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // checks if the check button has been clicked and 
        // checks if the formed sentence is equal to the answer
        if(e.getSource()==checkButton){
            gameScreen.continueButton.setEnabled(true);
            if(formedWord.getText().equalsIgnoreCase(" "+answer)){
                for(JToggleButton[] buttons1 : buttons){
                    for(JToggleButton b: buttons1){
                        b.setEnabled(false);
                        b.setSelected(false);
                        b.setBackground(panel.getDuoGreen());
                    }
                }
                gameScreen.continueButton.setBackground(panel.getDuoGreen());
                checkButton.setBackground(panel.getDuoGreen());
                checkButton.setEnabled(false);
                gameScreen.score++;
            }
            else{
                for(JToggleButton[] buttons1 : buttons){
                    for(JToggleButton b: buttons1){
                        b.setEnabled(false);
                        b.setSelected(false);
                        b.setBackground(panel.getDuoRed());
                    }
                }
                gameScreen.continueButton.setBackground(panel.getDuoRed());
                checkButton.setBackground(panel.getDuoRed());
                checkButton.setEnabled(false);
            }
        }
    }
    // add the button pressed to the formed sentence
    @Override
    public void itemStateChanged(ItemEvent e) {
        for(int i = 0; i<buttons.length;i++){
            for(int j = 0; j<buttons[i].length; j++){
                if(buttons[i][j].isSelected()&&!added[i][j]){
                    formedWord.setText(formedWord.getText()+" "+buttons[i][j].getText());
                    added[i][j] = true;
                }
                else if(!buttons[i][j].isSelected()&&added[i][j]){
                    formedWord.setText(formedWord.getText().replace(" "+buttons[i][j].getText(), ""));
                    added[i][j] = false;
                }
            }
        }
    }
    // add all compomponents to the panel
    @Override
    public void addComponents(){
        for(JToggleButton[] buttons1 : buttons){
            for(JToggleButton b: buttons1){
                panel.add(b);
            }
        }
        for(JLabel i: word){
            panel.add(i);
        }
        panel.add(formedWord);
        panel.add(question);
        panel.add(checkButton);
    }
}
