import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.InputStream;

import javax.swing.JPanel;

public class Panel extends JPanel implements Runnable, ActionListener{
    final int screenWidth = 400;
    final int screenHeight = 650;

    private final int FPS = 60;

    // 0 = enter username
    // 1 = level screen
    private int gamePhase = 0;

    Font font = importFont();
    Color duoGreen = new Color(88,204,2,255);

    //Screens
    UserScreen userScreen = new UserScreen(this);
    LevelScreen levelScreen = new LevelScreen(this);

    UsernameSaver usernameSaver = new UsernameSaver();
    Thread gameThread;

    //getters
    public Font getFont(){
        return font;
    }
    public Color getDuoGreen(){
        return duoGreen;
    }

    public Font importFont() {
        try {
            File file = new File("src/FeatherBold.ttf");
            Font font = Font.createFont(Font.TRUETYPE_FONT, file).deriveFont(12f);
            System.out.println("aa");
            return font;
        } catch (Exception e) {}
        return null;
    }
    public Panel() {
        setPreferredSize(new Dimension(screenWidth,screenHeight));
        setBackground(new Color(34, 42, 51));

        setLayout(null);
        changePhase(0);
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
    public void update(){
        
    }
    public void changePhase(int phase){
        System.out.println(phase);
        removeAll();
        gamePhase = phase;
        switch(gamePhase){
            case 0:
                userScreen.addComponents();
                break;
            case 1:
                levelScreen.addComponents(); 
                break;
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        // DELTA GAME LOOP
		final double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		
		while(gameThread.isAlive()) {
			long currentTime = System.nanoTime();
			
			delta += (currentTime-lastTime)/drawInterval; // how much time has passed 
                //divided by how many times we want to draw per second
			lastTime = currentTime;
			
			if(delta>=1) {
				update();
				repaint();
				delta--;
			}
		}
    }
}

// Game Loop Source
// https://stackoverflow.com/questions/29523750/gameloop-fps-controller