import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

// UsernameSaver Class to save username and the levels finished attached to the name
public class UsernameSaver {
    private final String FILE_NAME = "src/usernames.txt"; // Initializing the file name as a string
    
    private ArrayList<String> usernames; // declaring a arraylist of strings 
    // because it is flexable and can easily add or subtract from the list since it is dynamic
    
    private ArrayList<ArrayList<Integer>> listLevelsFinished; // creating a 2D arraylist of integers 
    // which are the levels finished by each player
    // once again we use arraylist because it is dynamic

    private String username; // Declaring a username String which will be the name of the current user
    private ArrayList<Integer> levelsFinished; // Declaring a Integer Arraylist of the levelsfinished by the current user
    private int userIndex; // Declaring an integer for the current player's index in the arraylist

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
        loadUsernamesAndLevels(); // loads the username and levels 
        // finished into the arraylist once the game is started
    }
    public void loadUsernamesAndLevels() {
        usernames = new ArrayList<>();
        listLevelsFinished = new ArrayList<>();
        try{
            FileReader file = new FileReader(FILE_NAME); // Initializing the file we want to read which acts as our database
            Scanner sc = new Scanner(file); // creating a scanner object for the file
            while(sc.hasNextLine()) {
                usernames.add(sc.nextLine()); // adding the read username to the usernames arraylist
                listLevelsFinished.add(new ArrayList<>()); // adding the read integers to the inner array
                while(sc.hasNextInt()){ // checking if the user has finished more than 1 level to add those levels into the arraylist
                    listLevelsFinished.get(listLevelsFinished.size()-1).add(sc.nextInt()); // getting the index of the user level list and adding the next int to that list
                }
                if(sc.hasNextLine()){
                    sc.nextLine(); // goes to the next line if there is a next line since sc.nextInt() 
                    // does not go to the next Line
                }
            }
            // closing both scanner and file reader
            file.close(); 
            sc.close();
        }catch(IOException e){}
    }
    // saving username to the "database"
    public void saveUser() {
        // initializing a printwriter to print text into the text file
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (int i = 0; i<usernames.size();i++) {
                // printing the usernames in the arraylist in the text file
                writer.println(usernames.get(i));
                // print the levels finished by the player in one line
                for(int j = 0; j<listLevelsFinished.get(i).size(); j++){
                    writer.print(listLevelsFinished.get(i).get(j)+" ");
                }
                writer.println(); // add a blank space between each player & levels finished
            }
        } catch (IOException e) {}
    }
    // adding username of the current player into the arraylist of usernames
    public void addUsername(String username) {
        // if the current player was already registered dont add the name in the array list
        if (!usernames.contains(username)) { 
            usernames.add(username);
            listLevelsFinished.add(new ArrayList<>()); // adding a new levelsfinished list for the current new player
            saveUser(); // Calling the saveUser method to save the player in the text file
        }
        this.username = username; // making the class variable username equal to username as the argument
        this.userIndex = usernames.indexOf(username); // saves the userIndex of the player in the list of players
        this.levelsFinished = listLevelsFinished.get(userIndex); // saves the list of finished levels
    }
    // a is the level that was just finished
    public void updateLevels(int a){
        // if a is newly finished then add it to the list of finished
        // add the list of finshed by the player at the index of the user to the 2d list of finished levels by all players
        if(!levelsFinished.contains(a)){
            levelsFinished.add(a);
            listLevelsFinished.set(userIndex, levelsFinished);
            saveUser(); // call saveUser to save the levels finished
        }
    }
}
