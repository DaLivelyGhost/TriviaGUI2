import java.util.Random;

public class Maze {
    final int MAZE_X_DIMENSION = 4;
    final int MAZE_Y_DIMENSION = 4;
    final int dimX = MAZE_X_DIMENSION - 1;
    final int dimY = MAZE_Y_DIMENSION - 1;
    Room[][] Maze;
    QuestionDatabase database;
    Random r;

    public Maze(QuestionDatabase qdb) {
        this.database = qdb;
        r = new Random();
        //Might not hard code this in the future.
        this.Maze = new Room[MAZE_X_DIMENSION][MAZE_Y_DIMENSION];

        for(int i = 0; i < MAZE_X_DIMENSION; i++){
            for(int j = 0; j < MAZE_Y_DIMENSION; j++){

                this.Maze[i][j] = new Room(i, j);

                if(i == 0 && j == 0) {
                    //create an empty room. This will be the starting room.
                }
                else {
                    int tempid = r.nextInt(this.database.maxQuestions ) + 1;     //random number for random question id. (between 1 and the max amount)
                    Object[] tempQuestion = this.database.getEntry(tempid);          //Acquire the random question.

                    this.Maze[i][j].setQuestionData((String) tempQuestion[0], (String) tempQuestion[1], (String) tempQuestion[2],
                            (String) tempQuestion[3], (String) tempQuestion[4], (int) tempQuestion[5]);
                }
            }
        }
        this.Maze[3][3].exit = true;
    }
    public String[] getRoomData(int x, int y) {
        return this.Maze[x][y].getQuestion();
    }
    //Returns true if the game is still winnable
    public boolean isWinnable(int x, int y) {

        //mark the room as stepped into
        this.Maze[x][y].winnableChecked = true;

        //------------------------
            //BASE CASE (successful)
        //------------------------
        if(this.Maze[x][y].exit) {
            return true;
        }

        if(x + 1 <= dimX) {
            if(!this.Maze[x + 1][y].isLocked && this.Maze[x + 1][y].winnableChecked == false) {
                x++;
                return isWinnable(x, y);
            }
        }
        if(y + 1 <= dimY) {
            if(!this.Maze[x][y + 1].isLocked && this.Maze[x][y + 1].winnableChecked == false) {
                y++;
                return isWinnable(x, y);
            }
        }
        if(x - 1 >= 0) {
            if(!this.Maze[x - 1][y].isLocked && this.Maze[x - 1][y].winnableChecked == false) {
                x--;
                return isWinnable(x, y);
            }
        }
        if(y - 1 >= 0) {
            if(!this.Maze[x][y - 1].isLocked && this.Maze[x][y - 1].winnableChecked == false) {
                y--;
                return isWinnable(x, y);
            }
        }

        //This return statement will only be reached once all other options fail.
        //Consider it a 2nd base case, but for failure.

        return false;
    }
    //this is called from checkWinnable.
    //resets the winnableChecked tag on each room.
    protected void clean() {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                this.Maze[i][j].winnableChecked = false;
            }
        }
    }
    public void setDatabase(QuestionDatabase qdb) {
        this.database = qdb;
    }
}
