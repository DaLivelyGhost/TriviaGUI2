import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class QuestionDatabase {

      //This is the Middle man for all database interactions. All queries to the database will go through here
     //because this class optimally will handle exceptions and null pointer exceptions to keep that nonsense from cluttering
    //up the main code.

    //ALL INCOMING ROWS MUST BE FORMATTED WITH
        //Question, answer1, answer2, answer3, answer4, int for correct choice.
            //I'm just lazy and don't want to implement more error checking.

    //Functions Quick Access--------------------

    //dropTable()
        //clears the current working table. Currently required upon application close (still working on it)
        //CREATE TABLE IF NOT EXISTS for some reason just creates a table regardless if the table exists or not.

    //getTable()
        //Returns the entire working table's resultSet. CAN RETURN NULL, WATCH OUT

    //getEntry(int id)
        //Returns an Object[] of the specified row in the table. Use this for getting a question for a room.
        //The id for the row will be the rowid of the entry, which means that passing in 1 will get you the first
        //row and 2 the second, and so on.

    //setEntry(int id, String[] toEnter)
        //Sets the entry identified by the id to whatever you make toEnter. [WIP]

    //SetConnection()
        //Private. Establishes a connection to the database file.
    
    //SetClosed()
        //Private. Needs to be called in every method. Closes the connection
        //to prevent leaving a query open between method calls.

    FileReader fileIn;
    BufferedReader fileReader;
    Connection dbCon;
    Statement stmt;          //DDL (really only used for database creation
    PreparedStatement dml;  //data management (insert, update, select)

    int maxQuestions; //The amount of questions currently in the database

    final String DATABASE_FILE = "jdbc:sqlite:QuestionDatabase.db";
    final String TABLE_DROP = "DROP TABLE questions";
    final String TABLE_CREATE = "CREATE TABLE IF NOT EXISTS questions (\n"
            + "Question varchar(255), \n"
            + "Answer1 varchar(255), \n"
            + "Answer2 varchar(255), \n"
            + "Answer3 varchar(255), \n"
            + "Answer4 varchar(255), \n"
            + "Correct int \n"
            +");";

    public QuestionDatabase(String fileName) {
        this.maxQuestions = 0;
        setFile(fileName);
    }
    //-----------------------------------------------
    //Setters
    //-----------------------------------------------

    //main purpose for this method is so that constructing a QuestionDatabase object
    //doesn't require a try catch block. It's public in case we want to be able to switch files at runtime.
    public void setFile(String fileName) {

        try {
            this.fileIn = new FileReader(fileName);
        }
        catch(FileNotFoundException e) {
            this.fileIn = null;
            System.out.println(e);
        }

        convertDatabase(fileIn);
    }
    private void setConnection() {
        //This try catch block will build the .db (database) file
        //and then create a table for the questions/answers.
        try {
            this.dbCon = DriverManager.getConnection(DATABASE_FILE);

            if(dbCon != null) {
                DatabaseMetaData mData = this.dbCon.getMetaData();
                this.stmt = this.dbCon.createStatement();
                this.stmt.execute(TABLE_CREATE);
            }
        }
        catch(SQLException e) {
            e.getMessage();
            System.out.println(e);
        }
    }
    private void setClosed() {
        try {
            this.dbCon.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
    }
    public void setEntry(int id, String[] toEnter) {

        setConnection();

        try {
            this.dml = this.dbCon.prepareStatement("UPDATE questions SET Question = ?,"
                                                    + "\t" + "Answer1 = ?,"
                                                    + "\t" + "Answer2 = ?,"
                                                    + "\t" + "Answer3 = ?,"
                                                    + "\t" + "Answer4 = ?,"
                                                    + "\t" + "Correct = ?"
                                                    + "\t" + "WHERE rowid = ?;");
            this.dml.setString(1, toEnter[0]);
            this.dml.setString(2, toEnter[1]);
            this.dml.setString(3, toEnter[2]);
            this.dml.setString(4, toEnter[3]);
            this.dml.setString(5, toEnter[4]);
            this.dml.setInt(6, Integer.parseInt(toEnter[5]));
            this.dml.setInt(7, id);
            this.dml.executeUpdate();

        } catch (SQLException throwables) {

            throwables.printStackTrace();

        }
        setClosed();
    }
    //-----------------------------------------------
    //Getters
    //-----------------------------------------------
    public Object[] getEntry(int id) {

        setConnection();

        ResultSet r;
        Object[] toReturn = new Object[6];
        try {

            this.dml = this.dbCon.prepareStatement("SELECT * from questions where rowid = ?");
            this.dml.setInt(1, id);
            r = this.dml.executeQuery();

            try {
                toReturn[0] = r.getString("Question");
                toReturn[1] = r.getString("Answer1");
                toReturn[2] = r.getString("Answer2");
                toReturn[3] = r.getString("Answer3");
                toReturn[4] = r.getString("Answer4");
                toReturn[5] = r.getInt("Correct");
            } catch(Exception e) {
                toReturn = new Object[0];
            }

        } catch (SQLException throwables) {

            throwables.printStackTrace();

        }
        setClosed();

        return toReturn;
    }
    public ResultSet getTable() {
        setConnection();
        try {
            this.dml = this.dbCon.prepareStatement("SELECT * from questions");
            ResultSet r = this.dml.executeQuery();
            return r;
        }
        catch(Exception e) {
            e.getMessage();
            System.out.println(e);
        }
        setClosed();
        return null;
    }

    //-----------------------------------------------
    //Other Methods
    //-----------------------------------------------
    public void dropTable() {
        setConnection();

        try {
            this.stmt.execute(TABLE_DROP);

        } catch (SQLException throwables) {

            throwables.printStackTrace();
        }
        setClosed();
    }

    //Called by the setFile. Will convert the .csv file into a sql compatible database.
    //This is called by setFile instead of the constructor so that if the file for questions is switched at run time
    //the questiondatabase object will automatically convert to sql.
    private void convertDatabase(FileReader fileIn) {

        setConnection();

        //This try catch block will read the information from the csv file
        //and then convert to sql.
        try {

            fileReader = new BufferedReader(fileIn);
            String line = "";
            int lineId;

            while((line = fileReader.readLine()) != null) {
                this.maxQuestions++;
                    //Some of the data is a quote with a comma in it, so it needs to be split manually.

                if(line.contains("\"")) {
                    int commaPosition1 = 0;
                    int commaPosition2 = 0;
                    String splitString;
                    this.dml = this.dbCon.prepareStatement("INSERT INTO questions(Question, Answer1, Answer2, Answer3, Answer4, Correct) VALUES(?, ?, ?, ?, ?, ?)");

                    for(int i = 1; i < 6; i++) {

                        if(line.charAt(0) == '"') {
                            commaPosition2 = line.indexOf("\"", 1) + 1;
                            splitString = line.substring(commaPosition1, commaPosition2);
                            this.dml.setString(i, splitString);
                            line = line.substring(commaPosition2 + 1);
                        }
                        else {
                            commaPosition2 = line.indexOf(",");
                            splitString = line.substring(commaPosition1, commaPosition2);
                            this.dml.setString(i, splitString);
                            line = line.substring(commaPosition2 + 1);
                        }
                    }
                    this.dml.setInt(6, Integer.parseInt(line));
                    this.dml.executeUpdate();
                }
                //If there is no quote in the current entry, this can be safely split and added automatically.
                else {
                    String[] fileValues = line.split(",");
                    this.dml = this.dbCon.prepareStatement("INSERT INTO questions(Question, Answer1, Answer2, Answer3, Answer4, Correct) VALUES(?, ?, ?, ?, ?, ?)");
                    this.dml.setString(1, fileValues[0]);
                    this.dml.setString(2, fileValues[1]);
                    this.dml.setString(3, fileValues[2]);
                    this.dml.setString(4, fileValues[3]);
                    this.dml.setString(5, fileValues[4]);
                    this.dml.setInt(6, Integer.parseInt(fileValues[5]));
                    this.dml.executeUpdate();
                }
            }

        }
        catch(Exception e) {

        }
        setClosed();
    }
}
