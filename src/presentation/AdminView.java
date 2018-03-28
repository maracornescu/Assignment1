package presentation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import business.Encryption;
import business.Theater;
import business.exportFactory.WriterFactory;
import business.model.ShowModel;
import business.model.TicketModel;
import business.model.UserModel;
import business.service.ShowService;
import business.service.UserService;

public class AdminView extends JFrame{

	private JPanel adminPanel, cashierPanelLabel, cashierPanelButton, showPanelLabel, showPanelButton, cashierPanelTable, showPanelTable, exportPanel;
	private JTable cashierTable, showTable;
	private JScrollPane cashierPane, showsPane;
	private UserTable cashiers;
	private ShowTable shows;
	private JLabel usernameLabel, passwordLabel, titleLabel, genreLabel, distributionLabel, dateLabel, numberOfTicketsLabel, isAdminLabel;
	private JTextField usernameText, passwordText, isAdminText, titleText, dateText, numberOfTicketsText;
	private JTextArea distributionArea;
	private JComboBox<String> genreBox;
	private JComboBox<String> exportBox;
	private JButton addAccount, updateAccount, deleteAccount, addShow, updateShow, deleteShow, exportButton;
	private boolean firstAccountTable = false, firstShowTable = false;
	private JTable newAccountTable, newShowTable;
	
	public AdminView() {
		super("Admin");
		
		setLayout(new GridLayout());
		setSize(1000,870);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		adminPanel = new JPanel();
		cashierPanelLabel = new JPanel();
		cashierPanelButton = new JPanel();
		showPanelLabel = new JPanel();
		showPanelButton = new JPanel();
		cashierPanelTable = new JPanel();
		showPanelTable = new JPanel();
		exportPanel = new JPanel();
		
		usernameLabel = new JLabel("Username ");
		passwordLabel = new JLabel("Password ");
		isAdminLabel = new JLabel("Admin ");
		
		titleLabel = new JLabel("Title ");
		genreLabel = new JLabel("Genre ");
		distributionLabel = new JLabel("Distribution ");
		dateLabel = new JLabel("Date ");
		numberOfTicketsLabel = new JLabel("Number of tickets ");
		
		usernameText = new JTextField(15);
		passwordText = new JTextField(15);
		
		titleText = new JTextField(10);
		
		String genre[] = {"","Opera","Ballet"};
		genreBox = new JComboBox<String>(genre);
		
		String export[] = {"csv","xml"};
		exportBox = new JComboBox<String>(export);
		
		distributionArea = new JTextArea(5,20);
		distributionArea.setLineWrap(true);
		distributionArea.setWrapStyleWord(true);
		JScrollPane areaScrollPane = new JScrollPane(distributionArea);
		areaScrollPane.setPreferredSize(new Dimension(250, 50));
		
		dateText = new JTextField(15);
		numberOfTicketsText = new JTextField(5);
		isAdminText = new JTextField(5);
		
		addAccount = new JButton("Add Account");
		updateAccount = new JButton("Update Account");
		deleteAccount = new JButton("Delete Account");
		
		addShow = new JButton("Add Show");
		updateShow = new JButton("Update Show");
		deleteShow = new JButton("Delete Show");
		exportButton = new JButton("Export Show Tickets");
		
		cashiers = new UserTable();
		cashierTable = cashiers.createTable();
		cashierTable.setPreferredSize(new Dimension(800, 500));
		cashierTable.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		cashierPane = new JScrollPane(cashierTable);
		cashierPane.setPreferredSize(new Dimension(800, 300));
		
		shows = new ShowTable();
		showTable = shows.createTable();
		showTable.setPreferredSize(new Dimension(800, 500));
		showTable.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		showsPane = new JScrollPane(showTable);
		showsPane.setPreferredSize(new Dimension(800, 300));
		
		cashierPanelLabel.add(usernameLabel);
		cashierPanelLabel.add(usernameText);
		cashierPanelLabel.add(passwordLabel);
		cashierPanelLabel.add(passwordText);
		cashierPanelLabel.add(isAdminLabel);
		cashierPanelLabel.add(isAdminText);
		
		cashierPanelButton.add(addAccount);
		cashierPanelButton.add(updateAccount);
		cashierPanelButton.add(deleteAccount);
		
		cashierPanelTable.add(cashierPane);
			
		showPanelLabel.add(titleLabel);
		showPanelLabel.add(titleText);
		showPanelLabel.add(genreLabel);
		showPanelLabel.add(genreBox);
		showPanelLabel.add(distributionLabel);
		showPanelLabel.add(areaScrollPane);
		showPanelLabel.add(dateLabel);
		showPanelLabel.add(dateText);
		showPanelLabel.add(numberOfTicketsLabel);
		showPanelLabel.add(numberOfTicketsText);
		
		showPanelButton.add(addShow);
		showPanelButton.add(updateShow);
		showPanelButton.add(deleteShow);
		
		showPanelTable.add(showsPane);
		
		exportPanel.add(exportBox);
		exportPanel.add(exportButton);
		
		adminPanel.add(cashierPanelLabel);
		adminPanel.add(cashierPanelButton);
		adminPanel.add(cashierPanelTable);
		adminPanel.add(showPanelLabel);
		adminPanel.add(showPanelButton);
		adminPanel.add(showPanelTable);
		adminPanel.add(exportPanel);
		
		add(adminPanel);
		setVisible(true);
		
		
		addAccount.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				String username;
				String password;
				int isAdmin;
				
				UserModel user = new UserModel();
				UserService service = new UserService();

				try {
					username = usernameText.getText();
					password = passwordText.getText();
					isAdmin = Integer.parseInt(isAdminText.getText());
					
			        String securePassword = Encryption.get_SHA_1_SecurePassword(password);
					
					user.setUsername(username);
					user.setPassword(securePassword);
					user.setIsAdmin(isAdmin);
					
					service.insertUser(user);
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "Incorrect username or password!");
				}
				refreshAccount();
				
			}
			
		});
		
		deleteAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserModel user = new UserModel();
				UserService service = new UserService();
				try {
					
					if(firstAccountTable == false) {
						user = service.findUser((Integer) cashierTable.getValueAt(cashierTable.getSelectedRow(), 0));
						firstAccountTable = true;
					}
					else {
						user = service.findUser((Integer) newAccountTable.getValueAt(newAccountTable.getSelectedRow(), 0));
					}
					service.deleteUser(user.getUserId());
					
				} catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Account does not exist!");
				}
				refreshAccount();
			}
		});
		
		updateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String username;
				String password;
				int isAdmin = 0;
				
				UserModel user = new UserModel();
				UserService service = new UserService();
				
				try {
					
					if(firstAccountTable == false) {
						user = service.findUser((Integer) cashierTable.getValueAt(cashierTable.getSelectedRow(), 0));
						firstAccountTable = true;
					}
					else {
						user = service.findUser((Integer) newAccountTable.getValueAt(newAccountTable.getSelectedRow(), 0));
					}
					
					if(usernameText.getText().equals("")) {
						username = user.getUsername();
					}
					else {
						username = usernameText.getText();
					}
					
					if(passwordText.getText().equals("")) {
						password = user.getPassword();
					}
					else {
						password = passwordText.getText();
					}
					
					UserModel updateUser = new UserModel(user.getUserId(),username,password,isAdmin);
					service.updateUser(user.getUserId(), updateUser);
					
				} catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Account cannot be updated!");
				}
				
				refreshAccount();
			}
		});
		
		addShow.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String title;
				String distribution;
				String genre;
				Timestamp date;
				int numberOfTickets;
				
				ShowModel show;
				ShowService service = new ShowService();

				try {
					title = titleText.getText();
					distribution = distributionArea.getText();
					genre = genreBox.getSelectedItem().toString();
					date = Timestamp.valueOf(dateText.getText());
					numberOfTickets = Integer.parseInt(numberOfTicketsText.getText());
					
					show = new ShowModel(title,distribution,genre,date,numberOfTickets);
					service.insertShow(show);
					
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "Incorrect username or password!");
				}
				refreshShow();
			}
			
		});
		
		deleteShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowModel show;
				ShowService service = new ShowService();
				try {
					
					if(firstShowTable == false) {
						show = service.findShow((Integer) showTable.getValueAt(showTable.getSelectedRow(), 0));
						firstShowTable = true;
					}
					else {
						show = service.findShow((Integer) newShowTable.getValueAt(newShowTable.getSelectedRow(), 0));
					}
					service.deleteShow(show.getShowId());
					
				} catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Show does not exist!");
				}
				refreshShow();
			}
		});
		
		updateShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String title;
				String distribution;
				String genre;
				Timestamp date;
				int numberOfTickets;
				
				ShowModel show;
				ShowService service = new ShowService();
				
				try {
					
					if(firstShowTable == false) {
						show = service.findShow((Integer) showTable.getValueAt(showTable.getSelectedRow(), 0));
						firstShowTable = true;
					}
					else {
						show = service.findShow((Integer) newShowTable.getValueAt(newShowTable.getSelectedRow(), 0));
					}
					
					if(titleText.getText().equals("")) {
						title = show.getTitle();
					}
					else {
						title = titleText.getText();
					}
					
					if(distributionArea.getText().equals("")) {
						distribution = show.getDistribution();
					}
					else {
						distribution = distributionArea.getText();
					}
					
					if(genreBox.getSelectedItem().toString().equals("")) {
						genre = show.getGenre();
					}
					else {
						genre = genreBox.getSelectedItem().toString();
					}
					
					if(dateText.getText().equals("")) {
						date = show.getDate();
					}
					else {
						date = Timestamp.valueOf(dateText.getText());
					}
					
					if(numberOfTicketsText.getText().equals("")) {
						numberOfTickets = show.getNumberOfTickets();
					}
					else {
						numberOfTickets = Integer.parseInt(numberOfTicketsText.getText());
					}
					
					ShowModel updateShow = new ShowModel(show.getShowId(),title,distribution,genre,date,numberOfTickets);
					service.updateShow(show.getShowId(), updateShow);
					
				} catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Show cannot be updated!");
				}
				
				refreshShow();
			}
		});
		
		exportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ShowModel show;
				
				ShowService service = new ShowService();
				try {
					
					//if(firstShowTable == false) {
						show = service.findShow((Integer) showTable.getValueAt(showTable.getSelectedRow(), 0));
					//	firstShowTable = true;
					//}
					//else {
					//	show = service.findShow((Integer) newShowTable.getValueAt(newShowTable.getSelectedRow(), 0));
					//}
					
					String format = exportBox.getSelectedItem().toString();
					List<TicketModel> tickets = new ArrayList<TicketModel>();
					Theater theater = new Theater();
					
					System.out.println(show.getShowId());
					
					tickets = theater.findAllTicketsForShow(show.getShowId());
					
					
					WriterFactory.getReader(format).write(tickets);
					
				} catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Show does not exist!");
				}
				//refreshShow();
			}			
		});
	}
	
	private void refreshAccount() {
		newAccountTable = cashiers.createTable();
		newAccountTable.setPreferredSize(new Dimension(800, 500));
		newAccountTable.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		JScrollPane newPane = new JScrollPane(newAccountTable);
		newPane.setPreferredSize(new Dimension(800, 300));
		
		cashierPanelTable.removeAll();
		cashierPanelTable.add(newPane);
		cashierPanelTable.revalidate();
		cashierPanelTable.repaint();
		
		usernameText.setText("");
		passwordText.setText("");
		//isAdminText.setText("");
		
	}
	
	private void refreshShow() {
		newShowTable = shows.createTable();
		newShowTable.setPreferredSize(new Dimension(800, 500));
		newShowTable.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		JScrollPane newPane = new JScrollPane(newShowTable);
		newPane.setPreferredSize(new Dimension(800, 300));
		
		showPanelTable.removeAll();
		showPanelTable.add(newPane);
		showPanelTable.revalidate();
		showPanelTable.repaint();
		
		titleText.setText("");
		distributionArea.setText("");
		dateText.setText("");
		numberOfTicketsText.setText("");
	}
	
}
