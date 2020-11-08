public class User {
    private String fname;
    private String lName;
    private String email;
    private String userName;
    private String password;
    private Account account;
    private Upgrade upgrades;

    /**
     * Create a new User with a default BankAccount.
     *
     * @param fName
     * @param lName
     * @param email
     * @param userName
     * @param password
     */

    public User(String fName, String lName, String email, String userName, String password){
        this.fname = fName;
        this.lName = lName;
        this.email = email;
        this.userName = userName;
        this.password = password;

        this.account = new Account();
        this.upgrades = new Upgrade();
    }

    public String getfName(){
        return fname;
    }
    public String getlName(){
        return lName;
    }
    public String getEmail(){
        return email;
    }
    public String getUserName(){return userName;}
    public String getPassword(){
        return password;
    }
    public Account getAccount(){
        return account;
    }
    public Upgrade getUpgrades(){return upgrades;}
}
