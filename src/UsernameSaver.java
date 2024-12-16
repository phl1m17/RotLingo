import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UsernameSaver {
    private final String FILE_NAME = "src/usernames.txt";
    private ArrayList<String> usernames;
    private ArrayList<ArrayList<Integer>> listLevelsFinished;
    private String username;
    private ArrayList<Integer> levelsFinished;
    private int userIndex;

    //getters
    public ArrayList<Integer> getLevelsFinished(){
        return levelsFinished;
    }
    public String getUsername(){
        return username;
    }
    public ArrayList<String> getList(){
        return usernames;
    }
    public UsernameSaver() {
        loadUsernamesAndLevels();
        System.out.println(usernames);
        System.out.println(listLevelsFinished);
    }
    public void loadUsernamesAndLevels() {
        usernames = new ArrayList<>();
        listLevelsFinished = new ArrayList<>();
        try{
            FileReader file = new FileReader(FILE_NAME);
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()) {
                usernames.add(sc.nextLine());
                listLevelsFinished.add(new ArrayList<>());
                while(sc.hasNextInt()){
                    listLevelsFinished.get(listLevelsFinished.size()-1).add(sc.nextInt());
                }
                if(sc.hasNextLine()){
                    sc.nextLine();
                }
            }
            file.close();
            sc.close();
        }catch(IOException e){}
    }
    public void saveUser() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (int i = 0; i<usernames.size();i++) {
                writer.println(usernames.get(i));
                for(int j = 0; j<listLevelsFinished.get(i).size(); j++){
                    writer.print(listLevelsFinished.get(i).get(j)+" ");
                }
                writer.println();
            }
        } catch (IOException e) {}
    }
    public void addUsername(String username) {
        if (!usernames.contains(username)) {
            usernames.add(username);
            listLevelsFinished.add(new ArrayList<>());
            saveUser();
        }
        this.username = username;
        this.userIndex = usernames.indexOf(username);
        this.levelsFinished = listLevelsFinished.get(userIndex);
    }
    public void updateLevels(int a){
        if(!levelsFinished.contains(a)){
            levelsFinished.add(a);
            listLevelsFinished.set(userIndex, levelsFinished);
            saveUser();
        }
    }
}
