import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Panel extends JPanel implements Runnable, ActionListener{
    final int screenWidth = 400;
    final int screenHeight = 650;

    private final int FPS = 60;
    private int introScreenDelay = 0;
    private int introScreenWidth = 400;
    private int introScreenHeight = 650;
    private int introScreenX = 0;
    private int introScreenY = 0;

    private final JTextField userName;
    private final JButton userButton;

    private ArrayList<String> usernames = new ArrayList<>();

    Thread gameThread;

    public Panel() {
        setPreferredSize(new Dimension(screenWidth,screenHeight));
        setBackground(new Color(34, 42, 51));

        userName = new JTextField();
        userName.setBounds(100,300,200,50);

        userButton = new JButton("Enter Username");
        userButton.setBounds(100,400,200,50);
        userButton.addActionListener(this);

        usernames = loadUsernames();
        setLayout(null);
        add(userName);
        add(userButton);
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        mainScreen(g);
    }
    public void mainScreen(Graphics g){
        g.setColor(new Color(25, 227, 38));
        g.fillRect(introScreenX, introScreenY, introScreenWidth, introScreenHeight);
        int introDelay = 30;
        if(introScreenDelay>=90){
            introDelay++;
            introScreenWidth -= introDelay;
            introScreenHeight -= introDelay*1.625;
            introScreenX += introDelay/2;
            introScreenY += introDelay;
        }
    }
    public void update(){
        introScreenDelay++;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == userButton) {
            String enteredUsername = userName.getText().toLowerCase();
            System.out.println("Entered username: " + enteredUsername);
            addUsername(enteredUsername);
        }
    }
    private ArrayList<String> loadUsernames() {
        ArrayList<String> names = new ArrayList<>();
        File file = new File("src/usernames.txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    names.add(line.trim());
                }
            } catch (IOException e) {}
        }
        return names;
    }

    public void saveUsernames() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("src/usernames.txt"))) {
            for (String name : usernames) {
                writer.println(name);
            }
        } catch (IOException e) {}
    }

    public void addUsername(String username) {
        if (!usernames.contains(username)) {
            usernames.add(username);
            saveUsernames();
        } else {
            System.out.println("Username already exists: " + username);
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