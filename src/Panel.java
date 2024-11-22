import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Panel extends JPanel implements Runnable, ActionListener{
    final int screenWidth = 400;
    final int screenHeight = 650;

    private final int FPS = 60;

    private final JTextField userName;
    private final JButton userButton;

    UsernameSaver usernameSaver = new UsernameSaver();
    Thread gameThread;

    public Panel() {
        setPreferredSize(new Dimension(screenWidth,screenHeight));
        setBackground(new Color(34, 42, 51));

        userName = new JTextField();
        userName.setBounds(100,300,200,50);

        userButton = new JButton("Enter Username");
        userButton.setBounds(100,400,200,50);
        userButton.addActionListener(this);

        setLayout(null);
        add(userName);
        add(userButton);
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
    public void update(){
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == userButton) {
            String enteredUsername = userName.getText().toLowerCase();
            System.out.println("Entered username: " + enteredUsername);
            usernameSaver.addUsername(enteredUsername);
        }
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