import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class SignUpView extends JPanel{
    private ViewManager manager;
    private JTextField fNameField;
    private JTextField lNameField;
    private JTextField emailField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton createAccountButton;
    private JLabel createAccountMessage;
    private JButton backButton;

    public SignUpView(ViewManager manager){
        super();
        this.manager = manager;
        this.init();
    }

    public JTextField getfName(){
        return fNameField;
    }

    public JTextField getlName(){
        return lNameField;
    }

    public JTextField getEmail(){
        return emailField;
    }

    public JTextField getUsername(){
        return usernameField;
    }

    public JTextField getPassword(){
        return passwordField;
    }
    public void toggleCreateAccountMessage(boolean created) {
        if (created) {
            createAccountMessage.setVisible(true);
        }
        else{
            createAccountMessage.setVisible(false);
        }
    }

    public void clear(){
        toggleCreateAccountMessage(false);
    }

    private void init(){
        this.setLayout(null);

        initTitle();
        initFNameField();
        initLNameField();
        initEmailField();
        initUserNameField();
        initPasswordField();
        initCreatedMessage();
        initCreateAccountButton();
        initBackButton();
    }

    private void initTitle(){
        JLabel label = new JLabel("Sign Up", SwingConstants.CENTER);
        label.setBounds(300, 20, 500, 35);
        label.setFont(new Font("DialogInput", Font.BOLD, 21));

        this.add(label);
    }

    private void initFNameField() {
        JLabel firstNameLabel = new JLabel("First Name: ", SwingConstants.CENTER);
        firstNameLabel.setBounds(350,100,200, 35);
        firstNameLabel.setFont(new Font("DialogInput", Font.ITALIC, 12));
        firstNameLabel.setLabelFor(fNameField);

        fNameField = new JTextField("");
        fNameField.setBounds(500, 100, 150, 35);
        fNameField.setFont(new Font("DialogInput", Font.ITALIC, 12));

        this.add(firstNameLabel);
        this.add(fNameField);
    }

    private void initLNameField(){
        JLabel lastNameLabel = new JLabel("Last Name: ", SwingConstants.CENTER);
        lastNameLabel.setBounds(350,140,200, 35);
        lastNameLabel.setFont(new Font("DialogInput", Font.ITALIC, 12));
        lastNameLabel.setLabelFor(lNameField);

        lNameField = new JTextField("");
        lNameField.setBounds(500, 140, 150, 35);
        lNameField.setFont(new Font("DialogInput", Font.ITALIC, 12));

        this.add(lastNameLabel);
        this.add(lNameField);
    }

    private void initEmailField(){
        JLabel emailLabel = new JLabel("Email: ", SwingConstants.CENTER);
        emailLabel.setBounds(360,180,200, 35);
        emailLabel.setFont(new Font("DialogInput", Font.ITALIC, 12));
        emailLabel.setLabelFor(emailField);

        emailField = new JTextField("");
        emailField.setBounds(500, 180, 150, 35);
        emailField.setFont(new Font("DialogInput", Font.ITALIC, 12));

        this.add(emailLabel);
        this.add(emailField);
    }

    private void initUserNameField(){
        JLabel userNameLabel = new JLabel("Username: ", SwingConstants.CENTER);
        userNameLabel.setBounds(350,220,200, 35);
        userNameLabel.setFont(new Font("DialogInput", Font.ITALIC, 12));
        userNameLabel.setLabelFor(usernameField);

        usernameField = new JTextField("");
        usernameField.setBounds(500, 220, 150, 35);
        usernameField.setFont(new Font("DialogInput", Font.ITALIC, 12));

        this.add(userNameLabel);
        this.add(usernameField);
    }

    private void initPasswordField(){
        JLabel passwordLabel = new JLabel("Password: ", SwingConstants.CENTER);
        passwordLabel.setBounds(350,260,200, 35);
        passwordLabel.setFont(new Font("DialogInput", Font.ITALIC, 12));
        passwordLabel.setLabelFor(passwordField);

        passwordField = new JPasswordField("");
        passwordField.setBounds(500, 260, 150, 35);
        passwordField.setFont(new Font("DialogInput", Font.ITALIC, 12));

        this.add(passwordLabel);
        this.add(passwordField);
    }

    private void initCreatedMessage(){
        createAccountMessage = new JLabel("", SwingConstants.CENTER);
        createAccountMessage.setBounds(250, 500, 600, 35);
        createAccountMessage.setFont(new Font("DialogInput", Font.ITALIC, 12));
        createAccountMessage.setForeground(Color.RED);

        this.add(createAccountMessage);
    }

    private void initCreateAccountButton(){
        createAccountButton = new JButton("Create!");
        createAccountButton.setBounds(515, 310, 120, 35);
        createAccountButton.setFont(new Font("DialogInput", Font.ITALIC, 12));
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                if (source.equals(createAccountButton)) {
                    String firstName = fNameField.getText();
                    firstName = (firstName.length() <= 0)? null: firstName;

                    String lastName = lNameField.getText();
                    lastName = (lastName.length() <= 0)? null: lastName;

                    String email = emailField.getText();
                    email = (email.length() <=0)? null: email;

                    String username = usernameField.getText();
                    username = (username.length() <= 0)? null: username;

                    String password = String.valueOf(passwordField.getPassword());
                    password = (password.length() <= 0)? null: password;

                    try {
                        int status = manager.signup(firstName, lastName, email, username, password);

                        if (status == 1) {
                            createAccountMessage.setText("Account created successfully");
                        } else {
                            createAccountMessage.setText("Username already exists");
                        }
                    } catch (NullPointerException exception){
                        createAccountMessage.setText("One or more of the required fields has not been filled out");
                    }
                    }

                }

        });

        this.add(createAccountButton);
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
                    manager.switchTo(PointATM.LOGIN_VIEW);
                }
            }
        });
        this.add(backButton);
    }
}
