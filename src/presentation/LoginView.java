package presentation;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import business.Encryption;
import business.model.UserModel;
import business.service.UserService;

public class LoginView extends JFrame{
	
	private JPanel loginPanel;
	private JButton loginButton;
	private JLabel accountLabel, passwordLabel;
	private JTextField accountText;
	private JPasswordField passwordText;
	
	public LoginView() {
		
		//loginFrame = new JFrame();
		
		super("Login");
		setName("Login");
		setLayout(new GridLayout());
		setSize(430,130);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		loginPanel = new JPanel();
		accountLabel = new JLabel("Username ");
		passwordLabel = new JLabel("Password ");
		
		accountText = new JTextField(30);
		passwordText = new JPasswordField(30);
		
		loginButton = new JButton("Login");
		
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String username;
				String password;
				List<UserModel> users = new ArrayList<UserModel>();
				UserService service = new UserService();
				boolean activeUser = false;
				
				try {
					username = accountText.getText();
					password = String.valueOf(passwordText.getPassword());

			        String securePassword = Encryption.get_SHA_1_SecurePassword(password);
					
					users = service.findAllUsers();
					for(UserModel u:users) {
						if(u.getUsername().equals(username) && u.getPassword().equals(securePassword)) {
							activeUser = true;
							if(u.getIsAdmin() == 1) {
								new AdminView();
							}
							else {
								new CashierView();
							}
						}
					}	
					
				if(activeUser==false)
					JOptionPane.showMessageDialog(null, "Incorrect username or password!");
					
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "Incorrect username or password!");
				}
				
			}
		});
		
		loginPanel.add(accountLabel);
		loginPanel.add(accountText);
		loginPanel.add(passwordLabel);
		loginPanel.add(passwordText);
		loginPanel.add(loginButton);
		add(loginPanel);
	
		setVisible(true);
	}
}
