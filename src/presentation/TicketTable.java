package presentation;

import java.lang.reflect.Field;
import java.util.List;
import javax.swing.JTable;

import business.Theater;
import business.model.ShowModel;
import business.model.TicketModel;
import business.service.IShowService;
import business.service.ITicketService;


public class TicketTable  extends AbstractTable<TicketModel>{
	
	private ITicketService service;
	private List<TicketModel> tickets;
	private Theater theater;
	
	public JTable create(List<TicketModel> tickets) {
		TicketTable table = new TicketTable();
		JTable ticketTable = table.createTable(tickets);
		return ticketTable;
	}
}
