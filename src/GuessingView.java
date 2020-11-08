import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
public class GuessingView extends JPanel {
    private ViewManager manager;
    private JTextField numberGuessField;
    private JButton submitGuess;
    private JButton nextGuess;
    private JButton transferPoints;
    private JButton upgrade;
    private JButton logout;
    private JLabel result;
    private JLabel userPoints;
    private JLabel acctNo;
    //initializes a new Random Number
    private RandNum randomNumber;

    public GuessingView(ViewManager manager){
        super();
        this.manager = manager;
        this.init();
    }


    // shows or hides result when submitted
    public void toggleResultMessage(boolean submitted){
        if(submitted){
            result.setVisible(true);
        }
        else {
            result.setText("");
        }

    }

    public void toggleNextOrSubmitButton(boolean submitted) {
        if (submitted) {
            nextGuess.setVisible(true);
            submitGuess.setVisible(false);
        } else {
            nextGuess.setVisible(false);
            submitGuess.setVisible(true);
        }
    }

    public void populate(User user){
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);
        df.setGroupingUsed(true);

        acctNo.setText("Account No. : " + user.getAccount().getAccountNumber());
        userPoints.setText("Points : " + df.format(user.getAccount().getPointBalance()));

    }

    public int calculatePoints(){
        int points = 0;
        int userGuess = Integer.parseInt(numberGuessField.getText());
        int actualNum = randomNumber.getRandomInt();
        points = (actualNum - userGuess);
        if(points < 0){
            points *= -1;
        }
        points = 100-points;
        points = Upgrade.newPointTotal(points);

        manager.guessNumber(points);
        return points;
    }

    public void clear() {
        numberGuessField.setText("Enter an integer between 1 and 100");

        toggleResultMessage(false);
        toggleNextOrSubmitButton(false);

        randomNumber = new RandNum();
    }

    // Initialize components of view

    private void init(){
        this.setLayout(null);

        initTitle();
        initNumberGuessField();
        initSubmitGuessButton();
        initLogoutButton();
        initNextGuessButton();
        initTransferPointsButton();
        initUpgradeButton();
        initResultLabel();
        initRandomNumber();
        initUserPoints();
        initAcctNo();
    }

    private void initTitle(){
        JLabel title = new JLabel("Guess The Number", SwingConstants.CENTER);
        title.setBounds(300, 20, 500, 35);
        title.setFont(new Font("DialogInput", Font.BOLD, 21));

        this.add(title);
    }

    private void initNumberGuessField(){
        JLabel numberFieldLabel = new JLabel("Guess: ", SwingConstants.CENTER);
        numberFieldLabel.setBounds(320, 150, 200, 35);
        numberFieldLabel.setLabelFor(numberGuessField);
        numberFieldLabel.setFont(new Font("DialogInput", Font.ITALIC, 14));

        numberGuessField = new JTextField("Enter number between 1 and 100");
        numberGuessField.setBounds(450, 150, 200, 35);

        numberGuessField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                numberGuessField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        });

        this.add(numberFieldLabel);
        this.add(numberGuessField);
    }

    private void initSubmitGuessButton() {
        submitGuess = new JButton("submit");
        submitGuess.setBounds(500, 200, 100, 30);
        submitGuess.addActionListener(new ActionListener() {

            //respond when button is clicked
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                if(source.equals(submitGuess)){
                    try {
                        int userGuess = Integer.parseInt(numberGuessField.getText());
                        if(randomNumber.isValidGuess(userGuess)){
                            result.setText("The number was " + randomNumber.getRandomInt() + " You received " + calculatePoints() + " points!");
                            if(userGuess == randomNumber.getRandomInt()){
                                result.setForeground(Color.GREEN);
                            }
                            else{
                                result.setForeground(Color.RED);
                            }
                            toggleNextOrSubmitButton(true);
                            populate(manager.getActiveUser());
                        }
                    }
                    catch(NumberFormatException error){
                        result.setText("Invalid Input");
                        toggleResultMessage(true);
                        toggleNextOrSubmitButton(true);
                    }

                }
            }
        });


        this.add(submitGuess);
    }

    private void initLogoutButton() {
        logout = new JButton("Logout");
        logout.setBounds(100, 20, 100, 30);
        logout.addActionListener(new ActionListener() {

            //respond when button is clicked
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                if(source.equals(logout)){
                        manager.logout();
                }
            }
        });
        this.add(logout);
    }

    private void initNextGuessButton(){
        nextGuess = new JButton("next");
        nextGuess.setBounds(500, 200, 100, 30);
        nextGuess.setVisible(false);
        nextGuess.addActionListener(new ActionListener() {

            //respond when button is clicked
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                if(source.equals(nextGuess)){
                    clear();
                }
            }
        });

        this.add(nextGuess);

    }

    private void initTransferPointsButton(){
        transferPoints = new JButton("Transfer");
        transferPoints.setBounds(760, 500, 100, 20);
        transferPoints.setFont(new Font("DialogInput", Font.ITALIC, 12));
        transferPoints.addActionListener(new ActionListener() {

            //respond when button is clicked
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                if(source.equals(transferPoints)){
                    manager.toTransferView();
                }
            }
        });

        this.add(transferPoints);
    }

    private void initUpgradeButton(){
        upgrade = new JButton("Upgrade");
        upgrade.setBounds(760, 650, 100, 20);
        upgrade.setFont(new Font("DialogInput", Font.ITALIC, 12));
        upgrade.addActionListener(new ActionListener() {

            //respond when button is clicked
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                if(source.equals(upgrade)){
                    manager.toUpgradeView();
                }
            }
        });
        this.add(upgrade);
    }

    private void initResultLabel(){
        result = new JLabel("", SwingConstants.CENTER);
        result.setBounds(250, 500, 500, 35);
        result.setFont(new Font( "DialogInput", Font.ITALIC, 12));
        result.setForeground(Color.RED);

        this.add(result);
    }

    private void initRandomNumber(){
        randomNumber = new RandNum();
    }

    private void initUserPoints(){
        userPoints = new JLabel("user points: ", SwingConstants.CENTER);
        userPoints.setBounds(545, 620, 200, 35);
        userPoints.setFont(new Font( "DialogInput", Font.ITALIC, 12));

        this.add(userPoints);
    }

    private void initAcctNo(){
        acctNo = new JLabel("user acct no: ", SwingConstants.CENTER);
        acctNo.setBounds(275, 620, 200, 35);
        acctNo.setFont(new Font( "DialogInput", Font.ITALIC, 12));

        this.add(acctNo);
    }
}
