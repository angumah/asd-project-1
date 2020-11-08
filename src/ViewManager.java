import java.awt.*;


public class ViewManager {
    private Container views;
    private User activeUser;

    public ViewManager(Container views){
        this.views = views;
        this.activeUser = null;
    }

    public User getActiveUser(){
        return activeUser;
    }

    /**
     * Switches to another view.
     *
     * @param view - the view to switch to
     */

    public void switchTo(String view) {
        ((CardLayout) views.getLayout()).show(views, view);
    }


    /**
     * Logs into an account.
     *
     * @param username - the username
     * @param password - the password
     */

    public void login(String username, String password) {
        LoginView lv = ((LoginView) views.getComponents()[PointATM.LOGIN_VIEW_INDEX]);

            activeUser = PointATM.lookupUser(
                   username,
                   password
            );

            if (activeUser == null) {
                lv.toggleErrorMessage(true);    // account not found, show error message
            } else {
                ((GuessingView) views.getComponents()[PointATM.GUESSING_VIEW_INDEX])
                        .populate(activeUser);

                switchTo(PointATM.GUESSING_VIEW);
                lv.clear();
            }

        }

        public void backToGame(){
            ((GuessingView) views.getComponents()[PointATM.GUESSING_VIEW_INDEX])
                    .populate(activeUser);

            switchTo(PointATM.GUESSING_VIEW);
        }

    public void toTransferView(){
        ((TransferView) views.getComponents()[PointATM.TRANSFER_VIEW_INDEX])
                .populate(activeUser);

        switchTo(PointATM.TRANSFER_VIEW);
    }

    public void toUpgradeView(){
        ((UpgradeView) views.getComponents()[PointATM.UPGRADE_VIEW_INDEX])
                .populate(activeUser);

        switchTo(PointATM.UPGRADE_VIEW);
    }
    /**
     * Makes a new account.
     *
     * @param fName - First Name
     * @param lName - Last Name
     * @param email - email
     * @param userName - username
     * @param password - password
     */

    public int signup(String fName, String lName, String email, String userName, String password){
        SignUpView sv = ((SignUpView) views.getComponents()[PointATM.SIGNUP_VIEW_INDEX]);
        User user = PointATM.existingUser(userName);
        int status = 0;
        if(user == null) {
            if(fName == null || lName == null || email == null || userName == null || password == null) {
                sv.toggleCreateAccountMessage(true);
            }
            else{
                user = new User(fName, lName, email, userName, password);
                PointATM.addUsers(user);
                sv.clear();
                switchTo(PointATM.LOGIN_VIEW);
                status = 1;
            }
        }
        else{
            status = -1;
            sv.toggleCreateAccountMessage(true);
        }
        sv.toggleCreateAccountMessage(true);
        return status;
    }


    /**
     * Routes a transfer request to the model.
     *
     * @param destination - the destination account
     * @param points - the amount to transfer
     */

    public int transfer(Account destination, int points) {
        return activeUser.getAccount().transferPoints(destination, points);
    }

    public int guessNumber(int points){
        return activeUser.getAccount().addPoints(points);
    }

    public int upgrade(int cost){
        ((GuessingView) views.getComponents()[PointATM.GUESSING_VIEW_INDEX])
                .populate(activeUser);
        return activeUser.getAccount().upgrade(cost);
    }

    /**
     * Logs out of an account.
     */

    public void logout() {
        switchTo(PointATM.LOGIN_VIEW);
        activeUser = null;

        ((GuessingView) views.getComponents()[PointATM.GUESSING_VIEW_INDEX]).clear();
    }

}
