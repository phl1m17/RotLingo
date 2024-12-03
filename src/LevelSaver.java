import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class LevelSaver {
    private final String FILE_NAME = "src/level.txt";
    private ArrayList<String> usernames;
    private String username;
    private ArrayList<Integer> userLevel;

    // Getter
    public int level(String username){
        return userLevel.get(usernames.indexOf(username));
    }

    public LevelSaver() {
        userLevel = loadLevel();
    }
    public ArrayList<Integer> loadLevel(){
        ArrayList<Integer> level = new ArrayList<>();
        try{
            FileReader file = new FileReader(FILE_NAME);
            Scanner sc = new Scanner(file);
            while(sc.hasNext()) {
                level.add(Integer.valueOf(sc.next()));
            }
            file.close();
            sc.close();
        } catch(IOException e){}
        return level;
    }
    public void saveLevels() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (int lvl : userLevel) {
                writer.println(lvl);
            }
        } catch (IOException e) {}
    }
}
