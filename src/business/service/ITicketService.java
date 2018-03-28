package business.service;

import java.util.List;

import business.model.TicketModel;

public interface ITicketService {
	
	public List<TicketModel> findAllTickets();
	public TicketModel findTicket(int id);
	public void deleteTicket(int id);
	public void updateTicket(int id, TicketModel model);
	public void insertTicket(TicketModel model);
	

}
