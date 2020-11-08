import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class TransferView extends JPanel{
    private ViewManager manager;
    private JTextField enterAccountField;
    private JTextField enterPointAmt;
    private JButton transferPoints;
    private JButton backButton;
    private JLabel transferMessage;
    private JLabel userPoints;
    private JLabel acctNo;

    public TransferView(ViewManager manager){
        super();
        this.manager = manager;
        this.init();
    }
    public void actionPerformed(ActionEvent e){

    }


    public void populate(User user){
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);
        df.setGroupingUsed(true);

        acctNo.setText("Account No. : " + user.getAccount().getAccountNumber());
        userPoints.setText("Points : " + df.format(user.getAccount().getPointBalance()));

    }


    // shows or hides result when submitted
    public void toggleTransferMessage(boolean transferred){
        if(transferred){
            transferMessage.setVisible(true);
        }
        else{
            transferMessage.setText("");
        }
    }

    public void clear(){
        enterAccountField.setText("");
        enterPointAmt.setText("");

        toggleTransferMessage(false);
    }


    private void init(){
        this.setLayout(null);

        initTitle();
        initEnterAccountNo();
        initEnterPointAmt();
        initTransferPointsButton();
        initBackButton();
        initTransferredMessage();
        initUserPoints();
        initAcctNo();

    }

    private void initTitle(){
        JLabel title = new JLabel("Transfer", SwingConstants.CENTER);
        title.setBounds(300, 20, 500, 35);
        title.setFont(new Font("DialogInput", Font.BOLD, 21));

        this.add(title);
    }

    private void initEnterAccountNo(){
        JLabel accountNoLabel = new JLabel("Enter Account Number: ", SwingConstants.CENTER);
        accountNoLabel.setBounds(300, 150, 200, 35);
        accountNoLabel.setFont(new Font("DialogInput", Font.ITALIC, 12));
        accountNoLabel.setLabelFor(enterAccountField);

        enterAccountField = new JTextField("Acct No.");
        enterAccountField.setBounds(500, 150, 150, 35);
        enterAccountField.setFont(new Font("DialogInput", Font.ITALIC, 12));

        enterAccountField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                enterAccountField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        this.add(accountNoLabel);
        this.add(enterAccountField);
    }

    private void initEnterPointAmt(){
        JLabel pointAmountLabel = new JLabel("Points: ", SwingConstants.CENTER);
        pointAmountLabel.setBounds(350, 200, 200, 35);
        pointAmountLabel.setFont(new Font("DialogInput", Font.ITALIC, 12));
        pointAmountLabel.setLabelFor(enterPointAmt);

        enterPointAmt = new JTextField("Amount");
        enterPointAmt.setBounds(500, 200, 150, 35);
        enterPointAmt.setFont(new Font("DialogInput", Font.ITALIC, 12));

        enterPointAmt.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                enterPointAmt.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });
        this.add(pointAmountLabel);
        this.add(enterPointAmt);
    }

    private void initTransferPointsButton(){
        transferPoints = new JButton("Transfer");
        transferPoints.setBounds(525, 250, 100, 20);
        transferPoints.setFont(new Font("DialogInput", Font.ITALIC, 12));
        transferPoints.addActionListener(new ActionListener() {

            //respond when button is clicked
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                if(source.equals(transferPoints)){
                    try {
                        Account destination = PointATM.accountLookup(Long.parseLong(enterAccountField.getText()));
                        int amount = Integer.parseInt(enterPointAmt.getText());
                        int status = manager.transfer(destination, amount);
                        if (status == 1) {
                            transferMessage.setText("Successfully transferred " + amount + " to " + destination + ".");
                            populate(manager.getActiveUser());
                        } else {
                            transferMessage.setText("Insufficient amount/ or invalid account number");
                        }
                    } catch (NullPointerException exception){
                        transferMessage.setText("Insufficient amount/ or invalid account number.");
                    } catch (NumberFormatException exception){
                        transferMessage.setText("Enter a valid number");
                    }
                    toggleTransferMessage(true);
                }
            }
        });
        this.add(transferPoints);
    }
    private void initBackButton(){
        backButton = new JButton("Back");
        backButton.setBounds(320, 30, 80, 20);
        backButton.setFont(new Font("DialogInput", Font.ITALIC, 12));
        backButton.addActionListener(new ActionListener() {

            //respond when button is clicked
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                if(source.equals(backButton)){
                    manager.backToGame();
                }
            }
        });
        this.add(backButton);
    }
    private void initTransferredMessage(){
        transferMessage = new JLabel("", SwingConstants.CENTER);
        transferMessage.setBounds(250, 500, 600, 35);
        transferMessage.setFont(new Font( "DialogInput", Font.ITALIC, 12));
        transferMessage.setForeground(Color.RED);
        this.add(transferMessage);
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
