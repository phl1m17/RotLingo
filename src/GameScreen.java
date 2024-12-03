public final class GameScreen{
    int questionCount = 0;

    int levels;

    Question[] questions = new Question[8];

    public GameScreen(Panel panel) {
        String[][] opts = {{"Aura", "Unc", "No Cap", "Rizz", "Sigma"},{"Cool Points", "Older Male", "Not Lying", "Charisma", "Cool Person"}};
        questions[questionCount] = new MatchingQuestion(panel, this, opts);
    }
    public void addComponents(){
        questions[questionCount].setQuestion("Question " + (questionCount+1));
        questions[questionCount].addComponents();
    }
}