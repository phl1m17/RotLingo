public final class GameScreen{
    int questionCount = 0;

    int levels;

    Question[] questions = new Question[8];

    public GameScreen(Panel panel) {
        String[] opts = {"reality", "vibe level", "Tommy", "standard"};
        questions[questionCount] = new MultipleChoiceQuestion(this, panel, "Unc", opts, 2);
    }
    public void addComponents(){
        questions[questionCount].setQuestion("Question " + (questionCount+1));
        questions[questionCount].addComponents();
    }
}