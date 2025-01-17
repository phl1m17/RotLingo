// Author: Shams C. and Phil M.
// Purpose: Panel controls swtiching through each screen, constants, thread

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import javax.swing.JPanel;

public final class Panel extends JPanel implements Runnable{
    // Screen Dimensions
    final int screenWidth = 400;
    final int screenHeight = 650;

    // Setting the FPS of the game / How many times it repaints in a second
    private final int FPS = 60;

    // 0 = enter username
    // 1 = level screen
    // 2 >= game screen (level Index = gamePhase-2)
    private int gamePhase = 0;

    // Initializing default values such as colors and font
    private Font font = importFont();
    private final Color duoGreen = new Color(88,204,2);
    private final Color duoRed = new Color(255,75,75);
    private final Color duoNavyBlue = new Color(24, 32, 39);
    private final Color duoBlue = new Color(28, 176, 246);

    // Creating an instance of the UsernameSaver Class which saves the username and level
    UsernameSaver usernameSaver = new UsernameSaver();

    //Screens
    UserScreen userScreen = new UserScreen(this);
    LevelScreen levelScreen = new LevelScreen(this);
    GameScreen gameScreen = new GameScreen(this);

    // Declaring a thread object
    Thread gameThread;

    //getters
    @Override
    public Font getFont(){
        return font;
    }
    public Color getDuoGreen(){
        return duoGreen;
    }
    public Color getDuoRed(){
        return duoRed;
    }
    public Color getDuoNavyBlue(){
        return duoNavyBlue;
    }
    public Color getDuoBlue(){
        return duoBlue;
    }
    public int getGamePhase(){
        return gamePhase;
    }
    public Font importFont() {
        try {
            File file = new File("src/FeatherBold.ttf"); // creating a file object of the font file
            // creating a font using the .createFont() method which accepts a file and an integer, then you 
            // also specify the font size using the deriveFont method which accepts a float
            font = Font.createFont(Font.TRUETYPE_FONT, file).deriveFont(12f);
            return font;
        } catch (FontFormatException | IOException e) {}
        return null;
    }
    // Default Constructor
    public Panel() {
        // sets the dimensions of the JPanel
        // setPrefferedSize requires a Dimension parameter which 
        // is why you have a new Dimension() with the width and height
        setPreferredSize(new Dimension(screenWidth,screenHeight));
        
        // Setting the Background color for the screen
        setBackground(new Color(34, 42, 51));
        
        // Allows you to put the buttons in specific coordinates.
        setLayout(null);

        // calling changePhase method with username screen as the 
        // argument since you want to start off in the username screen
        changePhase(0);
    }
    // Change phase method
    public void changePhase(int phase){
        removeAll(); // removing all components added to the JPanel
        gamePhase = phase; // making gamePhase equal to the argument phase
        // added the needed components base on the gamePhase
        if(gamePhase == 0){
            userScreen.addComponents();
        }
        else if(gamePhase == 1){
            levelScreen.addComponents(); 
        }
        else if(gamePhase > 1){
            gameScreen.levelStart(gamePhase);
            gameScreen.addComponents();
        }
    }
    // Start GameThread
    public void startGameThread(){
        gameThread = new Thread(this); // Initilizing the gameThread 
        // with this as in the runnable interface which is implemented in the class
        gameThread.start(); // .start() is a method in the Thread class
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(gamePhase > 1) {
            gameScreen.paint(g);
        }
    }
    // run is an abstract method in the runnable interface
    @Override
    public void run() {
        // DELTA GAME LOOP
        // drawInterval determines how much time should pass between frames
        // It is calculated based on the desired FPS (frames per second)
		final double drawInterval = 1000000000/FPS; // setting up how many times a second it repaints
		double delta = 0; // delta keeps track of the accumulated time difference between frames
		long lastTime = System.nanoTime();
		while(gameThread.isAlive()) {
            // Get the current time in nanoseconds
			long currentTime = System.nanoTime();

			delta += (currentTime-lastTime)/drawInterval; // how much time has passed 
            //divided by how many times we want to draw per second
			
            lastTime = currentTime; // update lastTime to the current time for the next iteration
			
            // checking if enough time has passed before repainting
			if(delta>=1) {
				repaint();
				delta--; // subtract delta by 1 to indicate that you refreshed the page
			}
		}
    }
}