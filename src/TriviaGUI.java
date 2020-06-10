import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class TriviaGUI extends  JFrame {


    private Container c;
    private JLabel title;


    private int playerLocationX, playerLocationY;
    private int potentialMoveLocationX = 0 , potentialMoveLocationY = 0;
    private int playerAnswer = 0;
    private int correctAnswer;
    private int lastPressed;
    final private Icon playerIcon = new ImageIcon("resources\\Killer_Rabbit.png");
    final private Icon lockIcon = new ImageIcon("resources\\icons8-lock-64.png");
    final private Icon roomBkg = new ImageIcon("resources\\room.jpg");
    final private Icon GOD = new ImageIcon("resources\\GOD.jpg");
    private GamePlayCycle GamePlay;


    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JButton button10;
    private JButton button11;
    private JButton button12;
    private JButton button13;
    private JButton button14;
    private JButton button15;
    private JButton button16;
    private JButton[] buttonHolder;// = {button1,button2,button3,button4,button5,button6,button7,button8,button9,button10,button10,button11,button12,button13,button14,button15,button16};
    private JButton[][] buttHolder = {{button13,button9,button5,button1},{button14,button10,button6,button2},{button15,button11,button7,button3},{button16,button12,button8,button4}};
        //lmao butt
    private JButton submit;


    private ButtonGroup buttons;

    private JTextArea questionTextArea;
    private JRadioButton answerRadioButton1;
    private JRadioButton answerRadioButton4;
    private JRadioButton answerRadioButton3;
    private JRadioButton answerRadioButton2;


    private JLabel resultsTextArea;
    private JTextArea answerTextArea3;
    private JTextArea answerTextArea1;
    private JTextArea answerTextArea2;
    private JTextArea answerTextArea4;

    private boolean playerAnswerIsCorrect = false;
    private boolean highlightCorrectAnswer = false;



    //Menu
    private JMenuBar menuBar = new JMenuBar();
    private JMenu cheats = new JMenu();
    private JMenu settings = new JMenu();
    private String[] fontsHolder;
    private String font = "Arial";

    public TriviaGUI(GamePlayCycle gameplay){
        super("Trivia Maze");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        c = getContentPane();
        c.setLayout(null);
        this.GamePlay = gameplay;
        //menu stuff
        menuBar.add(getFileMenu());
        menuBar.add(getHelpMenu());
        this.setJMenuBar(menuBar);

        //Making and hardcodding the locations of everything
        makeQuestionAndAnswerPlace(c);
        buttHolder = new JButton[][] {{button13,button9,button5,button1},{button14,button10,button6,button2},{button15,button11,button7,button3},{button16,button12,button8,button4}};
        //Needed for GUI stuff
        setVisible(true);
    }


    //Just making all the btns and stuff

    private void makeQuestionAndAnswerPlace(Container c){


        title = new JLabel("Trivia Maze");
        title.setFont(new Font(font, Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(250, 10);
        c.add(title);

        Font f = new Font(font , Font.PLAIN , 16);

        button1 = new JButton();
        button1.setSize(100, 100);
        button1.setLocation(0,425);
        button1.setFont(f);
        button1.addActionListener(this::actionPerformed);
        c.add(button1);

        button2 = new JButton();
        button2.setSize(100, 100);
        button2.setLocation(100,425);
        button2.setFont(f);
        button2.addActionListener(this::actionPerformed);
        c.add(button2);

        button3 = new JButton();
        button3.setSize(100, 100);
        button3.setLocation(200,425);
        button3.setFont(f);
        button3.addActionListener(this::actionPerformed);
        c.add(button3);

        button4 = new JButton(GOD);
        button4.setSize(100, 100);
        button4.setLocation(300,425);
        button4.setFont(f);
        button4.addActionListener(this::actionPerformed);
        c.add(button4);

        button5 = new JButton();
        button5.setSize(100, 100);
        button5.setLocation(0,325);
        button5.setFont(f);
        button5.addActionListener(this::actionPerformed);
        c.add(button5);

        button6 = new JButton();
        button6.setSize(100, 100);
        button6.setLocation(100,325);
        button6.setFont(f);
        button6.addActionListener(this::actionPerformed);
        c.add(button6);

        button7 = new JButton();
        button7.setSize(100, 100);
        button7.setLocation(200,325);
        button7.setFont(f);
        button7.addActionListener(this::actionPerformed);
        c.add(button7);

        button8 = new JButton();
        button8.setSize(100, 100);
        button8.setLocation(300,325);
        button8.setFont(f);
        button8.addActionListener(this::actionPerformed);
        c.add(button8);

        button9 = new JButton();
        button9.setSize(100, 100);
        button9.setLocation(0,225);
        button9.setFont(f);
        button9.addActionListener(this::actionPerformed);
        c.add(button9);

        button10 = new JButton();
        button10.setSize(100, 100);
        button10.setLocation(100,225);
        button10.setFont(f);
        button10.addActionListener(this::actionPerformed);
        c.add(button10);

        button11 = new JButton();
        button11.setSize(100, 100);
        button11.setLocation(200,225);
        button11.setFont(f);
        button11.addActionListener(this::actionPerformed);
        c.add(button11);

        button12 = new JButton();
        button12.setSize(100, 100);
        button12.setLocation(300,225);
        button12.setFont(f);
        button12.addActionListener(this::actionPerformed);
        c.add(button12);

        button13 = new JButton();
        button13.setSize(100, 100);
        button13.setLocation(0,125);
        button13.setFont(f);
        button13.addActionListener(this::actionPerformed);
        c.add(button13);

        button14 = new JButton();
        button14.setSize(100, 100);
        button14.setLocation(100,125);
        button14.setFont(f);
        button14.addActionListener(this::actionPerformed);
        c.add(button14);

        button15 = new JButton();
        button15.setSize(100, 100);
        button15.setLocation(200,125);
        button15.setFont(f);
        button15.addActionListener(this::actionPerformed);
        c.add(button15);

        button16 = new JButton();
        button16.setSize(100, 100);
        button16.setLocation(300,125);
        button16.setFont(f);
        button16.addActionListener(this::actionPerformed);
        c.add(button16);

        submit = new JButton("SUBMIT!!");
        submit.setSize(120,20);
        submit.setLocation(500,500);
        submit.setFont(f);
        submit.addActionListener(this::actionPerformed);
        c.add(submit);


        questionTextArea = new JTextArea("The questions will go here. This is the best text holder I could find for the task.\n But it still allows the person to edit it");
        questionTextArea.setFont(new Font(font , Font.PLAIN , 14));
        questionTextArea.setSize(400 , 150);
        questionTextArea.setLocation(435, 75);
        questionTextArea.setLineWrap(true);

        c.add(questionTextArea);


        //These are the Circle buttons that will be used to choose an answer
        //ButtonGroup makes it so only one can be clicked at a time
        buttons = new ButtonGroup();


        answerRadioButton1 = new JRadioButton();
        answerRadioButton1.setLocation(435,240);
        answerRadioButton1.setSize(20,20);
        answerRadioButton1.setSelected(true);
        answerRadioButton1.addActionListener(this::actionPerformed);
        c.add(answerRadioButton1);
        buttons.add(answerRadioButton1);

        answerRadioButton2 = new JRadioButton();
        answerRadioButton2.setLocation(435,310);
        answerRadioButton2.setSize(20,20);
        answerRadioButton2.addActionListener(this::actionPerformed);
        c.add(answerRadioButton2);
        buttons.add(answerRadioButton2);

        answerRadioButton3 = new JRadioButton();
        answerRadioButton3.setLocation(435,380);
        answerRadioButton3.setSize(20,20);
        answerRadioButton3.addActionListener(this::actionPerformed);
        c.add(answerRadioButton3);
        buttons.add(answerRadioButton3);

        answerRadioButton4 = new JRadioButton();
        answerRadioButton4.setLocation(435,450);
        answerRadioButton4.setSize(20,20);
        answerRadioButton4.addActionListener(this::actionPerformed);
        c.add(answerRadioButton4);
        buttons.add(answerRadioButton4);

        //Answer TextAreas. They are placed to the right of their corresponding RadioButtons
        answerTextArea1 = new JTextArea("Answer goes here\n.................................................");
        answerTextArea1.setFont(new Font(font , Font.PLAIN , 14));
        answerTextArea1.setSize(350,50);
        answerTextArea1.setLocation(460,230);
        c.add(answerTextArea1);

        answerTextArea2 = new JTextArea("Holder text\n.................................................");
        answerTextArea2.setFont(new Font(font , Font.PLAIN , 14));
        answerTextArea2.setSize(350,50);
        answerTextArea2.setLocation(460,300);
        c.add(answerTextArea2);

        answerTextArea3 = new JTextArea("Holder text\n.................................................");
        answerTextArea3.setFont(new Font(font , Font.PLAIN , 14));
        answerTextArea3.setSize(350,50);
        answerTextArea3.setLocation(460,370);
        c.add(answerTextArea3);

        answerTextArea4 = new JTextArea("Holder text\n.................................................");
        answerTextArea4.setFont(new Font(font , Font.PLAIN , 14));
        answerTextArea4.setSize(350,50);
        answerTextArea4.setLocation(460,440);
        c.add(answerTextArea4);


        resultsTextArea = new JLabel("Choose an adjacent room");
        resultsTextArea.setFont(new Font(font , Font.PLAIN , 20));
        resultsTextArea.setSize(300,100);
        resultsTextArea.setLocation(0,0);
        c.add(resultsTextArea);

    }

    //Called from GamePlayCycle. Displays the question.
    protected void setQuestion(String question) {
        questionTextArea.setText(question);
    }
    //Called from GamePlayCycle. Displays the possible answers.
    protected void setAnswers(String answer1, String answer2, String answer3, String answer4){
        answerTextArea1.setText(answer1);
        answerTextArea2.setText(answer2);
        answerTextArea3.setText(answer3);
        answerTextArea4.setText(answer4);

        if(highlightCorrectAnswer) {
            if(correctAnswer == 1 ) answerTextArea1.setBackground(Color.YELLOW);
            else if(correctAnswer == 2) answerTextArea2.setBackground(Color.YELLOW);
            else if(correctAnswer == 3) answerTextArea3.setBackground(Color.YELLOW);
            else if(correctAnswer == 4) answerTextArea4.setBackground(Color.YELLOW);
        }
    }
    //This will hide all rooms that are NOT options available to the player based on
    //the current room they are in.
    protected void setPlayerOptions(int x, int y) {
        //for loop to hide all rooms
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                //keep locked rooms visible
                if(!GamePlay.getLocked(i, j)) {
                    buttHolder[i][j].setVisible(false);
                }
                if(i == 3 && j == 3) {
                    buttHolder[i][j].setEnabled(false);
                }
            }
        }
        //Now draw adjacent rooms
        buttHolder[x][y].setVisible(true);
        buttHolder[3][3].setVisible(true);
        if(x <= 2) {
            buttHolder[x + 1][y].setVisible(true);
            if(x + 1 == 3 && y == 3) {
                buttHolder[x + 1][y].setEnabled(true);
            }
        }
        if(y <= 2) {
            buttHolder[x][y + 1].setVisible(true);
            if(x == 3 && y + 1 == 3) {
                buttHolder[x + 1][y].setEnabled(true);
            }
        }
        if(x >= 1) {
            buttHolder[x - 1][y].setVisible(true);
            if(x - 1 == 3 && y == 3) {
                buttHolder[x + 1][y].setEnabled(true);
            }
        }
        if(y >= 1) {
            buttHolder[x][y - 1].setVisible(true);
            if(x == 3 && y - 1 == 3) {
                buttHolder[x + 1][y].setEnabled(true);
            }
        }
    }

    //Button Action handlers. Code for when buttons are pressed goes here
    public void actionPerformed(ActionEvent e){
        Object action = e.getSource();

        if(action == button1){
            lastPressed = 1;
            potentialMoveLocationX = 0;
            potentialMoveLocationY = 3;
            this.GamePlay.roomEntry(potentialMoveLocationX,potentialMoveLocationY);

        }else if(action == button2){
            lastPressed = 2;
            potentialMoveLocationX = 1;
            potentialMoveLocationY = 3;
            this.GamePlay.roomEntry(potentialMoveLocationX,potentialMoveLocationY);

        }else if(action == button3){
            lastPressed = 3;
            potentialMoveLocationX = 2;
            potentialMoveLocationY =3;
            this.GamePlay.roomEntry(potentialMoveLocationX,potentialMoveLocationY);

        }else if(action == button4){
            lastPressed = 4;
            potentialMoveLocationX = 3;
            potentialMoveLocationY = 3;
            this.GamePlay.roomEntry(potentialMoveLocationX,potentialMoveLocationY);

        }else if(action == button5){
            lastPressed = 5;
            potentialMoveLocationX = 0;
            potentialMoveLocationY = 2;
            this.GamePlay.roomEntry(potentialMoveLocationX,potentialMoveLocationY);

        }else if(action == button6){
            lastPressed = 6;
            potentialMoveLocationX = 1;
            potentialMoveLocationY = 2;
            this.GamePlay.roomEntry(potentialMoveLocationX,potentialMoveLocationY);

        }else if(action == button7){
            lastPressed = 7;
            potentialMoveLocationX = 2;
            potentialMoveLocationY = 2;
            this.GamePlay.roomEntry(potentialMoveLocationX,potentialMoveLocationY);

        }else if(action == button8){
            lastPressed = 8;
            potentialMoveLocationX = 3;
            potentialMoveLocationY = 2;
            this.GamePlay.roomEntry(potentialMoveLocationX,potentialMoveLocationY);

        }else if(action == button9){
            lastPressed = 9;
            potentialMoveLocationX = 0;
            potentialMoveLocationY = 1;
            this.GamePlay.roomEntry(potentialMoveLocationX,potentialMoveLocationY);

        }else if(action == button10){
            lastPressed = 10;
            potentialMoveLocationX = 1;
            potentialMoveLocationY = 1;
            this.GamePlay.roomEntry(potentialMoveLocationX,potentialMoveLocationY);

        }else if(action == button11){
            lastPressed = 11;
            potentialMoveLocationX = 2;
            potentialMoveLocationY = 1;
            this.GamePlay.roomEntry(potentialMoveLocationX,potentialMoveLocationY);

        }else if(action == button12){
            lastPressed = 12;
            potentialMoveLocationX = 3;
            potentialMoveLocationY = 1;
            this.GamePlay.roomEntry(potentialMoveLocationX,potentialMoveLocationY);

        }else if(action == button13){
            lastPressed = 13;
            potentialMoveLocationX = 0;
            potentialMoveLocationY = 0;
            this.GamePlay.roomEntry(potentialMoveLocationX,potentialMoveLocationY);

        }else if(action == button14){
            lastPressed = 14;
            potentialMoveLocationX = 1;
            potentialMoveLocationY = 0;
            this.GamePlay.roomEntry(potentialMoveLocationX,potentialMoveLocationY);

        }else if(action == button15){
            lastPressed = 15;
            potentialMoveLocationX = 2;
            potentialMoveLocationY = 0;
            this.GamePlay.roomEntry(potentialMoveLocationX,potentialMoveLocationY);

        }else if(action == button16){
            lastPressed = 16;
            potentialMoveLocationX = 3;
            potentialMoveLocationY = 0;
            this.GamePlay.roomEntry(potentialMoveLocationX,potentialMoveLocationY);


            //when the submit button is pressed
        }else if(action == submit){
            //check that the correct answer is selected or not
            boolean submission = this.GamePlay.submit(potentialMoveLocationX, potentialMoveLocationY, playerAnswer);

            //if the player is wrong
            if(submission == false) {
                switch(lastPressed) {
                    case 1:
                        button1.setDisabledIcon(lockIcon);
                        button1.setBackground(Color.DARK_GRAY);
                        button1.setEnabled(false);
                        break;
                    case 2:
                        button2.setDisabledIcon(lockIcon);
                        button2.setBackground(Color.DARK_GRAY);
                        button2.setEnabled(false);
                        break;
                    case 3:
                        button3.setDisabledIcon(lockIcon);
                        button3.setBackground(Color.DARK_GRAY);
                        button3.setEnabled(false);
                        break;
                    case 4:
                        button4.setDisabledIcon(lockIcon);
                        button4.setBackground(Color.DARK_GRAY);
                        button4.setEnabled(false);
                        break;
                    case 5:
                        button5.setDisabledIcon(lockIcon);
                        button5.setBackground(Color.DARK_GRAY);
                        button5.setEnabled(false);
                        break;
                    case 6:
                        button6.setDisabledIcon(lockIcon);
                        button6.setBackground(Color.DARK_GRAY);
                        button6.setEnabled(false);
                        break;
                    case 7:
                        button7.setDisabledIcon(lockIcon);
                        button7.setBackground(Color.DARK_GRAY);
                        button7.setEnabled(false);
                        break;
                    case 8:
                        button8.setDisabledIcon(lockIcon);
                        button8.setBackground(Color.DARK_GRAY);
                        button8.setEnabled(false);
                        break;
                    case 9:
                        button9.setDisabledIcon(lockIcon);
                        button9.setBackground(Color.DARK_GRAY);
                        button9.setEnabled(false);
                        break;
                    case 10:
                        button10.setDisabledIcon(lockIcon);
                        button10.setBackground(Color.DARK_GRAY);
                        button10.setEnabled(false);
                        break;
                    case 11:
                        button11.setDisabledIcon(lockIcon);
                        button11.setBackground(Color.DARK_GRAY);
                        button11.setEnabled(false);
                        break;
                    case 12:
                        button12.setDisabledIcon(lockIcon);
                        button12.setBackground(Color.DARK_GRAY);
                        button12.setEnabled(false);
                        break;
                    case 13:
                        button13.setDisabledIcon(lockIcon);
                        button13.setBackground(Color.DARK_GRAY);
                        button13.setEnabled(false);
                        break;
                    case 14:
                        button14.setDisabledIcon(lockIcon);
                        button14.setBackground(Color.DARK_GRAY);
                        button14.setEnabled(false);
                        break;
                    case 15:
                        button15.setDisabledIcon(lockIcon);
                        button15.setBackground(Color.DARK_GRAY);
                        button15.setEnabled(false);
                        break;
                    case 16:
                        button16.setDisabledIcon(lockIcon);
                        button16.setBackground(Color.DARK_GRAY);
                        button16.setEnabled(false);
                        break;
                }
                this.potentialMoveLocationY = 0;
                this.potentialMoveLocationX = 0;
                //after locking the room, check if the game is still beatable
                if(!this.GamePlay.checkWinnable()) {
                    GameOver();
                }

            }

        }else if(action == answerRadioButton1){
            playerAnswer = 1;
        }else if(action == answerRadioButton2){
            playerAnswer = 2;
        }else if(action == answerRadioButton3){
            playerAnswer = 3;
        }else if(action == answerRadioButton4){
            playerAnswer = 4;
        }
        else{
            System.out.println("Error message: Something went wrong, IDK what.");
        }
        if(playerLocationX == 3 && playerLocationY == 3)
            gameIsWon();

    }

    protected void GameOver() {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                buttHolder[i][j].setEnabled(false);
            }
        }
        resultsTextArea.setText("Game Over!");
    }
    protected void setBunnyLocation(int x, int y){
        BufferedImage invisibleIcon = new BufferedImage(button1.getWidth(),button1.getHeight(),BufferedImage.TYPE_4BYTE_ABGR);
        ImageIcon invis = new ImageIcon(invisibleIcon);
        button1.setIcon(invis);
        button2.setIcon(invis);
        button3.setIcon(invis);
        //button4.setIcon(invis);
        button5.setIcon(invis);
        button6.setIcon(invis);
        button7.setIcon(invis);
        button8.setIcon(invis);
        button9.setIcon(invis);
        button10.setIcon(invis);
        button11.setIcon(invis);
        button12.setIcon(invis);
        button13.setIcon(invis);
        button14.setIcon(invis);
        button15.setIcon(invis);
        button16.setIcon(invis);
        buttHolder[x][y].setIcon(playerIcon);
    }

    protected void gameIsWon(){
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                buttHolder[i][j].setEnabled(false);
            }
        }
        resultsTextArea.setText("You Win!");
    }

    //Menu helper method
    private JMenu getFileMenu(){
        JMenu file = new JMenu("File");
        JMenuItem quit = new JMenuItem("quit");
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        //IDK why but I feel like it should have a settings option
        settings = new JMenu("settings");

        //Fonts because I was stuck and used this to take up time
        JMenu fonts = new JMenu("fonts");
        fontsHolder = new String[]{"Arial", "Compic Sans MS" , "Futura" , "Georgia" , "Kai" , "Nadeem" , "Osaka" , "Papyrus"};

        for(String s: fontsHolder){
            JMenuItem m = new JMenuItem(s);
            fonts.add(m);
            m.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    font = s;
                    Font newFont = new Font(s , Font.PLAIN , 14);
                    questionTextArea.setFont(newFont);
                    newFont = new Font(s , Font.PLAIN , 16);
                    button1.setFont(newFont);
                    button2.setFont(newFont);
                    button3.setFont(newFont);
                    button4.setFont(newFont);
                    button5.setFont(newFont);
                    button6.setFont(newFont);
                    button7.setFont(newFont);
                    button8.setFont(newFont);
                    button9.setFont(newFont);
                    button10.setFont(newFont);
                    button11.setFont(newFont);
                    button12.setFont(newFont);
                    button13.setFont(newFont);
                    button14.setFont(newFont);
                    button15.setFont(newFont);
                    button16.setFont(newFont);
                    submit.setFont(newFont);
                    answerTextArea1.setFont(newFont);
                    answerTextArea2.setFont(newFont);
                    answerTextArea3.setFont(newFont);
                    answerTextArea4.setFont(newFont);
                    newFont = new Font(s , Font.PLAIN , 16);
                    resultsTextArea.setFont(newFont);
                    newFont = new Font(s , Font.PLAIN , 30);
                    title.setFont(newFont);

                }
            });
        }

        settings.add(fonts);
        file.add(settings);

        //Adding A cheat menu
        //Ideas for cheats: Correct answer is highlighted
        cheats = new JMenu("cheats");
        JMenuItem cheatOption = new JMenuItem("Highlight Correct Answer");
        cheatOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                highlightCorrectAnswer = true;
                correctAnswer = GamePlay.getCorrectAnswer(potentialMoveLocationX, potentialMoveLocationY);

                if(highlightCorrectAnswer == true){
                    if(correctAnswer == 0 ) answerTextArea1.setBackground(Color.YELLOW);
                    else if(correctAnswer == 1) answerTextArea2.setBackground(Color.YELLOW);
                    else if(correctAnswer == 2) answerTextArea2.setBackground(Color.YELLOW);
                    else if(correctAnswer == 3) answerTextArea4.setBackground(Color.YELLOW);
                }
            }
        });
        cheats.add(cheatOption);


        settings.add(cheats);
        cheatOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // This is where the code for the cheat goes, I can add it better when its running better
            }
        });


        file.addSeparator();
        file.add(quit);
        return file;
    }

    //Menu helper method
    private JMenu getHelpMenu(){
        JMenu help = new JMenu("Help");
        JMenuItem about = new JMenuItem("about");
        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog( null, "Team: Ryan Cranston , Morgan Combs , Christopher Davisson\n" +
                        "Class: CSCD 350-001: Software Development Principle\n" +
                        "Prof: Tom Capaul");
            }
        });
        help.add(about);
        return help;
    }
}