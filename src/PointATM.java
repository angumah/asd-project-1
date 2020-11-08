import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

@SuppressWarnings("serial")
public class PointATM extends JFrame{
    public static final int SUCCESS = 1;
    public static final int INSUFFICIENT = -1;

    public static final String LOGIN_VIEW = "LOGIN_VIEW";
    public static final String GUESSING_VIEW = "GUESSING_VIEW";
    public static final String SIGNUP_VIEW = "SIGNUP_VIEW";
    public static final String TRANSFER_VIEW = "TRANSFER_VIEW";
    public static final String UPGRADE_VIEW = "UPGRADE_VIEW";


    public static final int LOGIN_VIEW_INDEX = 0;
    public static final int GUESSING_VIEW_INDEX = 1;
    public static final int SIGNUP_VIEW_INDEX = 2;
    public static final int TRANSFER_VIEW_INDEX = 3;
    public static final int UPGRADE_VIEW_INDEX = 4;

    public static ArrayList<User> users;
    public PointATM() {
        super("Guess The Number");

        PointATM.users = new ArrayList<User>();

        //test User

        PointATM.users.add(new User(
                "Aaron",
                "LaPlaca",
                "aaronlaplaca22@gmail.com",
                "Aaron_LaPlaca",
                "21"

        ));
        User aaron = PointATM.existingUser("Aaron_LaPlaca");
        Account aaronAccount = aaron.getAccount();
        aaronAccount.addPoints(10021);

        PointATM.users.add(new User(
                "Mr.",
                "Wilson",
                "wilson@gmail.com",
                "bestCompSci_teacher",
                "123456"
        ));
        User wilson = PointATM.existingUser("bestCompSci_teacher");
        Account wilsonAccount = wilson.getAccount();
        wilsonAccount.addPoints(4000);
    }


    //add users
    public static void addUsers(User user){
        PointATM.users.add(user);
    }


    //get users

    public static int getDBSize(){
        int counter = 1;

        for (int i = 0; i < users.size(); i++){
            counter++;
        }
        return counter;
    }

    public static User getUser(int index){
        User returnUser = users.get(index);
        return returnUser;
    }

    /**
     * Looks up a User by account number and PIN.
     *
     * @param username - the account number
     * @param password - the PIN
     * @return a user if found
     */
    public static User lookupUser(String username, String password){
        for(User user : users) {
            if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public static User existingUser(String username){
        for(User user : users) {
            if (user.getUserName().equals(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Looks up an account by account number.
     *
     * @param accountNumber - the account number
     * @return a bank account if found
     */

    public static Account accountLookup(long accountNumber){
        for(User user : users){
            if(user.getAccount().getAccountNumber() == accountNumber){
                return user.getAccount();
            }
        }
        return null;
    }

    /*
     * Initializes the ATM views and adds them to the CardLayout.
     */


    public void init() {
        JPanel views = new JPanel(new CardLayout());
        ViewManager manager = new ViewManager(views);

        // add child views to the parent container
        views.add(new LoginView(manager), LOGIN_VIEW);
        views.add(new GuessingView(manager), GUESSING_VIEW);
        views.add(new SignUpView(manager), SIGNUP_VIEW);
        views.add(new TransferView(manager), TRANSFER_VIEW);
        views.add(new UpgradeView(manager), UPGRADE_VIEW);

        // configure the application frame

        this.add(views);
        this.setBounds(100, 100, 1080, 720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }


    /*
     * Program execution begins here.
     *
     * @param args
     */

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    new PointATM().init();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
