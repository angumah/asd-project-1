import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class LoginView extends JPanel{
    private ViewManager manager;
    private JTextField userNameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton signUpButton;
    private JLabel errorMessageLabel;

    public LoginView(ViewManager manager) {
        super();
        this.manager = manager;
        this.init();
    }

    public JTextField getUserNameField(){
        return userNameField;
    }

    public JPasswordField getPasswordField(){
        return passwordField;
    }

    /**
     * Shows or hides the error message.
     *
     * @param show - true to show, false to hide
     */


    public void toggleErrorMessage(boolean show) {
        if (show) {
            errorMessageLabel.setVisible(true);
        } else {
            errorMessageLabel.setText("");
        }
    }


    /**
     * Clears the username and password fields, and hides the error message.
     */


    public void clear() {
        userNameField.setText("");
        passwordField.setText("");

        toggleErrorMessage(false);
    }


    /*
     * Initializes all components of this view.
     */

    private void init() {
        this.setLayout(null);

        initTitle();
        initErrorMessageLabel();
        initUserNameField();
        initPinField();
        initLoginButton();
        initSignUpButton();
    }

    private void initTitle() {
        JLabel label = new JLabel("Login", SwingConstants.CENTER);
        label.setBounds(300, 20, 500, 35);
        label.setFont(new Font("DialogInput", Font.BOLD, 21));

        this.add(label);
    }

    private void initErrorMessageLabel() {
        errorMessageLabel = new JLabel("", SwingConstants.CENTER);
        errorMessageLabel.setBounds(300, 110, 500, 35);
        errorMessageLabel.setFont(new Font("DialogInput", Font.ITALIC, 12));
        errorMessageLabel.setForeground(Color.RED);

        this.add(errorMessageLabel);
    }

    private void initUserNameField() {
        JLabel label = new JLabel("Username :", SwingConstants.RIGHT);
        label.setBounds(400, 160, 95, 35);
        label.setLabelFor(userNameField);
        label.setFont(new Font("DialogInput", Font.BOLD, 14));

        userNameField = new JTextField(20);
        userNameField.setBounds(505, 160, 200, 35);
        this.add(label);
        this.add(userNameField);
    }

    private void initPinField() {
        JLabel label = new JLabel("Password :", SwingConstants.RIGHT);
        label.setBounds(400, 200, 95, 35);
        label.setLabelFor(passwordField);
        label.setFont(new Font("DialogInput", Font.BOLD, 14));

        passwordField = new JPasswordField(20);
        passwordField.setBounds(505, 200, 200, 35);
        this.add(label);
        this.add(passwordField);
    }

    private void initLoginButton() {
        loginButton = new JButton("Login");
        loginButton.setBounds(505, 260, 200, 35);

        loginButton.addActionListener(new ActionListener() {

            /*
             * Respond when the user clicks the Login button.
             */

            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                try {
                    if (source.equals(loginButton)) {
                        String username = userNameField.getText();
                        String password = String.valueOf(passwordField.getPassword());
                        manager.login(username, password);
                    }
                } catch(NullPointerException exception){
                    errorMessageLabel.setText("Invalid username  and/or password.");
                }
            }
        });

        this.add(loginButton);
    }

    private void initSignUpButton(){
            signUpButton = new JButton("Sign Up");
            signUpButton.setBounds(505, 310, 200, 35);

            signUpButton.addActionListener(new ActionListener() {

                /*
                 * Respond when the user clicks the Login button.
                 */

                @Override
                public void actionPerformed(ActionEvent e) {
                    Object source = e.getSource();

                    if (source.equals(signUpButton)) {
                        clear();
                        manager.switchTo(PointATM.SIGNUP_VIEW);
                    }
                }
            });

            this.add(signUpButton);
        }

}
