public class GamePlayCycle {
    Maze maze;
    TriviaGUI gui;
    int cUp, cLeft, cDown, cRight;  //the possible places to move as a player.
    int charX;
    int charY;
    public GamePlayCycle(QuestionDatabase database) {
        this.maze = new Maze(database);
        this.gui = new TriviaGUI(this);
        this.charX = 0;
        this.charY = 0;
        startGame();
    }
    protected int xGetPlayerLocation() {
       return this.charX;
    }
    protected int yGetPlayerLocation() {
        return this.charY;
    }
    protected boolean getLocked(int x, int y) {
        return this.maze.Maze[x][y].isLocked;
    }
    //Used for the cheat
    protected int getCorrectAnswer(int x, int y) {
        return this.maze.Maze[x][y].CorrectAnswer;
    }

    //Called by the GamePlayCycle Constructor.
    //Probably needs more work.
    protected void startGame() {
        this.gui.setPlayerOptions(this.charX, this.charY);
        String[] Question = this.maze.getRoomData(this.charX, this.charY);
        this.gui.setQuestion(Question[0]);
        this.gui.setAnswers(Question[1], Question[2], Question[3], Question[4]);
        this.gui.setBunnyLocation(this.charX, this.charY);
    }

    //This is called when a room button is pressed.
    //This will send the data from the maze to the gui to use for that room.
    protected void roomEntry(int x, int y) {
        String[] Question = this.maze.getRoomData(x, y);
        this.gui.setQuestion(Question[0]);
        this.gui.setAnswers(Question[1], Question[2], Question[3], Question[4]);
    }

    //This is called when the submit button is pressed.
    //Checks for if the answer is correct or not. If it is correct, it moves to the highlighted room.
    //If not correct, the room locks, and "room locked" is displayed
    protected boolean submit(int x, int y, int playerAnswer) {
        //if right
        if(this.maze.Maze[x][y].checkAnswer(playerAnswer)){
            move(x, y);
            this.gui.setPlayerOptions(x,y);
            return true;
        }
        //if wrong
        else {
            this.maze.Maze[x][y].setLocked();
            //The loaded data below is the locked room data.
            String[] Question = this.maze.getRoomData(x, y);
            this.gui.setQuestion(Question[0]);
            this.gui.setAnswers(Question[1], Question[2], Question[3], Question[4]);
            return false;
        }
    }
    protected boolean checkWinnable() {
        boolean winnable = this.maze.isWinnable(charX, charY);
        this.maze.clean();
        return winnable;
    }
    protected void move(int x, int y) {
        this.charX = x;
        this.charY = y;
        this.gui.setBunnyLocation(x, y);
        if(this.charX == 3 && this.charY == 3) {
            this.gui.gameIsWon();
        }
    }
}
