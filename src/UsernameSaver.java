import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UsernameSaver {
    private final String FILE_NAME = "usernames.txt";
    private ArrayList<String> usernames;

    public UsernameSaver() {
        usernames = loadUsernames();
    }
    public ArrayList<String> loadUsernames() {
        ArrayList<String> names = new ArrayList<>();
        try{
            FileReader file = new FileReader("src/usernames.txt");
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
}
