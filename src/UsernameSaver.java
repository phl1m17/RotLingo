import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UsernameSaver {
    private final String FILE_NAME = "src/usernames.txt";
    private ArrayList<String> usernames;
    private String username;

    //getters
    public String getUsername(){
        return username;
    }
    public ArrayList<String> getList(){
        return usernames;
    }
    public UsernameSaver() {
        usernames = loadUsernames();
    }
    public ArrayList<String> loadUsernames() {
        ArrayList<String> names = new ArrayList<>();
        try{
            FileReader file = new FileReader(FILE_NAME);
            Scanner sc = new Scanner(file);
            while(sc.hasNext()) {
                names.add(sc.next());
            }
            file.close();
            sc.close();
        }catch(IOException e){}
        return names;
    }
    public void saveUsernames() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
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
        this.username = username;
    }
}
