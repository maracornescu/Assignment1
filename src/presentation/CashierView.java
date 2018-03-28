package presentation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import business.Encryption;
import business.Theater;
import business.model.ShowModel;
import business.model.TicketModel;
import business.service.ShowService;
import business.service.TicketService;

public class CashierView extends JFrame{
	private JPanel showTablePanel, ticketDataPanel, ticketButonPanel, ticketTablePanel, finalPanel;
	private JLabel showRowLabel, showNumberLabel, showPriceLabel;
	private JTextField showRowText, showNumberText, showPriceText;
	private JButton sellTicketButton, cancelTicketButton, editTicketButton, showAllTicketsButton;
	private JTable ticketTable, newTicketTable, showTable, newShowTable;
	private JScrollPane ticketPane, showsPane;
	private ShowTable shows;
	private TicketTable tickets;
	
	public CashierView() {
		super("Cashier");
		
		setLayout(new GridLayout());
		setSize(1000,750);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		showTablePanel = new JPanel();
		ticketDataPanel = new JPanel();
		ticketButonPanel = new JPanel();
		ticketTablePanel = new JPanel();
		finalPanel = new JPanel();
		
		showRowLabel = new JLabel("Ticket Row ");
		showNumberLabel = new JLabel("Seat Number ");
		showPriceLabel = new JLabel("Price ");
	
		showRowText = new JTextField(10);
		showNumberText = new JTextField(10);
		showPriceText = new JTextField(10);
		
		sellTicketButton = new JButton("Sell Ticket");
		cancelTicketButton = new JButton("Cancel Reservation");
		editTicketButton = new JButton("Edit Ticket");
		showAllTicketsButton = new JButton("Show All Tickets");
		
		shows = new ShowTable();
		showTable = shows.createTable();
		showTable.setPreferredSize(new Dimension(800, 500));
		showTable.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		showsPane = new JScrollPane(showTable);
		showsPane.setPreferredSize(new Dimension(800, 300));
		
		tickets = new TicketTable();
		ticketTable = new JTable();
		ticketTable.setPreferredSize(new Dimension(800, 500));
		ticketTable.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		ticketPane = new JScrollPane(ticketTable);
		ticketPane.setPreferredSize(new Dimension(800, 300));
		
		showTablePanel.add(showsPane);
		
		ticketDataPanel.add(showRowLabel);
		ticketDataPanel.add(showRowText);
		ticketDataPanel.add(showNumberLabel);
		ticketDataPanel.add(showNumberText);
		ticketDataPanel.add(showPriceLabel);
		ticketDataPanel.add(showPriceText);
		
		ticketButonPanel.add(sellTicketButton);
		ticketButonPanel.add(cancelTicketButton);
		ticketButonPanel.add(editTicketButton);
		ticketButonPanel.add(showAllTicketsButton);
		
		ticketTablePanel.add(ticketPane);
		
		
		finalPanel.add(showTablePanel);
		finalPanel.add(ticketDataPanel);
		finalPanel.add(ticketButonPanel);
		finalPanel.add(ticketTablePanel);
		
		add(finalPanel);
		setVisible(true);
		
		showAllTicketsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Theater service = new Theater();
				ShowService showService = new ShowService();
				ShowModel show;
				List<TicketModel> tickets;
				
				try {
					show = showService.findShow((Integer) showTable.getValueAt(showTable.getSelectedRow(), 0));
					tickets = service.findAllTicketsForShow(show.getShowId());	
					refreshTicket(tickets);	
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "No Tickets Available!");
				}
			}
		});
		
		sellTicketButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Theater service = new Theater();
				ShowService showService = new ShowService();
				ShowModel show;
				List<TicketModel> tickets;
				
				int seatRow;
				int seatNumber;
				float price;
				
				try {
					show = showService.findShow((Integer) showTable.getValueAt(showTable.getSelectedRow(), 0));
					seatRow = Integer.parseInt(showRowText.getText());
					seatNumber = Integer.parseInt(showNumberText.getText());
					price = Float.parseFloat(showPriceText.getText());
					
					service.sellTicket(show.getShowId(), seatRow, seatNumber, price);
					
					tickets = service.findAllTicketsForShow(show.getShowId());	
					refreshTicket(tickets);	
					refreshShow();
				} catch(IllegalArgumentException err) {
					JOptionPane.showMessageDialog(null, err.getMessage());
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "No Tickets Available!");
				}
			}
		});
		
		cancelTicketButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Theater service = new Theater();
				ShowService showService = new ShowService();
				TicketService ticketService = new TicketService();
				ShowModel show;
				TicketModel ticket;
				List<TicketModel> tickets;
				
				try {
					ticket = ticketService.findTicket((Integer) newTicketTable.getValueAt(newTicketTable.getSelectedRow(), 0));
					
					ticketService.deleteTicket(ticket.getTicketId());
					
					show = showService.findShow(ticket.getShowId());
					service.cancelSelling(show);
					
					tickets = service.findAllTicketsForShow(ticket.getShowId());	
					refreshTicket(tickets);	
					refreshShow();
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "Cancel Ticket Error!");
				}
			}
		});
		
		editTicketButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Theater service = new Theater();
				TicketService ticketService = new TicketService();
				TicketModel ticket;
				TicketModel newTicket;
				List<TicketModel> tickets;
				
				int seatRow;
				int seatNumber;
				float price;
				
				try {
					ticket = ticketService.findTicket((Integer) newTicketTable.getValueAt(newTicketTable.getSelectedRow(), 0));
					
					if(showRowText.getText().equals("")) {
						seatRow = ticket.getSeatRow();
					}
					else {
						seatRow = Integer.parseInt(showRowText.getText());
					}
					
					if(showNumberText.getText().equals("")) {
						seatNumber = ticket.getSeatNumber();
					}
					else {
						seatNumber = Integer.parseInt(showNumberText.getText());
					}
					
					if(showPriceText.getText().equals("")) {
						price = ticket.getPrice();
					}
					else {
						price = Float.parseFloat(showPriceText.getText());
					}
					
					newTicket = new TicketModel(ticket.getTicketId(),ticket.getShowId(),seatRow,seatNumber,price);
					ticketService.updateTicket(newTicket.getTicketId(), newTicket);
					
					tickets = service.findAllTicketsForShow(ticket.getShowId());	
					refreshTicket(tickets);	
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "Update Ticket Error!");
				}
				
			}
		});
		
	}
	
	private void refreshTicket(List<TicketModel> t) {
		newTicketTable = tickets.create(t);
		newTicketTable.setPreferredSize(new Dimension(800, 500));
		newTicketTable.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		JScrollPane newPane = new JScrollPane(newTicketTable);
		newPane.setPreferredSize(new Dimension(800, 300));
		
		ticketTablePanel.removeAll();
		ticketTablePanel.add(newPane);
		ticketTablePanel.revalidate();
		ticketTablePanel.repaint();
	}
	
	private void refreshShow() {
		newShowTable = shows.createTable();
		newShowTable.setPreferredSize(new Dimension(800, 500));
		newShowTable.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		JScrollPane newPane = new JScrollPane(newShowTable);
		newPane.setPreferredSize(new Dimension(800, 300));
		
		showTablePanel.removeAll();
		showTablePanel.add(newPane);
		showTablePanel.revalidate();
		showTablePanel.repaint();
	}
	
}
