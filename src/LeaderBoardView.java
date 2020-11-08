import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class LeaderBoardView extends JPanel {
    private JTable leaderBoardTable;


    public LeaderBoardView(){
        super();
        this.init();
    }

    private void init(){
        initTitle();
        initLeaderBoard();
    }

    private void initTitle(){
        JLabel title = new JLabel("Leaderboard", SwingConstants.CENTER);
        title.setBounds(450, 20, 200, 35);
        title.setFont(new Font("DialogInput", Font.BOLD, 24));

        this.add(title);
    }

    private void initLeaderBoard(){
        String data = toString();
        String labels = "First Name, Last Name, Username, Balance";
  //      DefaultTableModel tableModel = new DefaultTableModel(data, labels);
  //      leaderBoardTable = new JTable(tableModel);

    }

    public String toString(){
        String userList = "[";

        ArrayList <User> sortedUsers = new ArrayList();
        ArrayList <User> modifiedUsers = new ArrayList();

        int usersDBSize = PointATM.getDBSize();

        for (int i = 0; i < usersDBSize; i++){
            User addUser = PointATM.getUser(i);
            modifiedUsers.add(addUser);
        }


        User topUser = null;
        User currentUser = null;
        User prevUser = null;
        User nextUser = null;
        int currentUserPointBalance = 0;
        int prevUserPointBalance = 0;
        int nextUserPointBalance = 0;

        for (int j = 0; j < usersDBSize; j++) {
            for (int i = 0; i < modifiedUsers.size(); i++) {
                if (i != 0 || i != modifiedUsers.size() - 1) {
                    prevUser = modifiedUsers.get(i - 1);
                    nextUser = modifiedUsers.get(i + 1);

                } else if (i == 0) {
                    nextUser = modifiedUsers.get(i + 1);
                } else if (i == modifiedUsers.size() - 1) {
                    prevUser = modifiedUsers.get(i - 1);
                }

                if (prevUser != null && nextUser != null) {
                    prevUserPointBalance = prevUser.getAccount().getPointBalance();
                    nextUserPointBalance = nextUser.getAccount().getPointBalance();
                } else if (prevUser == null && nextUser != null) {
                    nextUserPointBalance = nextUser.getAccount().getPointBalance();
                } else if (prevUser != null && nextUser == null) {
                    prevUserPointBalance = prevUser.getAccount().getPointBalance();
                }

                if (currentUserPointBalance > prevUserPointBalance) {
                    topUser = currentUser;
                } else {
                    topUser = prevUser;
                }
                if (nextUserPointBalance > topUser.getAccount().getPointBalance()) {
                    topUser = nextUser;
                }
                sortedUsers.add(topUser);
                modifiedUsers.remove(topUser);
            }
        }

        for(int i = 0; i < sortedUsers.size(); i++){
            User current = sortedUsers.get(i);
            String fName = current.getfName();
            String lName = current.getlName();
            String username = current.getUserName();
            String points = String.valueOf(current.getAccount().getPointBalance());
            if(i == sortedUsers.size() - 1){
                userList+= fName + ", " + lName + ", " + username + ", " + points;
            }
            else{
                userList+= fName + ", " + lName + ", " + username + ", " + points + ", ";
            }
        }
        return  userList + "]";
    }

    public static void main(String[] args) {
        Object pointATM = new PointATM();
        ((PointATM) pointATM).init();
        new LeaderBoardView().init();
    }
}
