import java.awt.Color;

import javax.swing.JButton;

public class LevelScreen {
    Panel panel;

    JButton[] levels = new JButton[6];
    public LevelScreen(Panel panel) {
        this.panel = panel;
        for(int i = 0; i<levels.length; i++){
            levels[i] = new JButton(String.valueOf((i+1)));
            levels[i].setBounds((i<2?((i+1)):(i<4?(i-1):(i-3)))*150-70, (i<2?70:(i<4?230:390))+100, 100, 100);
            levels[i].setBackground(panel.getDuoGreen());
            levels[i].setForeground(Color.white);
            levels[i].setFont(panel.getFont().deriveFont(20f));
            levels[i].setForeground(Color.white);
        }
    }
    public void addComponents(){
        for(int i = 0; i<levels.length; i++){
            panel.add(levels[i]);
            levels[i].setVisible(true);
        }
    }
}
