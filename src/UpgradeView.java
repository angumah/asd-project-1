import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class UpgradeView extends JPanel {
    private ViewManager manager;
    private JLabel upgradeLabel;
    private JLabel upgradeCostLabel;
    private JLabel upgradeMessage;
    private JButton buyUpgradeButton;
    private JButton backButton;
    private JLabel userPoints;
    private JLabel acctNo;

    public UpgradeView(ViewManager manager){
        super();
        this.manager = manager;
        this.init();
    }
    // toggle upgrade message
    public void toggleUpgradeMessage(boolean upgraded){
        if(upgraded){
            upgradeMessage.setVisible(true);
        }
        else{
            upgradeMessage.setVisible(false);
        }
    }

    public void populate(User user){
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);
        df.setGroupingUsed(true);

        acctNo.setText("Account No. : " + user.getAccount().getAccountNumber());
        userPoints.setText("Points : " + df.format(user.getAccount().getPointBalance()));
        upgradeCostLabel.setText("costs " + manager.getActiveUser().getUpgrades().getCost() + " points");
    }

    public void clear(){
        toggleUpgradeMessage(false);
    }

    //initialize view

    private void init(){
        this.setLayout(null);

        initTitle();
        initUpgradeLabel();
        initUpgradeCostLabel();
        initUpgradeMessage();
        initBuyUpgradeButton();
        initBackButton();
        initUserPoints();
        initAcctNo();
    }

    private void initTitle(){
        JLabel title = new JLabel("Upgrade", SwingConstants.CENTER);
        title.setBounds(300, 20, 500, 35);
        title.setFont(new Font("DialogInput", Font.BOLD, 21));

        this.add(title);
    }

    private void initUpgradeLabel(){
        upgradeLabel = new JLabel("Point Upgrade:");
        upgradeLabel.setBounds(500, 110, 150, 35);
        upgradeLabel.setFont(new Font("DialogInput", Font.ITALIC, 12));

        JLabel upgradeDetails = new JLabel("Get more points for each guess");
        upgradeDetails.setBounds(420, 130, 500, 35);
        upgradeDetails.setFont(new Font("DialogInput", Font.ITALIC, 12));

        this.add(upgradeLabel);
        this.add(upgradeDetails);
    }

    private void initUpgradeCostLabel(){
        upgradeCostLabel = new JLabel("");
        upgradeCostLabel.setBounds(500, 220, 300, 35);
        upgradeCostLabel.setFont(new Font("DialogInput", Font.ITALIC, 12));

        this.add(upgradeCostLabel);
    }

    private void initBuyUpgradeButton(){
        buyUpgradeButton = new JButton("Get Upgrade");
        buyUpgradeButton.setBounds(450, 280, 200, 35);
        buyUpgradeButton.setFont(new Font("DialogInput", Font.ITALIC, 12));
        buyUpgradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                if(source.equals(buyUpgradeButton)){
                    int upgradeCost = manager.getActiveUser().getUpgrades().getCost();
                    int status = manager.upgrade(upgradeCost);

                    if (status == 1){
                        Upgrade.setPointIncrease();
                        Upgrade.setCost();
                        upgradeMessage.setText("Successful you will now receive, " + manager.getActiveUser().getUpgrades().getPercentIncrease() + "% more points for each guess!");
                        populate(manager.getActiveUser());
                    }
                    else{
                        upgradeMessage.setText("Insufficient points");
                    }
                    toggleUpgradeMessage(true);
                }
            }
        });
        this.add(buyUpgradeButton);
    }

    private void initUpgradeMessage(){
        upgradeMessage = new JLabel("", SwingConstants.CENTER);
        upgradeMessage.setBounds(240, 500, 600, 35);
        upgradeMessage.setFont(new Font("DialogInput", Font.ITALIC, 12));

        this.add(upgradeMessage);
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
                    clear();
                    manager.switchTo(PointATM.GUESSING_VIEW);
                }
            }
        });
        this.add(backButton);
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
