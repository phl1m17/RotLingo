
import javax.swing.JFrame;

public class Main{
    public static void main(String[] args){
        JFrame window = new JFrame("SlangSnap");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Panel p = new Panel();
        p.startGameThread();
        
        window.add(p);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
