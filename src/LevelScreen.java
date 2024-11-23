import javax.swing.JButton;

public class LevelScreen {
    Panel panel;

    JButton[] levels = new JButton[6];
    public LevelScreen(Panel panel) {
        this.panel = panel;
        for(int i = 0; i<levels.length; i++){
            levels[i] = new JButton(String.valueOf((i+1)));
            levels[i].setBounds(i>2?230:70, i>2?((i-2)*150):((i+1)*150), 100, 100);
        }
    }
    public void addComponents(){
        for(int i = 0; i<levels.length; i++){
            panel.add(levels[i]);
            levels[i].setVisible(true);
        }
    }
}
