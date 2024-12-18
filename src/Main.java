import javax.swing.JFrame;

// Main Class with the main method
public class Main{
    public static void main(String[] args){
        // Creating the JFrame
        JFrame window = new JFrame("RotLingo");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Makes sure that the program is terminated once closed 
        // instead of it running in the background
        
        // Creating an instance of the Panel class
        Panel p = new Panel();
        p.startGameThread(); // calling the startGameThread method 
        // in the panel class which starts the game thread once called
        
        // adding the JPanel to the JFrame since Panel extends JPanel
        window.add(p);
        window.pack(); // This allows the dimensions of the JPanel to 
        // be used as the Frame dimensions

        // setting the location of the screen in the centre of the monitor
        window.setLocationRelativeTo(null);
        window.setVisible(true); // setting the visibility to true
    }
}
